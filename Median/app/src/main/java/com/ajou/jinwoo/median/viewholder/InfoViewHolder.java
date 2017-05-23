package com.ajou.jinwoo.median.viewholder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.jinwoo.median.R;
import com.ajou.jinwoo.median.valueObject.Info;
import com.bumptech.glide.Glide;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mTelNumberTextView;
    private TextView mLocationTextView;
    private ImageView mImageView;
    private ImageButton mPhoneCallButton;
    private Info mInfo;
    private Context context;


    InfoViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.profile_image);
        mNameTextView = (TextView) itemView.findViewById(R.id.info_name_text_view);
        mEmailTextView = (TextView) itemView.findViewById(R.id.info_email_text_view);
        mTelNumberTextView = (TextView) itemView.findViewById(R.id.info_telnumber_text_view);
        mLocationTextView = (TextView) itemView.findViewById(R.id.info_location_text_view);
        mPhoneCallButton = (ImageButton) itemView.findViewById(R.id.info_phone_call_button);

        mPhoneCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = "tel:" + "031219" + mInfo.getTelNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void bindNotice(Info info, Context context) {
        this.context = context;
        mInfo = info;
        int res = context.getResources().getIdentifier(mInfo.getProfileImage(), "drawable",context.getPackageName());
        Glide.with(context).load(res).into(mImageView);
        mNameTextView.setText(mInfo.getName());
        mEmailTextView.setText(mInfo.getEmail());
        mLocationTextView.setText(mInfo.getLocation());
        mTelNumberTextView.setText(String.valueOf(mInfo.getTelNumber()));

        if (mInfo.getName().contains("학과사무실"))
            mPhoneCallButton.setVisibility(View.VISIBLE);
        else {
            mPhoneCallButton.setVisibility(View.INVISIBLE);
        }


    }
}