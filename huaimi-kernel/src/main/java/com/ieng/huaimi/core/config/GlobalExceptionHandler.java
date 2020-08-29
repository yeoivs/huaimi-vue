package com.ieng.huaimi.core.config;

import com.ieng.huaimi.common.domain.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * <p>
 *     异常处理类
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBody validationParamsException(MethodArgumentNotValidException e){
        LOGGER.warn(e.getMessage(), e);
        BindingResult result = e.getBindingResult();
        String message = "";
        if(result.hasErrors()){
            List<ObjectError> objectErrorList = result.getAllErrors();
            if(objectErrorList.size() > 0){
                FieldError fieldError = (FieldError) objectErrorList.get(0);
                message = fieldError.getDefaultMessage();
            }
        }
        return ResultBody.failed("".equals(message) ? "请填写正确信息" : message);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultBody parameterTypeException(HttpMessageConversionException e){
        LOGGER.warn(e.getMessage(), e);
        return ResultBody.failed("参数类型转换错误");
    }


}
