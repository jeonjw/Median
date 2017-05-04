package com.ajou.jinwoo.median.viewholder;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajou.jinwoo.median.BoardFragment;
import com.ajou.jinwoo.median.CommentListActivity;
import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.model.StudentNotice;
import com.google.firebase.database.DatabaseReference;

public class StudentNoticeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mContentsTextView;
    private RecyclerView recyclerView;
    private String dataRefKey;
    private DatabaseReference mDatabase;
    private Context context;


    public StudentNoticeViewHolder(View itemView) {
        super(itemView);

        mTitleTextView = (TextView) itemView.findViewById(R.id.media_notice_title_text_view);
        mContentsTextView = (TextView) itemView.findViewById(R.id.media_notice_contents_text_view);

        itemView.setOnClickListener(this);
    }


    public void bindNotice(StudentNotice studentNotice, Context context, String postKey) {
        mTitleTextView.setText(studentNotice.getTitle());
        mContentsTextView.setText(studentNotice.getContents());
        dataRefKey = postKey;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra("POST_KEY", dataRefKey);
        context.startActivity(intent);
    }
//        View view = LayoutInflater.from(activity).inflate(R.layout.popup_comment_list, null, false);
//        recyclerView = (RecyclerView) view.findViewById(R.id.comment_recycler_view);
//        final EditText commentEdit = (EditText) view.findViewById(R.id.comment_edit);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        commentEdit.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    User user =User.getInstance();
//                    Comment comment = new Comment(user.getUserName(),commentEdit.getText().toString());
//                    mDatabase.child("comments").child(dataRefKey).push().setValue(comment);
//
//                    commentEdit.setText("");
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        setCommentList();
//
//        Display display = ((Activity) activity).getWindowManager().getDefaultDisplay();
//        final Point size = new Point();
//        display.getSize(size);
//
//        // set height depends on the device size
//        PopupWindow popWindow = new PopupWindow(view, size.x, size.y, true);
//        // set a background drawable with rounders corners
//        popWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.popup_background, null));
//
//        // make it focusable to show the keyboard to enter in `EditText`
//        popWindow.setFocusable(true);
//        // make it outside touchable to dismiss the popup window
//        popWindow.setOutsideTouchable(true);
//
//        // show the popup at bottom of the screen and set some margin at bottom ie,
//        popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
////        popWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//    }
//
//    private void setCommentList() {
//        LinearLayoutManager mManager = new LinearLayoutManager(activity);
//        mManager.setReverseLayout(true);
//        mManager.setStackFromEnd(true);
//        mManager.scrollToPositionWithOffset(0, 0);
//        recyclerView.setLayoutManager(mManager);
//
//
//        FirebaseRecyclerAdapter<Comment, CommentViewHolder> mAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(Comment.class, R.layout.list_item_comment,
//                CommentViewHolder.class, mDatabase.child("comments").child(dataRefKey)) {
//            @Override
//            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
//                viewHolder.bindComment(model);
//            }
//
//        };
//
//        recyclerView.setAdapter(mAdapter);
//    }
}
