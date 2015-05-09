package com.evo.backend.modules;

import com.evo.backend.configs.Endpoints;
import com.evo.backend.entities.TextList;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashanth.a on 10/05/15.
 */
public class TextFetcher implements Runnable {
    private Thread t;
    private String threadName;
    private String tag;

    public TextFetcher(String name, String inputTag){
        threadName = name;
        tag = inputTag;
    }

    public void run() {
        try {

            NewsDispatcher dispatcher = new NewsDispatcher();
            dispatcher.setLabel(tag);
            dispatcher.setNews(getTextsForTag(tag));
            dispatcher.dispatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start ()
    {
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    private List getTextsForTag(String tag){
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = Endpoints.getTwitterTagDataUrl() + "?tag={tag}";

        List<String> texts = restTemplate.getForObject(apiURL, TextList.class, tag).getTexts();
        if(texts!=null){
            return texts;
        }
        return new ArrayList();
    }

}


