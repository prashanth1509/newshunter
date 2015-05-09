package com.evo.backend.modules;

import com.evo.backend.configs.Endpoints;
import com.evo.backend.entities.TagList;
import com.evo.backend.entities.TextList;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashanth.a on 09/05/15.
 */
public class SocialTextsFetcher implements Runnable {
    private Thread t;
    private String threadName;
    private List<String> tags;

    public SocialTextsFetcher(String name, List<String> inputTags){
        threadName = name;
        tags = inputTags;
    }

    public void run() {
        try {
            for(String tag: tags){
                TextFetcher textFetcher = new TextFetcher("text-fetcher", tag);
                textFetcher.start();
            }
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

}


