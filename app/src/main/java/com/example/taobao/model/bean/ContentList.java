
package com.example.taobao.model.bean;

;


public class ContentList {
    @Override
    public String toString() {
        return "ContentList{" +
                "cover='" + cover + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", couponAmount=" + couponAmount +
                ", zkFinalPrice='" + zkFinalPrice + '\'' +
                ", couponShareUrl='" + couponShareUrl + '\'' +
                ", couponTotalCount=" + couponTotalCount +
                ", couponRemainCount=" + couponRemainCount +
                ", sellCount=" + sellCount +
                ", justPrice='" + justPrice + '\'' +
                '}';
    }

    private String cover;
    private String source;
    private String title;
    private double couponAmount;
    private String zkFinalPrice;
    private String couponShareUrl;
    private int couponTotalCount;
    private int couponRemainCount;
    private int sellCount;
    private String justPrice;

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCouponAmount(int couponAmount) {
        this.couponAmount = couponAmount;
    }

    public double getCouponAmount() {
        return couponAmount;
    }

    public void setZkFinalPrice(String zkFinalPrice) {
        this.zkFinalPrice = zkFinalPrice;
    }

    public String getZkFinalPrice() {
        return zkFinalPrice;
    }

    public void setCouponShareUrl(String couponShareUrl) {
        this.couponShareUrl = couponShareUrl;
    }

    public String getCouponShareUrl() {
        return couponShareUrl;
    }

    public void setCouponTotalCount(int couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public int getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponRemainCount(int couponRemainCount) {
        this.couponRemainCount = couponRemainCount;
    }

    public int getCouponRemainCount() {
        return couponRemainCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setJustPrice(String justPrice) {
        this.justPrice = justPrice;
    }

    public String getJustPrice() {
        return justPrice;
    }

}