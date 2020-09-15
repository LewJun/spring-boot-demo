package com.example.lewjun.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * @author LewJun
 */
@Slf4j
public class QueryWrapperUtils {
    public static <T> QueryWrapper<T> initQueryWrapper(final T t) {
        final QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        final PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(t);
        for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            final String name = propertyDescriptor.getName();
            final String type = propertyDescriptor.getPropertyType().toString();

            if ("class".equals(name) || !PropertyUtils.isReadable(t, name)) {
                continue;
            }

            try {
                final Object value = propertyDescriptor.getReadMethod().invoke(t, null);
                if (value != null) {
                    final String val = value.toString();
                    queryWrapper.eq(CamelUnderlineUtil.camelToUnderline(name), getValue(type, val));
                }
            } catch (final IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return queryWrapper;
    }

    private static Object getValue(final String type, final String value) {
        final Object ret;
        switch (type) {
            case "class java.lang.Integer":
                ret = Integer.parseInt(value);
                break;
            case "class java.math.BigDecimal":
                ret = new BigDecimal(value);
                break;
            case "class java.lang.Short":
                ret = Short.parseShort(value);
                break;
            case "class java.lang.Long":
                ret = Long.parseLong(value);
                break;
            case "class java.lang.Float":
                ret = Float.parseFloat(value);
                break;
            case "class java.lang.Double":
                ret = Double.parseDouble(value);
                break;
            default:
                ret = value;
                break;
        }
        return ret;
    }
}
