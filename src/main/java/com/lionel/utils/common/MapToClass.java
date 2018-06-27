package com.lionel.utils.common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeConverter;
import org.springframework.util.Assert;

import com.lionel.base.string.StringToBooleanConverter;
import com.lionel.base.string.StringToDateConverter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
/**
 * Map转实体类
 *
 * @author Li Shijie
 * @date 2018/3/14
 */
public class MapToClass {
    private static TypeConverter typeConverter = new SimpleTypeConverter();

    private static StringToDateConverter dateConverter = new StringToDateConverter();

    private static StringToBooleanConverter booleanConverter = new StringToBooleanConverter();

    static{
        try {
            dateConverter.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 将Map转为对象.
     * @param map map 数据
     * @param clz 要转的类
     */
    public static <T> T convert(Map<String,Object> map, Class<T> clz){
        Assert.notNull(map);
        Assert.notNull(clz);

        T entity = null;
        try {
            entity = clz.newInstance();
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clz);

            for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
                if("class".equals(propertyDescriptor.getName())) continue;

                Class<?> type = propertyDescriptor.getPropertyType();
                Method writeMethod = propertyDescriptor.getWriteMethod();
                Object value = map.get(propertyDescriptor.getName());

                if(value == null || "".equals(value))continue;

                if(type.isAssignableFrom(Date.class)){
                    Date date = dateConverter.convert(value.toString());
                    writeMethod.invoke(entity, date);
                }else if (type.isAssignableFrom(Boolean.class)) {
                    Boolean booleans = booleanConverter.convert(value.toString());
                    writeMethod.invoke(entity, booleans);
                }else {
                    Object convertValue = typeConverter.convertIfNecessary(value, type);
                    writeMethod.invoke(entity, convertValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }


}
