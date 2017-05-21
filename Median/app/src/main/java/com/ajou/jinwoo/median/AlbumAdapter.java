package com.ajou.jinwoo.median;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by samsung on 2017-05-22.
 */

public class AlbumAdapter extends BaseAdapter {

    private Context context;

    public AlbumAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(400,400));
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(images[position]);
        return imageView;
    }

    private Integer[] images = {
            R.drawable.ajou_logo, R.drawable.hyejung , R.drawable.hyunjoon, R.drawable.kowook, R.drawable.minho
    };
}
