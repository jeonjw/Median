package com.ajou.jinwoo.median;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.ajou.jinwoo.median.model.LectureReview;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BoardWriteFragment extends Fragment {

    private EditText titleEditText;
    private EditText contentsEditText;
    private ImageButton writeButton;
    private ImageButton closeButton;
    private Spinner board_spinner;
    private FragmentManager fragmentManager;
    private DatabaseReference mDatabase;
    private LectureReview lectureReview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_write, container, false);

        setHasOptionsMenu(true);
        final int currentPosition = getArguments().getInt("BoardWriteFragment");

        fragmentManager=getFragmentManager();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        titleEditText = (EditText) view.findViewById(R.id.board_write_title_edit_text);
        contentsEditText = (EditText) view.findViewById(R.id.board_write_content_edit_text);
        contentsEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"test",Toast.LENGTH_SHORT).show();
                contentsEditText.requestFocus();
            }
        });
        writeButton = (ImageButton) view.findViewById(R.id.board_write_finish);
        closeButton = (ImageButton) view.findViewById(R.id.board_write_close_button);
        board_spinner = (Spinner) view.findViewById(R.id.board_spinner);

        board_spinner.post(new Runnable() {
            @Override
            public void run() {
                board_spinner.setSelection(currentPosition);
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lectureReview = new LectureReview(titleEditText.getText().toString(),contentsEditText.getText().toString());
                mDatabase.child((String) board_spinner.getSelectedItem()).push().setValue(lectureReview);
                fragmentManager.beginTransaction().remove(BoardWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().remove(BoardWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.board_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        board_spinner.setAdapter(adapter);
        return view;
    }
    public static Fragment newInstance(int position){
        Fragment boardWriteFragment = new BoardWriteFragment();
        Bundle args = new Bundle();
        args.putInt("BoardWriteFragment", position);
        boardWriteFragment.setArguments(args);

        return boardWriteFragment;

    }
}
