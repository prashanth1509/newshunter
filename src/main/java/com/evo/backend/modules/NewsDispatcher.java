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
        String result = restTemplate.getForObject(Endpoints.CLASSIFIER + "?label={label}&texts={json}", String.class, label, json);
        System.out.println(result);
    }

}
