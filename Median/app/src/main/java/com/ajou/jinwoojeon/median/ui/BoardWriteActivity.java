package com.ajou.jinwoojeon.median.ui;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.adapter.PhotoAdapter;
import com.ajou.jinwoojeon.median.model.OnUploadImageListener;
import com.ajou.jinwoojeon.median.model.PostModel;
import com.ajou.jinwoojeon.median.valueObject.User;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

public class BoardWriteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentsEditText;
    private Spinner board_spinner;
    private int currentPosition;
    private boolean rewrite;
    private PostModel postModel;
    private CheckBox anonymousCheckBox;
    private List<String> selectedPhotos;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        selectedPhotos = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.board_image_recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        currentPosition = getIntent().getExtras().getInt("CURRENT_BOARD_TAB");
        String reWriteTitle = getIntent().getExtras().getString("BOARD_TITLE");
        String reWriteContents = getIntent().getExtras().getString("BOARD_CONTENTS");


        titleEditText = (EditText) findViewById(R.id.board_write_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.board_write_content_edit_text);

        ImageButton writeButton = (ImageButton) findViewById(R.id.board_write_finish);
        ImageButton closeButton = (ImageButton) findViewById(R.id.board_write_close_button);
        ImageButton photoButton = (ImageButton) findViewById(R.id.board_photo_button);

        board_spinner = (Spinner) findViewById(R.id.board_spinner);
        anonymousCheckBox = (CheckBox) findViewById(R.id.board_write_anonymous_check_box);

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

                if(!rewrite)
                    writePost();
                else
                    correctPost();
            }
        });


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setPreviewEnabled(false)
                        .start(BoardWriteActivity.this, PhotoPicker.REQUEST_CODE);

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

//    public void sendPost() {
//
//        if (rewrite) {
//            int commentCount = getIntent().getExtras().getInt("COMMENT_COUNT");
//            postModel.correctPost(getDatabaseKey((String) board_spinner.getSelectedItem()), getPostAuthorName(), titleEditText.getText().toString(), contentsEditText.getText().toString(), postKey, commentCount);
//
//        } else
//            postModel.writePost(getDatabaseKey((String) board_spinner.getSelectedItem()), getPostAuthorName(), titleEditText.getText().toString(), contentsEditText.getText().toString());
//
//        Toast.makeText(getApplicationContext(), "작성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//    }

    private void writePost() {
        final String title = titleEditText.getText().toString();
        final String contents = contentsEditText.getText().toString();
        final String postType = getDatabaseKey(board_spinner.getSelectedItemPosition());

        if (selectedPhotos.size() != 0) {
            Snackbar.make(contentsEditText, "이미지 업로드 중 . .", Snackbar.LENGTH_LONG).show();
            postModel.uploadImages(getInputStreamFromUri(selectedPhotos), new OnUploadImageListener() {
                @Override
                public void onSuccess(List<String> urlList) {
                    postModel.writePostWithImage(postType, getPostAuthorName(), title, contents, urlList);
                    finish();
                }
            });

        } else {
            postModel.writePost(postType, getPostAuthorName(), title, contents);
            finish();
        }
    }

    private void correctPost() {
        final String title = titleEditText.getText().toString();
        final String contents = contentsEditText.getText().toString();
        final String postType = getDatabaseKey(board_spinner.getSelectedItemPosition());
        final String postKey = getIntent().getExtras().getString("CORRECT_POST_KEY");
        final int commentCount = getIntent().getExtras().getInt("COMMENT_COUNT");

        postModel.correctPost(postType, getPostAuthorName(), title, contents, postKey, commentCount);
        finish();

    }

    private String getPostAuthorName() {
        String authorName = anonymousCheckBox.isChecked() ? "익명" : User.getInstance().getUserName();
        return authorName;
    }

    private String getDatabaseKey(int currentPosition) {
        String databaseKey = null;

        switch (currentPosition){
            case 0:
                databaseKey = "student_notice";
            break;
            case 1:
                databaseKey = "reviews";
            break;
            case 2:
                databaseKey = "markets";
            break;
            case 3:
                databaseKey = "questions";
            break;
        }

       return databaseKey;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }

            selectedPhotos.clear();

            if (photos != null)
                selectedPhotos.addAll(photos);

            photoAdapter.notifyDataSetChanged();
        }
    }

    private ArrayList<InputStream> getInputStreamFromUri(List<String> photoInputStreamList) {
        ArrayList<InputStream> inputStreamList = new ArrayList<>();
        InputStream is;
        ContentResolver resolver;
        try {

            for (String str : photoInputStreamList) {
                resolver = getContentResolver();
                is = resolver.openInputStream(Uri.fromFile(new File(str)));
                inputStreamList.add(is);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStreamList;
    }


}
