package com.ajou.jinwoo.median.model;

import com.ajou.jinwoo.median.valueObject.StudentNotice;

import java.util.List;

/**
 * Created by jinwoo on 2017. 6. 7..
 */

public interface OnStudentNoticeChange {
    void onLoaded(List<StudentNotice> studentNoticeList, List<String> keyList);
}
