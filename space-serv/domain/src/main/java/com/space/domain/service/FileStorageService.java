package com.space.domain.service;

import com.space.db.entity.FileStorage;
import lombok.NonNull;

public interface FileStorageService {

    /**
     * 根据文件id获取文件信息
     * @param id 文件id
     * @return 文件信息
     */
    FileStorage getById(@NonNull Long id);

    /**
     * 保存文件信息
     * @param storage 文件信息
     */
    void save(@NonNull FileStorage storage);
}
