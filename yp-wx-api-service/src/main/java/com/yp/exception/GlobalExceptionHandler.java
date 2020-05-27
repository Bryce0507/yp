package com.yp.exception;

import com.yp.common.utils.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ObjectError error;

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public R validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        if (bindResult != null && bindResult.hasErrors()) {
            FieldError objectError= (FieldError) bindResult.getAllErrors().get(0);
           return R.error("入参校验失败:字段["+ objectError.getField()+"]错误："+objectError.getDefaultMessage());
        }else {
        	return R.error( "系统繁忙");
        }
    }
}
