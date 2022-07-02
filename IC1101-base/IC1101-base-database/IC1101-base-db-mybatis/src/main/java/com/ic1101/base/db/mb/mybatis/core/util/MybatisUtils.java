package com.ic1101.base.db.mb.mybatis.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ic1101.common.pojo.PageParam;
import com.ic1101.common.pojo.SortingField;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/2 20:40
 */
public class MybatisUtils {
    private static final String MYSQL_ESCAPE_CHARACTER = "`";

    public static <T> Page<T> buildPage(PageParam pageParam) {
        return buildPage(pageParam, null);
    }

    public static <T> Page<T> buildPage(PageParam pageParam, Collection<SortingField> sortingFields) {
        Page<T> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        if (CollectionUtil.isNotEmpty(sortingFields)) {
            page.addOrder(sortingFields.stream()
                    .map(sortingField -> SortingField.ORDER_ASC.equals(sortingField.getField()) ?
                            OrderItem.asc(sortingField.getField()) : OrderItem.desc(sortingField.getField()))
                    .collect(Collectors.toList()));
        }
        return page;
    }
}
