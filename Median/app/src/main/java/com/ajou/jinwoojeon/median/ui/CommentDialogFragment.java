package com.ajou.jinwoojeon.median.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.model.CommentModel;
import com.ajou.jinwoojeon.median.model.OnDataChangedListener;
import com.ajou.jinwoojeon.median.valueObject.User;

public class CommentDialogFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private CommentModel model;
    private ToggleButton anonymousToggleButton;

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        model.removeListener();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_comment_dialog, null);

        String dataRefKey = getArguments().getString("POST_KEY");
        String postType = getArguments().getString("POST_TYPE");
        model = new CommentModel(dataRefKey, postType);
        recyclerView = view.findViewById(R.id.comment_recycler_view);
        ImageButton closeButton = view.findViewById(R.id.comment_close_button);
        anonymousToggleButton = view.findViewById(R.id.comment_anonymous_toggle_button);
        TextView titleTextView = view.findViewById(R.id.comment_title_text_view);

        titleTextView.setText(getArguments().getString("POST_TITLE"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        final EditText commentEdit = view.findViewById(R.id.comment_edit);
        final ImageButton sendButton = view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(commentEdit.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(sendButton, "댓글을 입력하세요", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    sbView.setBackgroundColor(Color.parseColor("#6CABDD"));
                    snackbar.show();
                    return;
                }

                model.writeComment(getCommentAuthorName(), commentEdit.getText().toString());
                commentEdit.setText("");
            }
        });

        model.setOnDataChangedListener(new OnDataChangedListener() {
            @Override
            public void onDataChanged() {
                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
            }
        });

        setCommentList();
        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return dialog;
    }




    private void setCommentList() {
        recyclerView.setAdapter(model.loadCommentList());
    }

    public static CommentDialogFragment newInstance(String postKey, String postType, String title) {
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();

        Bundle args = new Bundle();
        args.putString("POST_KEY", postKey);
        args.putString("POST_TYPE", postType);
        args.putString("POST_TITLE", title);
        commentDialogFragment.setArguments(args);

        return commentDialogFragment;
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.no_change, R.anim.slide_down_anim);
//        model.removeListener();
//    }

    private String getCommentAuthorName() {
        return anonymousToggleButton.isChecked() ? "익명" : User.getInstance().getUserName();
    }
}
