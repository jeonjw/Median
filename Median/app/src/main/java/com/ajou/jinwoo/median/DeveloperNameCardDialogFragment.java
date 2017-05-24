package com.ajou.jinwoo.median;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by samsung on 2017-05-24.
 */

public class DeveloperNameCardDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_developer_name_card,null);


        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }
}
