package com.ayundao.base.utils;

import com.ayundao.base.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ClassName: ClassUtils
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/17 19:15
 * @Description: 类工具类
 * @Version: V1.0
 */
public class ClassUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);

    public static String forceGetProperty(Object obj, String name) {
        String result = "";
        try {
            Field[] fields = obj.getClass().getFields();
            for (Field field : fields) {
                if (field.getName().equals(name)) {
                    field.setAccessible(true);
                    result = field.getName();
                }
            }
        } catch (NullPointerException e) {
            logger.error(e.getLocalizedMessage());
        }
        return result;
    }

    public static Map<String, Object> getDeclaredFieldsWithSuper(Class<? extends BaseEntity> clazz) {
        return null;
    }

}
