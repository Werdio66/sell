package com.lx.sell.handler;

import com.lx.sell.exception.SellException;
import com.lx.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author Werdio丶
 * @since 2020-05-26 09:37:45
 */
@Slf4j
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO<?> handler(SellException e){
        log.error("【异常处理】");
        return ResultVO.error(e.getCode(), e.getMessage());
    }
}
