package com.ajou.jinwoojeon.median.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.valueObject.StudentNotice;
import com.ajou.jinwoojeon.median.viewholder.StudentNoticeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StudentNoticeAdapter extends RecyclerView.Adapter<StudentNoticeViewHolder>{
    private List<StudentNotice> studentNoticeList;
    private List<String> keyList;

    public StudentNoticeAdapter() {
        studentNoticeList = new ArrayList<>();
    }

    public void setDataList(List<StudentNotice> searchedList) {
        studentNoticeList = searchedList;
        notifyDataSetChanged();
    }
    public void setKeyList(List<String> keyList){
        this.keyList = keyList;
    }

    @Override
    public StudentNoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.list_item_student_notice, parent, false);

        return new StudentNoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentNoticeViewHolder holder, int position) {
        StudentNotice studentNotice = studentNoticeList.get(position);
        holder.bindNotice(studentNotice,keyList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentNoticeList.size();
    }
}
