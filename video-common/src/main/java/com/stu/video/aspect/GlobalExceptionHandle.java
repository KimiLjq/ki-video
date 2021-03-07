package com.stu.video.aspect;

import com.stu.video.rest.Rest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: kimijiaqili
 * @CreateDate: 2021/3/2 16:05
 * @Version: 1.0
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(value = OutputException.class)
    @ResponseBody
    public Rest<String> handleOutputException(OutputException e) {
        return new Rest<>(e.getRestCode(), e.getMsg(), null);
    }
}
