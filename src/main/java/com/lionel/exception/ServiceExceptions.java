package com.lionel.exception;

/**
 * 异常类
 * @author lionel
 *
 */
public class ServiceExceptions extends RuntimeException {
	
	private Integer errorCode;

    public ServiceExceptions() {
        super();
    }

    public ServiceExceptions(String message) {
        super(message);
    }

    public ServiceExceptions(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    //public ServiceExceptions(ErrorStatus errorStatus) { this(errorStatus.getErrCode(), errorStatus.getErrDesc()); }

    public ServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExceptions(Throwable cause) {
        super(cause);
    }

    protected ServiceExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
