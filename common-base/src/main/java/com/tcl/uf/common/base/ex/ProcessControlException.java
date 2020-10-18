package com.tcl.uf.common.base.ex;

/**
 * @author youyun.xu
 * @ClassName: ProcessControlException
 * @Description: 用户流程控制异常
 * @date 2020/8/14 13:53
 */
public class ProcessControlException extends Exception{

    public ProcessControlException() {
    }

    public ProcessControlException(String message) {
        super(message);
    }

    public ProcessControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessControlException(Throwable cause) {
        super(cause);
    }

    public ProcessControlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
