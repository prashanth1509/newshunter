package com.evo.backend.modules;

import com.evo.backend.configs.Endpoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by prashanth.a on 10/05/15.
 */
public class NewsDispatcher {
    private List<String> news;
    private String label;

    public List<String> getNews() {
        return news;
    }

    public void setNews(List<String> news) {
        this.news = news;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void dispatch() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this.news);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> result = restTemplate.getForObject(Endpoints.CLASSIFIER + "?label={label}&texts={json}", Map.class, label, json);

        if(result.get("isNews").toString().equals("yes")){
            System.out.println("Triggering news push");
            restTemplate.getForObject(Endpoints.getPusherUrl() + "?label={label}", String.class, label);
        }

    }

}
