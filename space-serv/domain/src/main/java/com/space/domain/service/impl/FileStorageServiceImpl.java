package com.space.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.space.db.entity.FileStorage;
import com.space.db.mapper.FileStorageMapper;
import com.space.domain.service.FileStorageService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileStorageServiceImpl extends ServiceImpl<FileStorageMapper,FileStorage> implements FileStorageService {

    @Resource
    private FileStorageMapper fileStorageMapper;

}
