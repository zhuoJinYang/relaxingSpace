package com.space.domain.service.impl;

import com.space.db.entity.FileStorage;
import com.space.db.mapper.FileStorageMapper;
import com.space.domain.service.FileStorageService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Resource
    private FileStorageMapper fileStorageMapper;

    @Override
    public FileStorage getById(@NonNull Long id) {
        return fileStorageMapper.selectById(id);
    }

    @Override
    public void save(@NonNull FileStorage storage) {
        fileStorageMapper.insert(storage);
    }
}
