package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ajou.jinwoo.median.model.NotificationPostModel;
import com.ajou.jinwoo.median.model.StudentNoticeModel;


public class StudentNoticeWriteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentsEditText;
    private StudentNoticeModel studentNoticeModel;
    private String postKey;
    private boolean rewrite;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_notice_write);

        studentNoticeModel = new StudentNoticeModel();
        String reWriteTitle = null;
        String reWriteContents = null;

        if (getIntent().getExtras() != null) {
            reWriteTitle = getIntent().getExtras().getString("STUDENT_TITLE");
            reWriteContents = getIntent().getExtras().getString("STUDENT_CONTENTS");
            postKey = getIntent().getExtras().getString("STUDENT_NOTICE_CORRECT_POST_KEY");
        }

        titleEditText = (EditText) findViewById(R.id.student_notice_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.student_notice_contents_edit_text);
        ImageButton closeButton = (ImageButton) findViewById(R.id.notice_write_close_button);
        ImageButton writeButton = (ImageButton) findViewById(R.id.notice_write_finish);

        if (reWriteContents != null && reWriteTitle != null) {
            titleEditText.setText(reWriteTitle);
            contentsEditText.setText(reWriteContents);
            rewrite = true;
        }

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextInputError())
                    return;

                sendNotice();
                finish();
            }
        });

    }

    private boolean isTextInputError() {
        if (TextUtils.isEmpty(titleEditText.getText().toString())) {
            titleEditText.setError("제목을 입력하세요");
            titleEditText.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(contentsEditText.getText().toString())) {
            contentsEditText.setError("내용을 입력하세요");
            contentsEditText.requestFocus();
            return true;
        }

        return false;
    }

    public void sendNotice() {
        if (rewrite) {
            int commentCount = getIntent().getExtras().getInt("COMMENT_COUNT");
            studentNoticeModel.correctNotice(titleEditText.getText().toString(), contentsEditText.getText().toString(), postKey, commentCount);

        } else
            studentNoticeModel.writeNotice(titleEditText.getText().toString(), contentsEditText.getText().toString());
    }
}
