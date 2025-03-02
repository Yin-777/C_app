package com.example.taobao.model.bean;

;

public class tb_response {

    private tb_data data;
    private String request_id;

    public void setData(tb_data data) {
        this.data = data;
    }

    public tb_data getData() {
        return data;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getRequest_id() {
        return request_id;
    }

}