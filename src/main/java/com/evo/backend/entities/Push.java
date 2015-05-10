package com.evo.backend.entities;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by prashanth.a on 10/05/15.
 */
@Document(collection = "jobs")
public class Push {
    private String id;
    private String description;
    private Boolean pushed;
    private String newsId;
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPushed() {
        return pushed;
    }

    public void setPushed(Boolean pushed) {
        this.pushed = pushed;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
