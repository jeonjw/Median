package com.ajou.jinwoojeon.median.viewholder;

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

import com.ajou.jinwoojeon.median.R;
import com.ajou.jinwoojeon.median.valueObject.Info;
import com.bumptech.glide.Glide;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mTelNumberTextView;
    private TextView mLocationTextView;
    private ImageView mImageView;
    private ImageButton mPhoneCallButton;
    private Info info;
    private Context context;


    public InfoViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();
        mImageView = itemView.findViewById(R.id.profile_image);
        mNameTextView = itemView.findViewById(R.id.info_name_text_view);
        mEmailTextView = itemView.findViewById(R.id.info_email_text_view);
        mTelNumberTextView = itemView.findViewById(R.id.info_telnumber_text_view);
        mLocationTextView = itemView.findViewById(R.id.info_location_text_view);
        mPhoneCallButton = itemView.findViewById(R.id.info_phone_call_button);

        mPhoneCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = "tel:" + "031219" + info.getTelNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void bindInfo(Info info) {
        this.info = info;
        int res = context.getResources().getIdentifier(this.info.getProfileImage(), "drawable", context.getPackageName());
        Glide.with(context).load(res).into(mImageView);
        mNameTextView.setText(this.info.getName());
        mEmailTextView.setText(this.info.getEmail());
        mLocationTextView.setText(this.info.getLocation());
        mTelNumberTextView.setText(String.valueOf(this.info.getTelNumber()));

        int visible = info.getName().contains("학과사무실") ? View.VISIBLE : View.INVISIBLE;
        mPhoneCallButton.setVisibility(visible);

    }
}