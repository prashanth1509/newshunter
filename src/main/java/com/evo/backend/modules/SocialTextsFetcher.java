package com.evo.backend.modules;

import com.evo.backend.entities.TagList;
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
        System.out.println("Thread running: " +  threadName );
        try {
            System.out.println("Fetching for tag: ");
            for(String tag: tags){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start ()
    {
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    private void getTextsForTag(String tag){

    }

}


