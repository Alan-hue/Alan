package com.xdclass.online_xdclass.exception;


import com.xdclass.online_xdclass.utils.JsonData;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e){
        /**
         * instanceof 是 Java 的保留关键字 它的作用是测试它左边的对象是否是它右边的类的实例，
         * 返回 boolean 的数据类型。
         */
         if (e instanceof XDException){
             logger.error("【系统异常】{} ",e);
             XDException xdException = (XDException) e;
             return JsonData.buildError(xdException.getCode(), xdException.getMsg());
         }
         else {
             return JsonData.buildError("全局异常，未知错误");
         }
    }
}
