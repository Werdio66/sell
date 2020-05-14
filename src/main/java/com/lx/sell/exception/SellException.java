package com.lx.sell.exception;

import com.lx.sell.enums.Result;

/**
 * 异常类
 *
 * @author Werdio丶
 * @since 2020-05-13 19:09:22
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(Result result) {
        super(result.getMsg());
        this.code = result.getCode();
    }

    public SellException(String msg){
        super(msg);
    }
}
