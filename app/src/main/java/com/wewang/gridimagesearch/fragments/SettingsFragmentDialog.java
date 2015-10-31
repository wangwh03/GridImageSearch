package com.wewang.gridimagesearch.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wewang.gridimagesearch.R;
import com.wewang.gridimagesearch.models.FilterSettings;

/**
 * Created by wewang on 10/29/15.
 */
public class SettingsFragmentDialog extends DialogFragment {
    private EditText etImageSize;
    private EditText etColorFilter;
    private EditText etImageType;
    private EditText etSiteFilter;
    private Button btnCancel;
    private Button btnSave;

    public interface  DismissDialogListener {
        void onFinishSettingDialog(FilterSettings filterSettings);
    }

    public SettingsFragmentDialog() { }

    public static SettingsFragmentDialog newInstance(FilterSettings filterSettings) {
        SettingsFragmentDialog dialog = new SettingsFragmentDialog();
        Bundle args = new Bundle();
        args.putSerializable("filterSettings", filterSettings);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        etColorFilter = (EditText) view.findViewById(R.id.etColorFilter);
        etImageType = (EditText) view.findViewById(R.id.etImageType);
        etSiteFilter = (EditText) view.findViewById(R.id.etSiteFilter);
        btnCancel = (Button) view.findViewById(R.id.btnCancelSettings);
        btnSave = (Button) view.findViewById(R.id.btnSaveSettings);

        btnSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        FilterSettings filterSettings =
                (FilterSettings) getArguments().getSerializable("filterSettings");
        etImageSize.setText(filterSettings.getImageSize());
        etColorFilter.setText(filterSettings.getColor());
        etImageType.setText(filterSettings.getImageType());
        etSiteFilter.setText(filterSettings.getSite());
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        Log.d("DEBUG", "dismissed for save");
        super.onDismiss(dialog);
        DismissDialogListener listener = (DismissDialogListener) getActivity();
        FilterSettings filterSettings = new FilterSettings(etImageSize.getText().toString(),
                etColorFilter.getText().toString(),
                etImageType.getText().toString(),
                etSiteFilter.getText().toString());
        listener.onFinishSettingDialog(filterSettings);
    }

}
