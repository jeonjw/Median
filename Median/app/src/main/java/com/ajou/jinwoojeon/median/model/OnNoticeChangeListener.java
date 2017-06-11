package com.ajou.jinwoojeon.median.model;

import java.util.List;


public interface OnNoticeChangeListener<T> {
    void onChange(List<T> noticeList, List<String> keyList);
}
