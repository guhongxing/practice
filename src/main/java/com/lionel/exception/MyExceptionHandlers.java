package com.lionel.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.lionel.base.base.ResponseBean;

/**
 * 异常统一处理
 * 
 * @author lionel
 *
 */
@RestControllerAdvice
public class MyExceptionHandlers {

	private static Logger logger = LoggerFactory.getLogger(MyExceptionHandlers.class);

	@ExceptionHandler({ ServiceExceptions.class })
	public ResponseEntity<Object> handlerApiException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ServiceExceptions apiException = (ServiceExceptions) ex;
        ResponseBean<?> responseBean = ResponseBean.failure(apiException.getErrorCode(), apiException.getMessage());
        responseBean.setErrCode(apiException.getErrorCode());
        return this.handleExceptionInternal(ex, responseBean, headers, HttpStatus.OK, request);
    }
	
	@ExceptionHandler({ SQLException.class })
	public ResponseEntity<Object> unFoundException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseBean<?> responseBean = ResponseBean.failure(500, ex.getCause().getMessage());
        return this.handleExceptionInternal(ex, responseBean, headers, HttpStatus.OK, request);
    }

    /**
     * 自定义异常返回格式
     *
     *
     * @param ex      the exception
     * @param body    the body for the response
     * @param headers the headers for the response
     * @param status  the response status
     * @param request the current request
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        if (body == null) {
            String message = ex.getMessage();
            if (message == null) {
                message = status.getReasonPhrase();
            }
            ResponseBean<?> responseBean = ResponseBean.failure(500, message);
            body = responseBean;
        }

        if (logger.isDebugEnabled()) {
            logger.error(ex.getMessage(), ex);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
