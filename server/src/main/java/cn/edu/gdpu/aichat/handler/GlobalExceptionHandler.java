package cn.edu.gdpu.aichat.handler;

import cn.edu.gdpu.aichat.constant.MessageConstant;
import cn.edu.gdpu.aichat.exception.BaseException;
import cn.edu.gdpu.aichat.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

//    /**
//     * 处理sql异常
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler
//    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
//        //Duplicate entry 'zhangsan' for key 'idx_username'
//        String message = ex.getMessage();
//
//        if (message.contains("Duplicate entry")) {
//            String[] split = message.split(" ");
//            String username = split[2];
//            String msg = username + MessageConstant.ALREADY_EXISTS;
//            return Result.error(msg);
//        } else {
//            return Result.error(MessageConstant.UNKNOWN_ERROR);
//        }
//    }
}