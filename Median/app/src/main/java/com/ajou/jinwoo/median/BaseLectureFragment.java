package com.ajou.jinwoo.median;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public abstract class BaseLectureFragment extends Fragment {

    TextView textViewB103;
    TextView textView415;
    TextView textView419;
    TextView textView420;
    TextView textView421;
    TextView textView422;
    TextView textView202;
    TextView textView511;
    TextView textViewA,textViewB,textViewC,textViewD,textViewE,textViewF,textViewG,textViewH;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getCurrentTabResourceId(), container, false);

        textViewB103 = (TextView) view.findViewById(R.id.textViewB103);
        textView415 = (TextView) view.findViewById(R.id.textView415);
        textView419 = (TextView) view.findViewById(R.id.textView419);
        textView420 = (TextView) view.findViewById(R.id.textView420);
        textView421 = (TextView) view.findViewById(R.id.textView421);
        textView422 = (TextView) view.findViewById(R.id.textView422);
//        textViewEx1 = (TextView) view.findViewById(R.id.textView202);
//        textViewEx2 = (TextView) view.findViewById(R.id.textView511);
//        textViewEx3 = (TextView) view.findViewById(R.id.textView511);
//        textViewEx4 = (TextView) view.findViewById(R.id.textView511);

        textViewA = (TextView) view.findViewById(R.id.timeA);
        textViewB = (TextView) view.findViewById(R.id.timeB);
        textViewC = (TextView) view.findViewById(R.id.timeC);
        textViewD = (TextView) view.findViewById(R.id.timeD);
        textViewE = (TextView) view.findViewById(R.id.timeE);
        textViewF = (TextView) view.findViewById(R.id.timeF);
        textViewG = (TextView) view.findViewById(R.id.timeG);
        textViewH = (TextView) view.findViewById(R.id.timeH);

        String strA = getString(R.string.a) + "\n \n" + getString(R.string.aTime);
        SpannableString spnA = new SpannableString(strA);
        spnA.setSpan(new RelativeSizeSpan(0.7f), 1, spnA.length(), 0);
        textViewA.setText(spnA);

        String strB = getString(R.string.b) + "\n \n" + getString(R.string.bTime);
        SpannableString spnB = new SpannableString(strB);
        spnB.setSpan(new RelativeSizeSpan(0.7f), 1, spnB.length(), 0);
        textViewB.setText(spnB);

        String strC = getString(R.string.c) + "\n \n" + getString(R.string.cTime);
        SpannableString spnC = new SpannableString(strC);
        spnC.setSpan(new RelativeSizeSpan(0.7f), 1, spnC.length(), 0);
        textViewC.setText(spnC);

        String strD = getString(R.string.d) + "\n \n" + getString(R.string.dTime);
        SpannableString spnD = new SpannableString(strD);
        spnD.setSpan(new RelativeSizeSpan(0.7f), 1, spnD.length(), 0);
        textViewD.setText(spnD);

        String strE = getString(R.string.e) + "\n \n" + getString(R.string.eTime);
        SpannableString spnE = new SpannableString(strE);
        spnE.setSpan(new RelativeSizeSpan(0.7f), 1, spnE.length(), 0);
        textViewE.setText(spnE);

        String strF = getString(R.string.f) + "\n \n" + getString(R.string.fTime);
        SpannableString spnF = new SpannableString(strF);
        spnF.setSpan(new RelativeSizeSpan(0.7f), 1, spnF.length(), 0);
        textViewF.setText(spnF);

        String strG = getString(R.string.g) + "\n \n" + getString(R.string.gTime);
        SpannableString spnG = new SpannableString(strG);
        spnG.setSpan(new RelativeSizeSpan(0.7f), 1, spnG.length(), 0);
        textViewG.setText(spnG);

        String strH = getString(R.string.h) + "\n \n" + getString(R.string.hTime);
        SpannableString spnH = new SpannableString(strH);
        spnH.setSpan(new RelativeSizeSpan(0.7f), 1, spnH.length(), 0);
        textViewH.setText(spnH);

        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);

        checkCurrentTime((currentHour * 60) + currentMinute);
        setTextViewFont();

        return view;
    }

    private void setTextViewFont() {
        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "Trebuchet MS.ttf");

//        textViewB103.setTypeface(type);
//        textView415.setTypeface(type);
//        textView419.setTypeface(type);
//        textView420.setTypeface(type);
//        textView421.setTypeface(type);
//        textView422.setTypeface(type);
//        textView202.setTypeface(type);
//        textView511.setTypeface(type);
    }

    private void checkCurrentTime(int time) {
        if (time < 615) {//A
            textViewA.setTextColor(Color.RED);
        } else if (time < 705) {//B
            textViewB.setTextColor(Color.RED);
        } else if (time < 795) {//C
            textViewC.setTextColor(Color.RED);
        } else if (time < 885) {//D
            textViewD.setTextColor(Color.RED);
        } else if (time < 975) {//E
            textViewE.setTextColor(Color.RED);
        } else if (time < 1065) {//F
            textViewF.setTextColor(Color.RED);
        } else if (time < 1155) {//G
            textViewG.setTextColor(Color.RED);
        } else if (time < 1245) {//H
            textViewH.setTextColor(Color.RED);
        }

    }

    public abstract int getCurrentTabResourceId();

}
