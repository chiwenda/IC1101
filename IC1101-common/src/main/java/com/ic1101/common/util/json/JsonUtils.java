package com.ic1101.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author cwd
 * @date 6/10/22 5:44 PM
 */
public class JsonUtils {
    private static ObjectMapper jsonMapper = new ObjectMapper();

    static {
        //配置序列化空对象动作结果
        jsonMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String toJsonString(final Object object) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(object);
    }

    public static byte[] toJsonByte(final Object object) throws JsonProcessingException {
        return jsonMapper.writeValueAsBytes(object);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if(StrU)
    }
}
