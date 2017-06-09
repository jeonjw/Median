package com.ajou.jinwoo.median.model;

import java.util.List;


public interface OnNoticeChangeListener<T> {
    void onChange(List<T> noticeList, List<String> keyList);
}
