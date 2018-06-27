package com.lionel.base.string;

import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Li Shijie
 * @Description: 自定义转换器
 * @date 2018/3/4
 */
public class StringToBooleanConverter implements Converter<String, Boolean> {
    private static final Set<String> trueValues = new HashSet(4);
    private static final Set<String> falseValues = new HashSet(4);

    public StringToBooleanConverter() {
    }

    public Boolean convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        } else {
            value = value.toLowerCase();
            if (trueValues.contains(value)) {
                return Boolean.TRUE;
            } else if (falseValues.contains(value)) {
                return Boolean.FALSE;
            } else {
                throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
            }
        }
    }

    static {
        trueValues.add("true");
        trueValues.add("on");
        trueValues.add("yes");
        trueValues.add("1");
        falseValues.add("false");
        falseValues.add("off");
        falseValues.add("no");
        falseValues.add("0");
        falseValues.add("");
    }
}
