package com.ajou.jinwoo.median;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LectureTuesdayFragment extends Fragment{

    TextView textViewB103;
    TextView textView415;
    TextView textView419;
    TextView textView420;
    TextView textView421;
    TextView textView422;
    TextView textViewB105;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture_tuesday, container, false);

        textViewB103 = (TextView) view.findViewById(R.id.textViewB103);
        textView415 = (TextView) view.findViewById(R.id.textView415);
        textView419 = (TextView) view.findViewById(R.id.textView419);
        textView420 = (TextView) view.findViewById(R.id.textView420);
        textView421 = (TextView) view.findViewById(R.id.textView421);
        textView422 = (TextView) view.findViewById(R.id.textView422);
        textViewB105 = (TextView) view.findViewById(R.id.textViewB105);

        setTextViewFont();

        return view;
    }

    private void setTextViewFont() {
        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "AritaThin.ttf");

        textViewB103.setTypeface(type);
        textView415.setTypeface(type);
        textView419.setTypeface(type);
        textView420.setTypeface(type);
        textView421.setTypeface(type);
        textView422.setTypeface(type);
        textViewB105.setTypeface(type);
    }
}
