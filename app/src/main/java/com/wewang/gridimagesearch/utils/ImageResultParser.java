package com.wewang.gridimagesearch.utils;

import com.wewang.gridimagesearch.models.ImageResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wewang on 10/28/15.
 */
public final class ImageResultParser {

    public static ImageResult parseFromGoogleAPI(JSONObject json) throws JSONException {
        return new ImageResult(json.getString("url"), json.getString("tbUrl"), json.getString("title"));
    }

    public static ArrayList<ImageResult> parseFromJSONArray(JSONArray array) throws JSONException {
        ArrayList<ImageResult> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            results.add(parseFromGoogleAPI(array.getJSONObject(i)));
        }
        return results;
    }

}
