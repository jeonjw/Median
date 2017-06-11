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
import android.widget.EditText;
import android.widget.ImageButton;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.adapter.StudentNoticePhotoAdapter;
import com.ajou.jinwoojeon.median.model.OnUploadImageListener;
import com.ajou.jinwoojeon.median.model.StudentNoticeModel;
import com.ajou.jinwoojeon.median.valueObject.StudentNotice;

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
    private boolean correct;
    private List<String> selectedPhotos = new ArrayList<>();
    private StudentNoticePhotoAdapter photoAdapter;
    private StudentNotice studentNotice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notice_write);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.student_notice_image_recycler_view);

        ImageButton closeButton = (ImageButton) findViewById(R.id.notice_write_close_button);
        ImageButton writeButton = (ImageButton) findViewById(R.id.notice_write_finish);
        ImageButton photoButton = (ImageButton) findViewById(R.id.student_notice_photo_button);

        titleEditText = (EditText) findViewById(R.id.student_notice_title_edit_text);
        contentsEditText = (EditText) findViewById(R.id.student_notice_contents_edit_text);

        studentNoticeModel = new StudentNoticeModel();

        if (getIntent().getExtras() != null) {
            studentNotice = getIntent().getParcelableExtra("STUDENT_NOTICE");
            postKey = getIntent().getExtras().getString("STUDENT_NOTICE_CORRECT_POST_KEY");
            titleEditText.setText(studentNotice.getTitle());
            contentsEditText.setText(studentNotice.getContents());
            correct = true;
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
                writeNotice();

            }
        });

        photoAdapter = new StudentNoticePhotoAdapter(this, selectedPhotos);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));
        recyclerView.setAdapter(photoAdapter);

    }

    private boolean isTextInputError() {
        if (TextUtils.isEmpty(titleEditText.getText().toString())) {
            titleEditText.setError("제목을 입력하세요\n");
            titleEditText.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(contentsEditText.getText().toString())) {
            contentsEditText.setError("내용을 입력하세요\n");
            contentsEditText.requestFocus();
            return true;
        }

        return false;
    }

    private void writeNotice() {
        if (selectedPhotos.size() != 0) {
            Snackbar.make(contentsEditText, "이미지 업로드 중 . .", Snackbar.LENGTH_LONG).show();
            studentNoticeModel.uploadImages(getInputStreamFromUri(selectedPhotos), new OnUploadImageListener() {
                @Override
                public void onSuccess(List<String> urlList) {
                    if (!correct)
                        studentNoticeModel.writeNoticeWithImage(titleEditText.getText().toString(), contentsEditText.getText().toString(), urlList);
                    else
                        studentNoticeModel.correctNoticeWithImages(titleEditText.getText().toString(), contentsEditText.getText().toString(), postKey, studentNotice.getCommentCount(), urlList);
                    finish();
                }
            });

        } else {
            if (!correct)
                studentNoticeModel.writeNotice(titleEditText.getText().toString(), contentsEditText.getText().toString());
            else
                studentNoticeModel.correctNotice(titleEditText.getText().toString(), contentsEditText.getText().toString(), postKey, studentNotice.getCommentCount());
            finish();
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
