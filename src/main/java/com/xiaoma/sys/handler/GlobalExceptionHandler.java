package com.xiaoma.sys.handler;

import com.xiaoma.sys.exception.MyException;
import com.xiaoma.sys.utils.CodeEnum;
import com.xiaoma.sys.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.net.BindException;

/**
 * 全局异常处理
 *
 * @author mjh
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(MyException.class)
    public ResultInfo<Object> myException(MyException ex) {
        log.info("自定义异常 , 错误信息: {}", ex.getMsg());
        return new ResultInfo<>(ex.getCode(), ex.getMsg());
    }

    /**
     * Java安全管理器异常
     */
    @ExceptionHandler(IllegalAccessException.class)
    public ResultInfo<Object> illegalAccessException(IllegalAccessException ex) {
        log.info("Java安全管理器异常 ,错误信息: {}", ex.getMessage());
        return new ResultInfo<>(CodeEnum.SECURITY_MANAGEMENT_ERROR.getCode(),
                CodeEnum.SECURITY_MANAGEMENT_ERROR.getDescription());
    }

    /**
     * 传入的参数为空异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultInfo<Object> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.info("参数为空异常 ,错误信息: {}", ex.getMessage());
        return new ResultInfo<>(CodeEnum.EMPTY_ERROR.getCode(), CodeEnum.EMPTY_ERROR.getDescription());
    }

    /**
     * 传入的参数校验异常抛出
     */
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResultInfo<Object> validateException(Exception ex) {
        log.info("参数校验异常 ,错误类型: {} ,错误信息: {}", ex.getClass().getName(), ex.getMessage());
        return new ResultInfo<>
                (CodeEnum.SYS_ERROR_D0101.getCode(),
                        CodeEnum.SYS_ERROR_D0101.getDescription());
    }


    /**
     * 所有的异常处理
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo<Object> exception(Exception ex) {
        log.info("未知异常 ,错误类型: {} ,错误信息: {}", ex.getClass().getName(), ex.getMessage());
        return new ResultInfo<>(CodeEnum.UNKNOWN_ERROR.getCode(), CodeEnum.UNKNOWN_ERROR.getDescription());
    }
}
