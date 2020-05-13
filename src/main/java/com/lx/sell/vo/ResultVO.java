package com.lx.sell.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultVO<T> {

    /**
     *  返回状态
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     *  返回对象信息
     */
    private T data;


    public static ResultVO<?> ok(String msg, Object data){
        return new ResultVO<>(0, msg, data);
    }

    public static ResultVO<?> ok(String msg){
        return new ResultVO<>(0, msg, null);
    }

    public static ResultVO<?> ok(Object data){
        return new ResultVO<>(0, "", data);
    }

    public static ResultVO<?> error(String msg, Object data){
        return new ResultVO<>(1, msg, data);
    }

    public static ResultVO<?> error(String msg){
        return new ResultVO<>(1, msg, null);
    }

    public static ResultVO<?> error(Object data){
        return new ResultVO<>(1, "", data);
    }

}
