package com.wewang.gridimagesearch.fragments;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.wewang.gridimagesearch.R;

/**
 * Created by wewang on 10/29/15.
 */
public class AdvancedFragmentDialog extends DialogFragment {
    private EditText etImageSize;

    public AdvancedFragmentDialog() { }

    public static AdvancedFragmentDialog newInstance() {
        AdvancedFragmentDialog frag = new AdvancedFragmentDialog();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advanced_settings, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        etImageSize = (EditText) view.findViewById(R.id.etImageSize);
        // Show soft keyboard automatically and request focus to field
        etImageSize.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
