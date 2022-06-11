package com.ic1101.common.util.collection;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author ：chiwd
 * @description：集合工具
 * @date ：2022/6/10 23:21
 */
public class CollectionUtils {

    public static boolean containerAny(Object source, Object... targets) {
        return Arrays.asList(targets).contains(source);
    }

    /**
     * 任意集合中是否有为空
     */
    public static boolean isAnyEmpty(Collection<?>... collections) {
        return Arrays.stream(collections).anyMatch(Collection::isEmpty);
    }

    /**
     * 条件过滤集合
     */
    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        if (from == null || from.isEmpty()) {
            return new ArrayList<>();
        }

        return from.stream().filter(predicate).collect(Collectors.toList());
    }

}
