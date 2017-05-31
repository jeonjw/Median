package com.ajou.jinwoo.median.viewholder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ajou.jinwoo.median.StudentNoticePhotoAdapter;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.StudentNoticeModel;
import com.ajou.jinwoo.median.ui.CommentListActivity;
import com.ajou.jinwoo.median.ui.StudentNoticeWriteActivity;
import com.ajou.jinwoo.median.valueObject.StudentNotice;
import com.ajou.jinwoo.median.valueObject.User;

import java.util.Objects;

public class StudentNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView titleTextView;
    private TextView contentsTextView;
    private TextView authorTextView;
    private TextView dateTextView;
    private TextView commentCountTextView;
    private ImageButton dropdownButton;
    private String dataRefKey;
    private Context context;
    private StudentNotice studentNotice;
    private RecyclerView photoRecyclerView;


    public StudentNoticeViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.student_notice_title_text_view);
        contentsTextView = (TextView) itemView.findViewById(R.id.student_notice_contents_text_view);
        authorTextView = (TextView) itemView.findViewById(R.id.student_notice_author_text_view);
        dateTextView = (TextView) itemView.findViewById(R.id.student_notice_date_text_view);
        commentCountTextView = (TextView) itemView.findViewById(R.id.comment_number);
        dropdownButton = (ImageButton) itemView.findViewById(R.id.student_notice_dropdown_button);
        photoRecyclerView = (RecyclerView) itemView.findViewById(R.id.student_notice_holder_image_recycler_view);

        titleTextView.setFocusableInTouchMode(true);

        photoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL));


        dropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, dropdownButton);
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.popup_delete) {
                            if (Objects.equals(studentNotice.getAuthorUid(), User.getInstance().getUid())) {
                                StudentNoticeModel studentNoticeModel = new StudentNoticeModel();
                                studentNoticeModel.removeNotice(dataRefKey);
                            } else {
                                Snackbar.make(dateTextView, "권한이 없습니다", Snackbar.LENGTH_SHORT).show();
                            }
                        } else if (item.getItemId() == R.id.popup_rewrite) {
                            Intent intent = new Intent(context, StudentNoticeWriteActivity.class);
                            intent.putExtra("STUDENT_NOTICE_CORRECT_POST_KEY", dataRefKey);
                            intent.putExtra("STUDENT_NOTICE", studentNotice);
                            context.startActivity(intent);
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });

        itemView.setOnClickListener(this);
    }

    public void bindNotice(StudentNotice studentNotice, Context context, String postKey) {
        this.context = context;
        this.studentNotice = studentNotice;

        titleTextView.setText(studentNotice.getTitle());
        contentsTextView.setText(studentNotice.getContents());
        authorTextView.setText(studentNotice.getAuthor());
        dateTextView.setText(studentNotice.getTimeStamp());
        commentCountTextView.setText(String.valueOf(studentNotice.getCommentCount()));
        dataRefKey = postKey;


        int visibility = Objects.equals(studentNotice.getAuthorUid(), User.getInstance().getUid()) ?
                View.VISIBLE : View.GONE;

        dropdownButton.setVisibility(visibility);

        if (studentNotice.getUrlList().size() == 0)
            photoRecyclerView.setVisibility(View.GONE);
        else {
            photoRecyclerView.setVisibility(View.VISIBLE);
            StudentNoticePhotoAdapter photoAdapter = new StudentNoticePhotoAdapter(context, studentNotice.getUrlList());
            photoRecyclerView.setAdapter(photoAdapter);

        }
        titleTextView.requestFocus();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra("POST_KEY", dataRefKey);
        intent.putExtra("POST_TYPE", "student_notice");
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_up_anim, R.anim.no_change);
    }
}
