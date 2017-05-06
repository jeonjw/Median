package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ajou.jinwoo.median.model.StudentNotice;
import com.ajou.jinwoo.median.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StudentNoticeWriteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentsEditText;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_notice_write);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        titleEditText = (EditText) findViewById(R.id.student_notice_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.student_notice_contents_edit_text);
        ImageButton closeButton = (ImageButton) findViewById(R.id.notice_write_close_button);
        ImageButton writeButton = (ImageButton) findViewById(R.id.notice_write_finish);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(titleEditText.getText().toString())){
                    titleEditText.setError("제목을 입력하세요");
                    titleEditText.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(contentsEditText.getText().toString())){
                    contentsEditText.setError("내용을 입력하세요");
                    contentsEditText.requestFocus();
                    return;
                }
                String key = mDatabase.child("student_notice").push().getKey();
                StudentNotice studentNotice = new StudentNotice(User.getInstance().getUserName(),titleEditText.getText().toString(), contentsEditText.getText().toString());
                Map<String, Object> postValues = studentNotice.toMap();

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/student_notice/" + key, postValues);

                mDatabase.updateChildren(childUpdates);
                finish();
            }
        });

    }
}
