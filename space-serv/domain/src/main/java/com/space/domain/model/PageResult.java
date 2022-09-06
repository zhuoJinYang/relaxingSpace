package com.space.domain.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据标准返回结果实体类
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 默认的分页时每页数据条数
     */
    public static final long DEFAULT_PAGE_SIZE = 20L;
    /**
     * 分页页码
     */
    public long page;
    /**
     * 分页数据条数
     */
    public long size;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 数据总条数
     */
    private long total;
    /**
     * 分页数据列表
     */
    private List<T> list;

    private PageResult(long page,long size,long pages){
        this(page,size,pages,0,new ArrayList<>());
    }

    /**
     * 将MybatisPlus获得的分页对象处理为PageResult对象
     */
    public static <T> PageResult<T> convert(IPage<T> page){
        if (page == null) {
            return new PageResult<>(1L,DEFAULT_PAGE_SIZE,0L);
        }
        if (page.getRecords() == null || page.getRecords().isEmpty()) {
            return new PageResult<>(page.getCurrent(),page.getSize(),page.getPages());
        }
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal(), page.getRecords());
    }

    public static <E> PageResult<E> parse(IPage<?> page,List<E> list){
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal(),list);
    }
}
