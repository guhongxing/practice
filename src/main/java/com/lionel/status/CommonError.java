package com.lionel.status;

/**
 * @author Li Shijie
 * @Description: 公共返回错误类型
 * @date 2018/3/3下午4:56
 * //
 */
public enum CommonError implements ErrorStatus {
    /**
     * 缺少必要请求参数
     */
    MISSING_PARAMETERS(400, "missing_required_parameters"),

    /**
     * 非法请求参数
     */
    ILLEGAL_PARAMETER(401, "illegal_request_parameter"),

    /**
     * 请求路径错误
     */
    WRONG_URL(404, "WRONG_URL"),

    /**
     * 服务器内部异常
     */
    INTERNAL_EXCEPTION(500, "server_internal_exception"),

    /**
     * 请求方法不支持
     */
    NOT_SUPPORTED(415, "request_method_not_supported"),

    /**
     * 商品服务调用失败
     */
    PRODUCT_SERVICE_ERROR(600, "product_service_error"),

    /**
     * 认证服务调用失败
     */
    AUTHENTICATION_SERVICE_ERROR(700,"authentication_service_erro");

    private Integer errCode;
    private String errDesc;

    public Integer getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    CommonError(Integer errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }
}
