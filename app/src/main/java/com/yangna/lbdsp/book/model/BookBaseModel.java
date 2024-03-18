package com.yangna.lbdsp.book.model;


import com.yangna.lbdsp.common.base.BaseModel;

/**
 * Description: 基础实体类
 */
public class BookBaseModel extends BaseModel {
    private String message;
    private Integer code;
    private Boolean success;
    private BookBaseResult result=new BookBaseResult();

    private Long timestamp;

    public BookBaseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public BookBaseResult getResult() {
        return result;
    }

    public void setResult(BookBaseResult result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

