package com.ajou.jinwoojeon.median.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ajou.jinwoojeon.median.R;

public class MyAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] strings;

    public MyAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        strings = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater=((Activity) context).getLayoutInflater();
        View row=inflater.inflate(R.layout.spin_test, parent, false);
        TextView label= row.findViewById(R.id.day_textview);
        label.setText(strings[position]);


        return row;
    }
}
