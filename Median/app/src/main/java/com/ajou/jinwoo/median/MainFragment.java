package com.ajou.jinwoo.median;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
    private Button mNoticeButton;
    private Button mCalendarButton;
    private Button mInfoButton;
    private Button mBoardButton;
    private Button mLectureButton;
    private Button mSettingButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setToolbarTitle("Median");

        mNoticeButton = (Button) view.findViewById(R.id.notice_button);
        mCalendarButton = (Button) view.findViewById(R.id.calendar_button);
        mInfoButton = (Button) view.findViewById(R.id.info_button);
        mBoardButton = (Button) view.findViewById(R.id.board_button);
        mLectureButton = (Button) view.findViewById(R.id.lecture_button);
        mSettingButton = (Button) view.findViewById(R.id.setting_button);

        mNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment noticeFragment = new NoticeFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, noticeFragment).addToBackStack(null).commit();
            }
        });

        mBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment boardFragment = new BoardFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, boardFragment).addToBackStack(null).commit();
            }
        });

        mLectureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment lectureFragment = new LectureFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, lectureFragment).addToBackStack(null).commit();
            }
        });

        setButtonFont();

        return view;
    }


    private void setButtonFont() {
        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "Impact.ttf");

        mNoticeButton.setTypeface(type);
        mCalendarButton.setTypeface(type);
        mInfoButton.setTypeface(type);
        mBoardButton.setTypeface(type);
        mLectureButton.setTypeface(type);
        mSettingButton.setTypeface(type);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.menu_notice_write).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);

    }

}
