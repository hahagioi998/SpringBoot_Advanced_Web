package com.wsh.handle;

import com.wsh.domain.Result;
import com.wsh.enums.ResultEnum;
import com.wsh.exception.GirlException;
import com.wsh.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wsh on 2017/11/30.
 * 异常捕获 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        } else {
            logger.error("【系统异常】",e);
            GirlException girlException = new GirlException(ResultEnum.UNKNOWN_ERROR);
            return ResultUtil.error(girlException.getCode(),girlException.getMessage());
        }
    }

}
