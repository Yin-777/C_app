/**
 * Copyright 2024 bejson.com
 */
package com.example.taobao.model.bean;

;

/**
 * Auto-generated: 2024-11-18 19:57:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class HomePagerContent {
    @Override
    public String toString() {
        return "HomePagerContent{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    private boolean success;
    private int code;
    private String msg;
    private ContentData data;

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

    public void setData(ContentData data) {
        this.data = data;
    }

    public ContentData getData() {
        return data;
    }

}