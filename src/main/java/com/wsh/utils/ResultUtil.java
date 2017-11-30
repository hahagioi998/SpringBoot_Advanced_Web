package com.wsh.utils;

import com.wsh.constrant.GlobalConstrant;
import com.wsh.domain.Result;

/**
 * Created by wsh on 2017/11/30.
 * 组装错误消息工具类
 */
public class ResultUtil {

    /**
     * 请求成功
     *
     * @param object 请求成功返回的对象
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(GlobalConstrant.successCode);
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 请求失败
     *
     * @param code    状态码
     * @param message 提示消息
     * @return
     */
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
