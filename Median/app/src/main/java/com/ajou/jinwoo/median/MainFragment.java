package com.ajou.jinwoo.median;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
    private Button mNoticeButton;
    private Button mAlbumButton;
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
        mAlbumButton = (Button) view.findViewById(R.id.album_button);
        mInfoButton = (Button) view.findViewById(R.id.info_button);
        mBoardButton = (Button) view.findViewById(R.id.board_button);
        mLectureButton = (Button) view.findViewById(R.id.lecture_button);
        mSettingButton = (Button) view.findViewById(R.id.setting_button);

        mNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        mBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardActivity.class);
                startActivity(intent);
            }
        });

        mLectureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LectureActivity.class);
                startActivity(intent);
            }
        });

        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });

        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        mAlbumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AlbumActivity.class);
                startActivity(intent);
            }
        });

        setButtonFont();

        return view;
    }

    private void setButtonFont() {
        Typeface type = Typeface
                .createFromAsset(getActivity().getAssets(), "Impact.ttf");

        mNoticeButton.setTypeface(type);
        mAlbumButton.setTypeface(type);
        mInfoButton.setTypeface(type);
        mBoardButton.setTypeface(type);
        mLectureButton.setTypeface(type);
        mSettingButton.setTypeface(type);
    }
}
