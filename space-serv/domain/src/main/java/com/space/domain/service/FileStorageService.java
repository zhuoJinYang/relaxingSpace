package com.space.domain.service;

import com.space.db.entity.FileStorage;
import lombok.NonNull;

public interface FileStorageService {
    FileStorage getById(@NonNull Long id);

    void save(@NonNull FileStorage storage);
}
