package com.wewang.gridimagesearch.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.wewang.gridimagesearch.R;
import com.wewang.gridimagesearch.fragments.SettingsFragmentDialog;

public class AdvancedSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_advanced_settings);
    }

    private void showSettingsDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SettingsFragmentDialog advancedFragmentDialog = SettingsFragmentDialog.newInstance();
        advancedFragmentDialog.show(fm, "fragment_advanced_settings");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
