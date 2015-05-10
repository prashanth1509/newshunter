package com.evo.backend.modules;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Created by prashanth.a on 10/05/15.
 */
public class PushUtils {

    private String id;
    private String message;
    private String data;
    private String newsId;

    public PushUtils(String id) {
        this.id = id;
    }

    public boolean pushMessage(){
        System.out.println("Global push message");
        Sender sender = new Sender("AIzaSyBW8m_-OAPblSRiJBRrGjAYogl31Je8V8o");
        Message message = new Message.Builder().timeToLive(30).delayWhileIdle(true).addData("NS_MESSAGE", "test").build();
        try{
            System.out.println("Message-sending: " + id);
            Result result = sender.send(message, id, 1);
            System.out.println("Message-sent: " + result.getMessageId());
        }
        catch (Exception e){
            System.err.println("Exception during gcm.");
            e.printStackTrace();
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
