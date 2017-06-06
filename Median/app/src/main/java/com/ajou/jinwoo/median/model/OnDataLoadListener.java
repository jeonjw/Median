package com.ajou.jinwoo.median.model;

import com.ajou.jinwoo.median.valueObject.MediaNotice;

import java.util.List;

public interface OnDataLoadListener {
    void onLoaded(List<MediaNotice> dataList);
}
