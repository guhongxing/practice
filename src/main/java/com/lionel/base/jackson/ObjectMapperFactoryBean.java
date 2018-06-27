package com.lionel.base.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.text.DateFormat;

/**
 * @author Li Shijie
 * @Description: 实例化 ObjectMapper
 * @date 2018/3/4
 */
public class ObjectMapperFactoryBean implements FactoryBean<ObjectMapper>, InitializingBean {

    private JsonInclude.Include serializationInclusion;
    private DateFormat dateFormat;

    @Override
    public ObjectMapper getObject() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(serializationInclusion);
        objectMapper.setDateFormat(dateFormat);

        /*DefaultSerializerProvider serializerProvider =
                (DefaultSerializerProvider) objectMapper.getSerializerProvider();
        serializerProvider.setNullValueSerializer(new NullKeyJsonSeralizer());
        objectMapper.setSerializerProvider(serializerProvider);*/

        objectMapper.setSerializerFactory(
                objectMapper.getSerializerFactory().withSerializerModifier(new EagleBeanSerializerModifier())
        );

        return objectMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public JsonInclude.Include getSerializationInclusion() {
        return serializationInclusion;
    }

    public void setSerializationInclusion(JsonInclude.Include serializationInclusion) {
        this.serializationInclusion = serializationInclusion;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
}
