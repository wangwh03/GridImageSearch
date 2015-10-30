package com.wewang.gridimagesearch.models;

/**
 * Created by wewang on 10/30/15.
 */
public class FilterSettings {
    private String imageSize;
    private String color;
    private String imageType;
    private String site;

    public FilterSettings(String site, String imageSize, String color, String imageType) {
        this.site = site;
        this.imageSize = imageSize;
        this.color = color;
        this.imageType = imageType;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
