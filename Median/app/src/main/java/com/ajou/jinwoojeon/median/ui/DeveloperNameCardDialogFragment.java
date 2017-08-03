package com.ajou.jinwoojeon.median.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ajou.jinwoojeon.median.R;

public class DeveloperNameCardDialogFragment extends android.support.v4.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_developer_name_card,null);

        final Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Button closeButton = view.findViewById(R.id.developer_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }
}
