package com.example.demo.ToolUtil;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 业务层错误格式化工厂
 * @author: zqy
 * @date: 2018/4/11 15:43
 * @since: 1.0-SNAPSHOT
 * @note: none
 */
public class FormatableErrorFactory {
    String formatString;

    int errorCode;

    public FormatableErrorFactory(int errorCode, String formatString) {
        this.formatString = formatString;
        this.errorCode = errorCode;
    }

    public ValidationException baseException(String ... paras) {
        return new ValidationException(this.errorCode, String.format(formatString, paras));
    }

    public void assertThrowable(boolean throwable, String ... paras) {
        if (throwable) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(Long val, String ... paras) {
        if (val == null || val.longValue() == 0L) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(String str, String ... paras) {
        if (str == null || str.trim().isEmpty()) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(Integer val, String ... paras) {
        if (val == null || val.intValue() == 0) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(Object val, String ... paras) {
        if (val == null) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(Object[] values, String ... paras) {
        if (values == null || 0 == values.length) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(Collection<?> val, String ... paras) {
        if (val == null || val.isEmpty()) {
            throw baseException(paras);
        }
    }

    public void ifEmptyThrow(Map val, String ... paras) {
        if (val == null || val.isEmpty()) {
            throw baseException(paras);
        }
    }

    public void ifNotEmptyThrow(Long val, String ... paras) {
        if (val != null && val.longValue() != 0L) {
            throw baseException(paras);
        }
    }

    public void ifNotEmptyThrow(String str, String ... paras) {
        if (str != null && !str.trim().isEmpty()) {
            throw baseException(paras);
        }
    }

    public void ifNotEmptyThrow(Integer val, String ... paras) {
        if (val != null && val.intValue() != 0) {
            throw baseException(paras);
        }
    }

    public void ifNotEmptyThrow(Object val, String ... paras) {
        if (val != null) {
            throw baseException(paras);
        }
    }

    public void ifNotEmptyThrow(Collection<?> val, String ... paras) {
        if (val != null && !val.isEmpty()) {
            throw baseException(paras);
        }
    }

    public void ifNotEmptyThrow(Map map, String ... paras) {
        if (!CollectionUtils.isEmpty(map)) {
            throw baseException(paras);
        }
    }

    public static final int SUCCESS = 1000;
    public static final String SUCCESS_MSG = "success";
    public static final int ERROR_LOGIN = -1;//登陆错误
    public static final int UN_LOGIN = -2;//登陆无效
    public static final int INVALID_REQUEST_PARAMETERS_CODE = 1001;
    public static final int NOT_BE_EMPTY_CODE = 1002;
    public static final int BE_EMPTY_CODE = 1003;
    public static final int NOT_EXIST_CODE = 1004;
    public static final int EXISTED_CODE = 1005;
    public static final int CUSTOM_CODE = 1006;
    public static final int UNAUTHORIZED_ERROR_CODE = 1007;
    public static final int INVALID_REQUEST_SESSION_CODE = 1008;
    public static final int REQUEST_SERVER_ERROR_CODE = 1009;
    public static final int SERVER_INNER_ERROR_CODE = 1010;
    
    public static final FormatableErrorFactory CUSTOM = new FormatableErrorFactory(CUSTOM_CODE, "%s");
    public static final FormatableErrorFactory NOT_EXIST = new FormatableErrorFactory(NOT_EXIST_CODE, "%s");
}
