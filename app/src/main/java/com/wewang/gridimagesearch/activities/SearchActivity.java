package com.wewang.gridimagesearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wewang.gridimagesearch.R;
import com.wewang.gridimagesearch.adapters.ImageResultsAdapter;
import com.wewang.gridimagesearch.clients.GoogleImageSearchClient;
import com.wewang.gridimagesearch.fragments.SettingsFragmentDialog;
import com.wewang.gridimagesearch.listeners.EndlessScrollListener;
import com.wewang.gridimagesearch.models.FilterSettings;
import com.wewang.gridimagesearch.models.ImageResult;
import com.wewang.gridimagesearch.utils.ImageResultParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity implements SettingsFragmentDialog.DismissDialogListener {

    private GridView gvResults;
    private List<ImageResult> images;
    private ImageResultsAdapter imageResultsAdapter;
    private FilterSettings filterSettings;
    private GoogleImageSearchClient client = new GoogleImageSearchClient();
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        images = new ArrayList<>();
        imageResultsAdapter = new ImageResultsAdapter(this, images);
        gvResults.setAdapter(imageResultsAdapter);
    }

    private void setupViews() {
        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                ImageResult result = images.get(position);
                intent.putExtra("imageInfo", result);
                startActivity(intent);
            }
        });
        gvResults.setOnScrollListener(new EndlessScrollListener() {

            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                fetchImages(query, totalItemsCount);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.this.query = query;
                images.clear();
                fetchImages(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            launchSettingView();
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchSettingView() {
        FragmentManager fm = getSupportFragmentManager();
        SettingsFragmentDialog settingsFragmentDialog = SettingsFragmentDialog.newInstance(filterSettings);
        settingsFragmentDialog.show(fm, "fragment_advanced_settings");
    }

    public void customLoadMoreDataFromApi(int offset) {
        fetchImages(query, offset);
    }

    public void fetchImages(String query) {
        fetchImages(query, 0);
    }

    public void fetchImages(String query, int offset) {
        client.search(query, offset, filterSettings, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
                try {
                    JSONArray imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    imageResultsAdapter.addAll(ImageResultParser.parseFromJSONArray(imageResultsJson));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("DEBUG", "failed");
            }
        });
    }

    @Override
    public void onFinishSettingDialog(FilterSettings filterSettings) {
        this.filterSettings = filterSettings;
    }

}
