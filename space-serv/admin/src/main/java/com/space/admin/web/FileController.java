package com.space.admin.web;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.space.admin.converter.VoConverter;
import com.space.db.entity.FileStorage;
import com.space.domain.constant.ErrorCode;
import com.space.domain.exception.ServiceException;
import com.space.domain.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件操作接口
 */
@RequestMapping("/file")
@RestController
public class FileController {

    @Value("${custom.file-storage.root-path}")
    private String fileStorageRootPath;

    @Resource
    private FileStorageService fileStorageService;

    @GetMapping("/preview")
    public void preview(@RequestParam Long key, HttpServletResponse response){
        FileStorage storage = fileStorageService.getById(key);
        if (ObjectUtil.isNull(storage)){
            throw new ServiceException(ErrorCode.FILE_NOT_EXIT);
        }

        String filePath = storage.getAbsolutePath();
        File file = FileUtil.file(filePath);
        if (!FileUtil.exist(file)){
            throw new ServiceException(ErrorCode.FILE_NOT_EXIT);
        }

        //括号里可以声明对象，声明的对象必须实现autoCloseable接口，可以声明多个变量。
        //在括号里声明的对象，无需手动关闭，用完后会自动关闭
        try (BufferedInputStream is = FileUtil.getInputStream(file); ServletOutputStream os = response.getOutputStream()) {
            int len = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((len = is.read(buffer)) != -1){
                os.write(buffer, 0, len);
                os.flush();
            }
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.FILE_DOWNLOAD_FAIL);
        }
    }

    @GetMapping("/download")
    public void download(@RequestParam Long key, HttpServletResponse response){
        FileStorage storage = fileStorageService.getById(key);
        if (ObjectUtil.isNull(storage)){
            throw new ServiceException(ErrorCode.FILE_NOT_EXIT);
        }

        String filePath = storage.getAbsolutePath();
        File file = FileUtil.file(filePath);
        if (!FileUtil.exist(file)){
            throw new ServiceException(ErrorCode.FILE_NOT_EXIT);
        }

        try (BufferedInputStream is = FileUtil.getInputStream(file); ServletOutputStream os = response.getOutputStream()) {
            response.reset();
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置下载文件名
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(storage.getName(),"UTF-8"));
            response.setHeader("Content-Length","" + file.length());

            int len = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                os.flush();
            }
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.FILE_DOWNLOAD_FAIL);
        }
    }

    @PostMapping("/upload")
    public Object upload(@RequestParam(name = "file") MultipartFile uploadFile){
        // 获取文件名
        String filename = uploadFile.getOriginalFilename();
        // 获取文件后缀名
        String suffix = FileNameUtil.getSuffix(filename);

        String uuid = IdUtil.fastSimpleUUID();
        File file = FileUtil.touch(fileStorageRootPath + uuid + StrUtil.DOT + suffix);
        try {
            uploadFile.transferTo(file);
        } catch (IOException e) {
            throw new ServiceException(ErrorCode.FILE_SAVE_FAIL);
        }

        // 获取文件类型
        String type = FileTypeUtil.getType(file);

        FileStorage storage = new FileStorage();
        storage.setName(filename);
        storage.setType(type);
        storage.setSuffix(suffix);
        storage.setSize(uploadFile.getSize());
        storage.setPath(file.getPath());
        storage.setAbsolutePath(file.getAbsolutePath());
        fileStorageService.save(storage);

        return VoConverter.INSTANCE.convert(storage);
    }
}
