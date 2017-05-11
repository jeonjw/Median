package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.ajou.jinwoo.median.model.Post;
import com.ajou.jinwoo.median.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BoardWriteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentsEditText;
    private ImageButton writeButton;
    private ImageButton closeButton;
    private Spinner board_spinner;
    private DatabaseReference mDatabase;
    private Post post;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);


        final int currentPosition = getIntent().getExtras().getInt("CURRENT_BOARD_TAB");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        titleEditText = (EditText) findViewById(R.id.board_write_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.board_write_content_edit_text);
        contentsEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BoardWriteActivity.this, "test", Toast.LENGTH_SHORT).show();
                contentsEditText.requestFocus();
            }
        });
        writeButton = (ImageButton) findViewById(R.id.board_write_finish);
        closeButton = (ImageButton) findViewById(R.id.board_write_close_button);
        board_spinner = (Spinner) findViewById(R.id.board_spinner);

        board_spinner.post(new Runnable() {
            @Override
            public void run() {
                board_spinner.setSelection(currentPosition);
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName;
                if (TextUtils.isEmpty(titleEditText.getText().toString())) {
                    titleEditText.setError("제목을 입력하세요");
                    titleEditText.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(contentsEditText.getText().toString())) {
                    contentsEditText.setError("내용을 입력하세요");
                    contentsEditText.requestFocus();
                    return;
                }
                if (currentPosition == 2)
                    userName = "익명";
                else
                    userName = User.getInstance().getUserName();
                post = new Post(userName, titleEditText.getText().toString(), contentsEditText.getText().toString());
                mDatabase.child((String) board_spinner.getSelectedItem()).push().setValue(post);
                finish();
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BoardWriteActivity.this, R.array.board_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        board_spinner.setAdapter(adapter);
    }
}
