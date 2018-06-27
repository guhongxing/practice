package com.lionel.base.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import com.lionel.base.jackson.serializer.NullArrayJsonSerializer;
import com.lionel.base.jackson.serializer.NullObjectJsonSerializer;
import com.lionel.base.jackson.serializer.NullPropertyJsonSerializer;
import org.springframework.util.ClassUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Li Shijie
 * @Description: 空值处理
 * @date 2018/3/4
 */
public class EagleBeanSerializerModifier extends BeanSerializerModifier {

    private JsonSerializer<Object> nullObjectJsonSerializer = new NullObjectJsonSerializer();
    private JsonSerializer<Object> nullArrayJsonSerializer = new NullArrayJsonSerializer();
    private JsonSerializer<Object> nullPropertyJsonSerializer = new NullPropertyJsonSerializer();

    private static Class<?>[] typeClasses = {
            int.class, boolean.class, byte.class, short.class,
            long.class, char.class, double.class, float.class,
            CharSequence.class, Date.class, Number.class
    };

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (BeanPropertyWriter writer : beanProperties) {
            JsonProperty jsonProperty = writer.getAnnotation(JsonProperty.class);
            if (jsonProperty != null && jsonProperty.required()) {
                //给writer注册一个自己的nullSerializer
                if (isArrayType(writer)) {
                    writer.assignNullSerializer(this.defaultNullArrayJsonSerializer());
                } else if (isDataType(writer)) {
                    //writer.assignNullSerializer(this.defaultNullPropertyJsonSerializer());
                } else {
                    writer.assignNullSerializer(this.defaultNullObjectJsonSerializer());
                }
            }
        }
        return beanProperties;
    }


    @Override
    public JsonSerializer<?> modifyKeySerializer(SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        AnnotatedMember member = beanDesc.findAnyGetter();
        return super.modifyKeySerializer(config, valueType, beanDesc, serializer);
    }

    // 判断是什么类型
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || ClassUtils.isAssignable(Collection.class, clazz);
    }

    private boolean isDataType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        boolean flag = false;
        for (Class<?> typeClass : typeClasses) {
            if (ClassUtils.isAssignable(typeClass, clazz)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private JsonSerializer<Object> defaultNullArrayJsonSerializer() {
        return nullArrayJsonSerializer;
    }

    private JsonSerializer<Object> defaultNullObjectJsonSerializer() {
        return nullObjectJsonSerializer;
    }

    private JsonSerializer<Object> defaultNullPropertyJsonSerializer() {
        return nullPropertyJsonSerializer;
    }

}

