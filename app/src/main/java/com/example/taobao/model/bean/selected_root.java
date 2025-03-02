
package com.example.taobao.model.bean;


public class selected_root {

    private boolean success;
    private int code;
    private String msg;
    private selected_Data data;

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

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(selected_Data data) {
        this.data = data;
    }

    public selected_Data getData() {
        return data;
    }

}