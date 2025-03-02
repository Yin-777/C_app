
package com.example.taobao.model.bean;

import java.util.List;

public class tabBean {

    private boolean success;
    private int code;
    private String message;
    private List<Data> data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

}