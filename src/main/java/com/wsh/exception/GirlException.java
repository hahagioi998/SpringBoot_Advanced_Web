package com.wsh.exception;

import com.wsh.enums.ResultEnum;

/**
 * Created by wsh on 2017/11/30.
 * 自定义Exception  统一异常处理
 */
public class GirlException extends RuntimeException {

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
