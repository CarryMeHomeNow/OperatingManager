package com.tcl.uf.advert.exception;

/**
 * JMException
 *
 */
public class JMailException extends RuntimeException {

    private int errCode = -1;
    private String msg;
    public JMailException() {
        super();
    }

    public JMailException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public JMailException(int errCode) {
        this.errCode = errCode;
    }

    public JMailException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

}