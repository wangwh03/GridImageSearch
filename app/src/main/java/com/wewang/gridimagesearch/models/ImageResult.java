package com.wewang.gridimagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wewang on 10/28/15.
 */
public class ImageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullUrl;
    private String thumbUrl;
    private String title;

    public ImageResult(String fullUrl, String thumbUrl, String title) {
        this.fullUrl = fullUrl;
        this.thumbUrl = thumbUrl;
        this.title = title;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getTitle() {
        return title;
    }
}
