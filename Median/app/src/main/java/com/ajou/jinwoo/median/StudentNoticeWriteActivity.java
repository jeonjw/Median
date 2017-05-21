package com.ajou.jinwoo.median;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ajou.jinwoo.median.model.StudentNoticeModel;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


public class StudentNoticeWriteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentsEditText;
    private StudentNoticeModel studentNoticeModel;
    private String postKey;
    private boolean rewrite;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notice_write);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.student_notice_image_recycler_view);
        titleEditText = (EditText) findViewById(R.id.student_notice_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.student_notice_contents_edit_text);
        ImageButton closeButton = (ImageButton) findViewById(R.id.notice_write_close_button);
        ImageButton writeButton = (ImageButton) findViewById(R.id.notice_write_finish);
        ImageButton photoButton = (ImageButton) findViewById(R.id.student_notice_photo_button);

        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

        studentNoticeModel = new StudentNoticeModel();

        String reWriteTitle = null;
        String reWriteContents = null;

        if (getIntent().getExtras() != null) {
            reWriteTitle = getIntent().getExtras().getString("STUDENT_TITLE");
            reWriteContents = getIntent().getExtras().getString("STUDENT_CONTENTS");
            postKey = getIntent().getExtras().getString("STUDENT_NOTICE_CORRECT_POST_KEY");
        }

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setGridColumnCount(4)
                        .setPreviewEnabled(false)
                        .start(StudentNoticeWriteActivity.this, PhotoPicker.REQUEST_CODE);

            }
        });

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

        } else {
            if (selectedPhotos.size() == 0)
                studentNoticeModel.writeNotice(titleEditText.getText().toString(), contentsEditText.getText().toString());
            else
                studentNoticeModel.writeNoticeWithImage(titleEditText.getText().toString(), contentsEditText.getText().toString(), getInputStreamFromUri(selectedPhotos));
        }
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

    private ArrayList<InputStream> getInputStreamFromUri(ArrayList<String> photoInputStreamList) {
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
