package com.space.domain.service;

import com.space.db.entity.BlogContent;
import lombok.NonNull;

public interface BlogContentService {
    void save(@NonNull BlogContent blogContext);

    void updateByBlogId(@NonNull Long blogId,@NonNull BlogContent blogContext);
}
