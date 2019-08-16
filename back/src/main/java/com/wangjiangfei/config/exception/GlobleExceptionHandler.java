package com.wangjiangfei.config.exception;

import com.wangjiangfei.domain.ErrorCode;
import com.wangjiangfei.domain.ServiceVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @author wangjiangfei
 */

@ControllerAdvice
public class GlobleExceptionHandler {

//	@ExceptionHandler(SQLException.class)
//    @ResponseBody
//    public ServiceVO handleSQLException() {
//        return new ServiceVO(ErrorCode.SQL_ERROR_CODE, ErrorCode.SQL_ERROR_MESS);
//    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ServiceVO handleCastException() {
        return new ServiceVO(ErrorCode.PARA_TYPE_ERROR_CODE, ErrorCode.PARA_TYPE_ERROR_MESS);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ServiceVO handleNullPoint() {
        return new ServiceVO(ErrorCode.NULL_POINTER_CODE, ErrorCode.NULL_POINTER_MESS);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ServiceVO formatExp(){
        return new ServiceVO(ErrorCode.PARA_TYPE_ERROR_CODE, ErrorCode.PARA_TYPE_ERROR_MESS);
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseBody
    public ServiceVO timeoutException(){
        return new ServiceVO(ErrorCode.TIME_OUT_CODE, ErrorCode.TIME_OUT_MESS);
    }

    @ExceptionHandler(NoTokenException.class)
    @ResponseBody
    public ServiceVO noTokenException(){
        return new ServiceVO(ErrorCode.NO_TOKEN_CODE, ErrorCode.NO_TOKEN_MESS);
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public ServiceVO tokenExpiredException(){
        return new ServiceVO(ErrorCode.LOGIN_EXPIRE_CODE, ErrorCode.LOGIN_EXPIRE_MESS);
    }

    @ExceptionHandler(NoPermissionException.class)
    @ResponseBody
    public ServiceVO noPermissionException(){
        return new ServiceVO(ErrorCode.NO_AUTH_CODE, ErrorCode.NO_AUTH_MESS);
    }

}
