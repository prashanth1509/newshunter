package com.evo.backend.modules;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Created by prashanth.a on 10/05/15.
 */
public class PushUtils {

    private String id;
    private String newsId;
    private String topic;
    private String hashTag;

    public PushUtils(String id) {
        this.id = id;
    }

    public boolean pushMessage(){
        System.out.println("Global push message");
        Sender sender = new Sender("AIzaSyDhRTGzyjvxy54VCPeSPLTZlEq5WdQ2CXc");

        String multiplexed = hashTag + "," + topic + "," + newsId;

        Message message = new Message.Builder().timeToLive(30).delayWhileIdle(true).addData("NS_MSG", multiplexed).build();
        try{
            System.out.println("Message-sending-to: " + id + "with content: " + multiplexed);
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

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
}
