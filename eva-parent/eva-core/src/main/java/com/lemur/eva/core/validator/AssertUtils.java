package com.lemur.eva.core.validator;

import com.lemur.eva.core.exception.ErrorCodeMeta;
import com.lemur.eva.core.exception.EvaException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 校验工具类
 *
 */
public class AssertUtils {

    public static void isBlank(String str, String... params) {
        isBlank(str, ErrorCodeMeta.NOT_NULL, params);
    }

    public static void isBlank(String str, Integer code, String... params) {
        if(code == null){
            throw new EvaException(ErrorCodeMeta.NOT_NULL, "code");
        }

        if (StringUtils.isBlank(str)) {
            throw new EvaException(code, params);
        }
    }

    public static void isNull(Object object, String... params) {
        isNull(object, ErrorCodeMeta.NOT_NULL, params);
    }

    public static void isNull(Object object, Integer code, String... params) {
        if(code == null){
            throw new EvaException(ErrorCodeMeta.NOT_NULL, "code");
        }

        if (object == null) {
            throw new EvaException(code, params);
        }
    }

    public static void isArrayEmpty(Object[] array, String... params) {
        isArrayEmpty(array, ErrorCodeMeta.NOT_NULL, params);
    }

    public static void isArrayEmpty(Object[] array, Integer code, String... params) {
        if(code == null){
            throw new EvaException(ErrorCodeMeta.NOT_NULL, "code");
        }

        if(ArrayUtils.isEmpty(array)){
            throw new EvaException(code, params);
        }
    }

    public static void isListEmpty(List<?> list, String... params) {
        isListEmpty(list, ErrorCodeMeta.NOT_NULL, params);
    }

    public static void isListEmpty(List<?> list, Integer code, String... params) {
        if(code == null){
            throw new EvaException(ErrorCodeMeta.NOT_NULL, "code");
        }

        if(CollectionUtils.isEmpty(list)){
            throw new EvaException(code, params);
        }
    }

    public static void isMapEmpty(Map map, String... params) {
        isMapEmpty(map, ErrorCodeMeta.NOT_NULL, params);
    }

    public static void isMapEmpty(Map map, Integer code, String... params) {
        if(code == null){
            throw new EvaException(ErrorCodeMeta.NOT_NULL, "code");
        }

        if(MapUtils.isEmpty(map)){
            throw new EvaException(code, params);
        }
    }
}