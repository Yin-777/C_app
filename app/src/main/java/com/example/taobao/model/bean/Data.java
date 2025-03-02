
package com.example.taobao.model.bean;


public class Data {

    private int id;
    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}