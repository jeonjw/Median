package com.ajou.jinwoo.median;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by samsung on 2017-05-23.
 */

public class CustomDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_developer,null);
        TextView developerTextView1 = (TextView) view.findViewById(R.id.developer_text_view1);
        TextView developerTextView2 = (TextView) view.findViewById(R.id.developer_text_view2);

        developerTextView1.setText("전진우 Jeon Jin Woo");
        developerTextView2.setText("이도원 Lee Do Won");

        return new AlertDialog.Builder(getActivity()).setView(view).setNegativeButton("취소",null).setPositiveButton("예약",null).show();
    }
}
