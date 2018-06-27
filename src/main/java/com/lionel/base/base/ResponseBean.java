package com.lionel.base.base;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.pagehelper.Page;
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseBean<T> implements Serializable {

    private Integer errCode = 0;
    private String message;
    /**
     * 当前第几页
     */
    private Integer pageNum;

    /**
     * 每页多少条
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 总页数
     */
    private Integer totalPage;

    private T data;

    private List<T> rows;

    public ResponseBean() {
    }

    public ResponseBean(T data) {
        this.data = data;
    }

    public ResponseBean(Page<?> page, T data) {
        this.data = data;
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
    }

    public ResponseBean(Page<T> page) {
        this.rows = page.getResult();
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.totalCount = page.getTotal();
        this.totalPage = page.getPages();
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<>(data);
    }

    public static <T> ResponseBean<T> success(Page<?> page, T data) {
        return new ResponseBean<>(page, data);
    }

    public static ResponseBean<?> success() {
        ResponseBean<?> responseBean = new ResponseBean<>();
        return responseBean;
    }


    public static ResponseBean<?> failure(Integer errCode, String message) {
        ResponseBean<?> responseBean = new ResponseBean<>();
        responseBean.setErrCode(errCode);
        responseBean.setMessage(message);
        return responseBean;
    }

    
    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public List<T> getRows() {
        return rows;
    }
}
