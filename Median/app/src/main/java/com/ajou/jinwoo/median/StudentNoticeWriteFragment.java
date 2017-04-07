package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentNoticeWriteFragment extends Fragment {
    private EditText titleEditText;
    private EditText contentsEditText;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_notice_write, container, false);

        setHasOptionsMenu(true);
        final FragmentManager fragmentManager = getFragmentManager();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        titleEditText = (EditText) view.findViewById(R.id.student_notice_title_edit_text);
        contentsEditText = (EditText) view.findViewById(R.id.student_notice_contents_edit_text);
        ImageButton closeButton = (ImageButton) view.findViewById(R.id.notice_write_close_button);
        ImageButton writeButton = (ImageButton) view.findViewById(R.id.notice_write_finish);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().remove(StudentNoticeWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentNotice studentNotice = new StudentNotice(titleEditText.getText().toString(), contentsEditText.getText().toString());
                mDatabase.child("student_notice").push().setValue(studentNotice);

                fragmentManager.beginTransaction().remove(StudentNoticeWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.menu_write).setVisible(false);
        menu.findItem(R.id.menu_search).setVisible(false);
    }
}
