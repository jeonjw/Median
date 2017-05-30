package com.ajou.jinwoo.median.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.PostModel;
import com.ajou.jinwoo.median.valueObject.User;

public class BoardWriteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentsEditText;
    private Spinner board_spinner;
    private int currentPosition;
    private boolean rewrite;
    private String postKey;
    private PostModel postModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        currentPosition = getIntent().getExtras().getInt("CURRENT_BOARD_TAB");
        String reWriteTitle = getIntent().getExtras().getString("BOARD_TITLE");
        String reWriteContents = getIntent().getExtras().getString("BOARD_CONTENTS");
        postKey = getIntent().getExtras().getString("CORRECT_POST_KEY");

        titleEditText = (EditText) findViewById(R.id.board_write_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.board_write_content_edit_text);
        ImageButton writeButton = (ImageButton) findViewById(R.id.board_write_finish);
        ImageButton closeButton = (ImageButton) findViewById(R.id.board_write_close_button);
        board_spinner = (Spinner) findViewById(R.id.board_spinner);

        board_spinner.post(new Runnable() {
            @Override
            public void run() {
                board_spinner.setSelection(currentPosition);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BoardWriteActivity.this, R.array.board_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        board_spinner.setAdapter(adapter);

        postModel = new PostModel((String) board_spinner.getSelectedItem());


        if (reWriteContents != null && reWriteTitle != null) {
            titleEditText.setText(reWriteTitle);
            contentsEditText.setText(reWriteContents);
            board_spinner.setVisibility(View.GONE);
            rewrite = true;

        }


        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextInputError())
                    return;
                String userName = getPostAuthorName();
                sendPost(userName);
                finish();
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private boolean isTextInputError() {
        if (TextUtils.isEmpty(titleEditText.getText().toString())) {
            titleEditText.requestFocus();
            titleEditText.setError("제목을 입력해주세요\n");
            return true;
        } else if (TextUtils.isEmpty(contentsEditText.getText().toString())) {
            contentsEditText.setError("내용을 입력해주세요\n");
            contentsEditText.requestFocus();
            return true;
        }

        return false;
    }

    public void sendPost(String userName) {
        if (rewrite) {
            int commentCount = getIntent().getExtras().getInt("STUDENT_NOTICE_COMMENT_COUNT");
            postModel.correctPost((String) board_spinner.getSelectedItem(), userName, titleEditText.getText().toString(), contentsEditText.getText().toString(), postKey, commentCount);

        } else
            postModel.writePost((String) board_spinner.getSelectedItem(), userName, titleEditText.getText().toString(), contentsEditText.getText().toString());
    }

    private String getPostAuthorName() {
        String authorName = currentPosition == 2 ? "익명" : User.getInstance().getUserName();
        return authorName;
    }
}
