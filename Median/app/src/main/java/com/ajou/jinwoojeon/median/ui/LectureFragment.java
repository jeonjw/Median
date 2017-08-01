package com.ajou.jinwoojeon.median.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.OnSpinnerClickListener;

import java.util.Calendar;


public class LectureFragment extends Fragment {
    private FragmentManager fragmentManager;
    private int selectDay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lecture, container, false);
        setHasOptionsMenu(true);

        fragmentManager = getFragmentManager();
        selectDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;

        BaseLectureFragment baseLectureFragment = createLectureFragment(selectDay);
        fragmentManager.beginTransaction().replace(R.id.lecture_container, baseLectureFragment).commit();

        return view;
    }

    public BaseLectureFragment createLectureFragment(int position) {

        BaseLectureFragment baseLectureFragment;

        switch (position) {
            case 0:
                baseLectureFragment = new BaseLectureFragment.LectureMondayFragment();
                break;

            case 1:
                baseLectureFragment = new BaseLectureFragment.LectureTuesdayFragment();
                break;

            case 2:
                baseLectureFragment = new BaseLectureFragment.LectureWednesdayFragment();
                break;

            case 3:
                baseLectureFragment = new BaseLectureFragment.LectureThursdayFragment();
                break;

            case 4:
                baseLectureFragment = new BaseLectureFragment.LectureFridayFragment();
                break;

            default:
                return null;
        }

        baseLectureFragment.setOnSpinnerClickListener(new OnSpinnerClickListener() {
            @Override
            public void onCLick(int position) {
                selectDay = position;
                fragmentManager.beginTransaction().replace(R.id.lecture_container, createLectureFragment(position)).commit();
            }
        });

        Bundle args = new Bundle();
        args.putInt("SELECT_DAY", selectDay);
        baseLectureFragment.setArguments(args);

        return baseLectureFragment;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(false);
    }


}
