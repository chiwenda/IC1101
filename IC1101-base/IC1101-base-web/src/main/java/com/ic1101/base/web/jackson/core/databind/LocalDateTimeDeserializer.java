package com.ic1101.base.web.jackson.core.databind;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author ：chiwd
 * @description：TODO
 * @date ：2022/7/4 21:04
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    public static final LocalDateTimeDeserializer INStANCE = new LocalDateTimeDeserializer();

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(p.getValueAsLong()), ZoneId.systemDefault());
    }
}
