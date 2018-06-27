package com.lionel.base.string;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义转换器：解析字符串到Date
 * 
 */
public class StringToDateConverter implements Converter<String, Date>, InitializingBean {
    public final String[] FORMAT_PATTERN = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyyMMdd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd"};
    public DateFormat[] formaters;

    public StringToDateConverter() {
    }

    public Date convert(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        } else {
            Date date = null;
            DateFormat[] arr$ = this.formaters;
            int len$ = arr$.length;
            int i$ = 0;

            while (i$ < len$) {
                DateFormat formater = arr$[i$];

                try {
                    date = formater.parse(s);
                    return date;
                } catch (ParseException var8) {
                    ++i$;
                }
            }

            return date;
        }
    }

    public void afterPropertiesSet() throws Exception {
        this.formaters = new DateFormat[this.FORMAT_PATTERN.length];

        for (int i = 0; i < this.FORMAT_PATTERN.length; ++i) {
            this.formaters[i] = new SimpleDateFormat(this.FORMAT_PATTERN[i]);
        }

    }
}

