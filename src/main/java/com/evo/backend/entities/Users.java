package com.evo.backend.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by prashanth.a on 10/05/15.
 */
@Document(collection = "newsusers")
public class Users {

    private String id;
    private String name;
    private String pass;
    private List<String> topics;
    private String deviceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
