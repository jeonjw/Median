package com.ajou.jinwoo.median.ui;


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

import com.ajou.jinwoo.median.R;

import java.util.Calendar;

public abstract class BaseLectureFragment extends Fragment {

    private TextView textViewB103;
    private TextView textView415;
    private TextView textView419;
    private TextView textView420;
    private TextView textView421;
    private TextView textView422;
    private TextView textViewEx1;
    private TextView textViewEx2;
    private TextView textViewEx3;
    private TextView textViewA, textViewB, textViewC, textViewD, textViewE, textViewF, textViewG, textViewH;

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
        textViewEx1 = (TextView) view.findViewById(R.id.textViewEx1);
        textViewEx2 = (TextView) view.findViewById(R.id.textViewEx2);
        textViewEx3 = (TextView) view.findViewById(R.id.textViewEx3);

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

        textViewA.setTypeface(type);
        textViewB.setTypeface(type);
        textViewC.setTypeface(type);
        textViewD.setTypeface(type);
        textViewE.setTypeface(type);
        textViewF.setTypeface(type);
        textViewG.setTypeface(type);
        textViewH.setTypeface(type);
        textViewB103.setTypeface(type);
        textView415.setTypeface(type);
        textView419.setTypeface(type);
        textView420.setTypeface(type);
        textView421.setTypeface(type);
        textView422.setTypeface(type);
        textViewEx1.setTypeface(type);
        if (textViewEx2 != null)
            textViewEx2.setTypeface(type);
        if (textViewEx3 != null)
            textViewEx3.setTypeface(type);
    }

    private void checkCurrentTime(int time) {
        if (time < 615 && time > 540) {//A
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

    public static class LectureMondayFragment extends BaseLectureFragment {

        @Override
        public int getCurrentTabResourceId() {
            return R.layout.fragment_lecture_monday;
        }

    }

    public static class LectureFridayFragment extends BaseLectureFragment {

        @Override
        public int getCurrentTabResourceId() {
            return R.layout.fragment_lecture_friday;
        }
    }

    public static class LectureThursdayFragment extends BaseLectureFragment {

        @Override
        public int getCurrentTabResourceId() {
            return R.layout.fragment_lecture_thursday;
        }
    }

    public static class LectureTuesdayFragment extends BaseLectureFragment {

        @Override
        public int getCurrentTabResourceId() {
            return R.layout.fragment_lecture_tuesday;
        }
    }

    public static class LectureWednesdayFragment extends BaseLectureFragment {

        @Override
        public int getCurrentTabResourceId() {
            return R.layout.fragment_lecture_wednesday;
        }
    }
}
