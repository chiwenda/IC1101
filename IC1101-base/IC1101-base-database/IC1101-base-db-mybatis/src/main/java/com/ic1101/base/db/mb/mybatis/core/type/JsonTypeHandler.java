package com.ic1101.base.db.mb.mybatis.core.type;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ic1101.common.util.json.JsonUtils;

import java.util.Set;

/**
 * @author ：chiwd
 * @description：mybatis序列化和反序列化json处理工具
 * @date ：2022/7/3 10:09
 */
public class JsonTypeHandler extends AbstractJsonTypeHandler<Object> {

    //处理反序列化为Set<Long>时由于值太小变成Set<Integer>的问题
    private static final TypeReference<Set<Long>> typeReference = new TypeReference<Set<Long>>() {
    };

    @Override
    protected Object parse(String json) {
        return JsonUtils.parseObject(json, typeReference);
    }

    @Override
    protected String toJson(Object obj) {
        return JsonUtils.toJsonString(obj);
    }
}
