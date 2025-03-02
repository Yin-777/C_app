package com.example.taobao.model.bean;

;

public class tb_root {

    private boolean success;
    private int code;
    private String message;
    private tb_Adata data;

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

    public void setData(tb_Adata data) {
        this.data = data;
    }

    public tb_Adata getData() {
        return data;
    }

}