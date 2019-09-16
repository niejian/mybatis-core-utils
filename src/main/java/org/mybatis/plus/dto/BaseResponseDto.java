package org.mybatis.plus.dto;


import org.mybatis.plus.instance.CommonInstance;

import java.io.Serializable;

/**
 * @desc: cn.com.bluemoon.middleware.mybatis.dto.BaseResponseDto
 * @author: niejian9001@gmail.com
 * @date: 2019-09-11-10:52
 */

public class BaseResponseDto<T extends Object> implements Serializable {
    private Boolean success = CommonInstance.FAIL;
    private Integer responseCode = CommonInstance.FAIL_CODE;
    private String responseMsg = CommonInstance.FAIL_MSG;
    private T data;

    public BaseResponseDto success(Boolean success) {
        this.success = success;
        return this;
    }

    public BaseResponseDto responseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public BaseResponseDto responseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
        return this;
    }

    public BaseResponseDto data(T data) {
        this.data = data;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
