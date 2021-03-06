package com.wewang.gridimagesearch.clients;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wewang.gridimagesearch.models.FilterSettings;

/**
 * Created by wewang on 10/29/15.
 */
public class GoogleImageSearchClient {

    private final AsyncHttpClient client = new AsyncHttpClient();
    private final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0";
    private final int LIMIT = 8;
    public void search(String query, int offset,
                       FilterSettings filterSettings, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("q", query);
        params.put("rsz", LIMIT);
        params.put("start", offset);

        if (filterSettings != null) {
            if (filterSettings.getColor() != null) {
                params.put("imgcolor", filterSettings.getColor());
            }
            if (filterSettings.getImageSize() != null) {
                params.put("imgsz", filterSettings.getImageSize());
            }
            if (filterSettings.getImageType() != null) {
                params.put("imgtype", filterSettings.getImageType());
            }
            if (filterSettings.getSite() != null) {
                params.put("as_sitesearch", filterSettings.getSite());
            }
        }

        client.get(BASE_URL, params, handler);
    }
}
