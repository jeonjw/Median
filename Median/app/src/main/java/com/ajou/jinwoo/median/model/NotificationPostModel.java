package com.ajou.jinwoo.median.model;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class NotificationPostModel extends AsyncTask<Void,Void,Void> {
    private String url;
    private String title;
    private String body;

    public NotificationPostModel(String title, String body) {
        url = "https://fcm.googleapis.com/fcm/send";
        this.title = title;
        this.body = body;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL object = new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Authorization", "key=AAAA__71jho:APA91bEjPS63CO_osmhqlDXJiMGznsAUYh80Go81Gh536GCzTJLu8dvijJskbHmfxkgZFCFKvFWbS0NhE60oEgOk1bcSIqrTF2bh22E9SQUiepNvJMNTH5sPtUWNKBIKbha1NfJqkE7y");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("POST");

            JSONObject data = new JSONObject();
            JSONObject parent = new JSONObject();

            data.put("title", title);
            data.put("body", body);
//cm1wboMuxnk:APA91bF7usfp43ryYkAgfZG1SKox71Wkumqjsaaf6_0B61oX05ldwcVWxcurFXMek_7S5ep5sGVr1dBNDquXqkuP4o2xFFVfmlcDdwCOVaosifj3Tcc8imMQvsxNp6j3isn2QaJqm2lr
            parent.put("to", "/topics/notice");
            parent.put("priority", "high");
            parent.put("data", data);
            System.out.println("확인"+parent.toString());

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(parent.toString());
            writer.flush();
            writer.close();
            os.close();
            con.connect();

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(con.getResponseMessage());
            }


        }catch (Exception e){
                e.printStackTrace();
        }

        return null;
    }
}
