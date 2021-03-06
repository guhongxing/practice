package com.lionel.base.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Li Shijie
 * @Description: 空字段序列化
 * @date 2018/3/4
 */
public class NullPropertyJsonSerializer  extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator generator, SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        generator.writeObject(value);
    }

}
