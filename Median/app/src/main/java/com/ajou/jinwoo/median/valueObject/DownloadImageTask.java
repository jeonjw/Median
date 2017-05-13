package com.ajou.jinwoo.median.valueObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

//Image를 Url을 통해서 받아오는 클래스 ..
//어싱크 태스크를 통해 처리하지않으면 네트워크 쓰레드 Exception에러가 뜬다.
//안드로이드에서는 main Thread에서 network연결을 하지 못하도록 해놨다. 그래서 어싱크태스크 사용
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        InputStream noImageInput = null;
        String urldisplay = urls[0];
        String noImageUrl = "https://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjM7K7RhN3TAhXEopQKHWscC5UQjRwIBw&url=http%3A%2F%2Fentre.hoseo.ac.kr%2F&psig=AFQjCNHD-a1CSQ4oot50YzWIx1GyJub2GA&ust=1494220858060595";
        Bitmap bitmap;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            noImageInput = new java.net.URL(noImageUrl).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = BitmapFactory.decodeStream(noImageInput);
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);

    }

}

