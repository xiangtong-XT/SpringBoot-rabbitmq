package com.example.demo.ToolUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 多彩校园服务器后台响应
 * @author: zqy
 * @date: 2018/4/11 15:43
 * @since: 1.0-SNAPSHOT
 * @note: none
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerResponse implements Serializable {
    int code;


    String msg;

    public ServerResponse(ValidationException exception) {
        this.code = exception.getErrorCode();
        this.msg = exception.getMessage();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return 1000 == code;
    }
}
