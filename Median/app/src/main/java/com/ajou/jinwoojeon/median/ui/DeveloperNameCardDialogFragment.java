package com.ajou.jinwoojeon.median.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.ajou.jinwoojeon.median.R;

public class DeveloperNameCardDialogFragment extends android.support.v4.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_developer_name_card,null);
        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }
}
