package com.wewang.gridimagesearch.clients;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by wewang on 10/29/15.
 */
public class GoogleImageSearchClient {

    private final AsyncHttpClient client = new AsyncHttpClient();
    private final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0";

    public void search(String query, int rsz, AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("q", query);
        params.put("rsz", rsz);
        client.get(BASE_URL, params, handler);
    }
}
