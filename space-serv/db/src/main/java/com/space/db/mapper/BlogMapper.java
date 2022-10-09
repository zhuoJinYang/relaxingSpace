package com.space.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.space.db.dto.BlogDto;
import com.space.db.entity.Blog;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zjy
 * @since 2022-09-21
 */
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 获取博客详情信息
     */
    BlogDto getDetailById(Long id);
}
