package com.lionel.base.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Li Shijie
 * @Description: 空对象序列化
 * @date 2018/3/4
 */
public class NullObjectJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (value == null) {
            gen.writeStartObject();
            gen.writeEndObject();
        } else {
            gen.writeObject(value);
        }
    }
}