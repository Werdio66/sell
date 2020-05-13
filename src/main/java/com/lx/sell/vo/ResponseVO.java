package com.lx.sell.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Data
public class ResponseVO<T> {

    /**
     *  返回状态
     */
    private boolean ret;

    /**
     * 提示信息
     */
    private String msg;

    /**
     *  返回对象信息
     */
    private T data;


    public static ResponseVO<?> ok(String msg, Object data){
        return new ResponseVO<>(true, msg, data);
    }

    public static ResponseVO<?> ok(String msg){
        return new ResponseVO<>(true, msg, null);
    }

    public static ResponseVO<?> ok(Object data){
        return new ResponseVO<>(true, "", data);
    }

    public static ResponseVO<?> error(String msg, Object data){
        return new ResponseVO<>(false, msg, data);
    }

    public static ResponseVO<?> error(String msg){
        return new ResponseVO<>(false, msg, null);
    }

    public static ResponseVO<?> error(Object data){
        return new ResponseVO<>(false, "", data);
    }

}
