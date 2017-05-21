package com.ajou.jinwoo.median;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LectureMondayFragment extends Fragment{

    TextView textViewB103;
    TextView textView415;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture_monday, container, false);

        textViewB103 = (TextView) view.findViewById(R.id.textViewB103);
        textView415 = (TextView) view.findViewById(R.id.textView415);

        setTextViewFont();

        return view;
    }

    private void setTextViewFont() {
        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "AritaBold.ttf");

        textViewB103.setTypeface(type);
        textView415.setTypeface(type);
    }
}
