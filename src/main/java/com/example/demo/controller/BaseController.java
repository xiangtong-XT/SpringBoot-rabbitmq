package com.example.demo.controller;

import com.example.demo.ToolUtil.FormatableErrorFactory;
import com.example.demo.ToolUtil.ServerDataResponse;
import com.example.demo.ToolUtil.ServerResponse;

public class BaseController {

    //校验失败
    protected ServerResponse validationError(String error) {
        return new ServerResponse(FormatableErrorFactory.INVALID_REQUEST_PARAMETERS_CODE, error);
    }

    //错误
    protected ServerResponse apiError(String error) {
        return new ServerResponse(FormatableErrorFactory.REQUEST_SERVER_ERROR_CODE, error);
    }

    //成功
    protected ServerResponse success(String... msg) {
        return new ServerResponse(FormatableErrorFactory.SUCCESS, null == msg || 0 == msg.length ? FormatableErrorFactory.SUCCESS_MSG : msg[0]);
    }

    //返回数据
    protected ServerResponse resultData(Object data) {
        return new ServerDataResponse(data);
    }
}
