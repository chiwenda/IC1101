package com.ic1101.common.util.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：chiwd
 * @description：set工具
 * @date ：2022/7/2 18:22
 */
public class SetUtils {

    /**
     * 数组转set
     */
    public static <T> Set<T> asSet(T... values) {
        return new HashSet<>(Arrays.asList(values));
    }
}
