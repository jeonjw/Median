package com.ajou.jinwoo.median;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class LectureMondayFragment extends Fragment{

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
        View view = inflater.inflate(R.layout.fragment_lecture_monday, container, false);

        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        Toast.makeText(getContext(),""+currentHour+" "+currentMinute+" "+currentDay,Toast.LENGTH_SHORT).show();

        textViewB103 = (TextView) view.findViewById(R.id.textViewB103);
        textView415 = (TextView) view.findViewById(R.id.textView415);
        textView419 = (TextView) view.findViewById(R.id.textView419);
        textView420 = (TextView) view.findViewById(R.id.textView420);
        textView421 = (TextView) view.findViewById(R.id.textView421);
        textView422 = (TextView) view.findViewById(R.id.textView422);
        textView202 = (TextView) view.findViewById(R.id.textView202);
        textView511 = (TextView) view.findViewById(R.id.textView511);

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

//        final SpannableString timeA = new SpannableString("A 9:00~10:15");
//        final SpannableString timeB = new SpannableString("B 9:00~10:15");
//        final SpannableString timeC = new SpannableString("C 9:00~10:15");
//        final SpannableString timeD = new SpannableString("D 9:00~10:15");
//        final SpannableString timeE = new SpannableString("E 9:00~10:15");
//        final SpannableString timeF = new SpannableString("F 9:00~10:15");
//        final SpannableString timeG = new SpannableString("G 9:00~10:15");
//        final SpannableString timeH = new SpannableString("H 9:00~10:15");


//        timeA.setSpan(new RelativeSizeSpan(2.0f), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        timeA.setSpan(new RelativeSizeSpan(1.5f), 5, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        A.setText(Html.fromHtml(String.format("<font size=\"10\">A</font><font size=\"15\">%s</font>", timeA)));
//        timeA = (TextView) view.findViewById(R.id.timeA);
//        timeB = (TextView) view.findViewById(R.id.timeB);
//        timeC = (TextView) view.findViewById(R.id.timeC);
//        timeD = (TextView) view.findViewById(R.id.timeD);
//        timeE = (TextView) view.findViewById(R.id.timeE);
//        timeF = (TextView) view.findViewById(R.id.timeF);
//        timeG = (TextView) view.findViewById(R.id.timeG);
//        timeH = (TextView) view.findViewById(R.id.timeH);

        setTextViewFont();

        return view;
    }

    private void setTextViewFont() {
        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "Trebuchet MS.ttf");

        textViewB103.setTypeface(type);
        textView415.setTypeface(type);
        textView419.setTypeface(type);
        textView420.setTypeface(type);
        textView421.setTypeface(type);
        textView422.setTypeface(type);
        textView202.setTypeface(type);
        textView511.setTypeface(type);
    }
}
