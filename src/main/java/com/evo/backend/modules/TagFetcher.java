package com.evo.backend.modules;

import com.evo.backend.configs.Endpoints;
import com.evo.backend.entities.TagList;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashanth.a on 09/05/15.
 */
public class TagFetcher implements Runnable {
    private Thread t;
    private String threadName;
    private List<String> tags;

    int misses = 0;
    int maxMisses = 2;

    public TagFetcher(String name){
        threadName = name;
        tags = new ArrayList<String>();
    }

    public void run() {
        System.out.println("Thread running: " +  threadName );
        try {
            while(true) {
                System.out.println("Fetching trending tags..");

                if(misses>maxMisses)
                    break;

                RestTemplate restTemplate = new RestTemplate();
                String apiURL = Endpoints.TWITTER_TRENDS;
                List<String> tagList;

                tagList = restTemplate.getForObject(apiURL, TagList.class).getTags();

                if(tagList==null){
                    Thread.sleep(10 * 1000);
                    misses = misses + 1;
                }
                else{
                    List<String> tagsToFetch = new ArrayList<String>();
                    for(String tag: tagList){
                        if(!tags.contains(tag)){
                            tags.add(tag);
                            tagsToFetch.add(tag);
                        }
                    }
                    if(!tagsToFetch.isEmpty()){
                        SocialTextsFetcher socialTextsFetcher = new SocialTextsFetcher("social-text-fetcher", tagsToFetch);
                        socialTextsFetcher.start();
                    }
                }

                Thread.sleep(5 * 1000);
            }
        } catch (InterruptedException e) {
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

}

