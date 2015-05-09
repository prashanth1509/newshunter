package com.evo.backend.controllers;

import com.evo.backend.configs.Endpoints;
import com.evo.backend.entities.TextList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prashanth.a on 10/05/15.
 */
@RestController
public class TextProvider {

    @RequestMapping("/twitter-api/trending")
    public Object twitterTrends(){
        Map<String, List<String>> trends = new HashMap();

        trends.put("tags", Arrays.asList(
            /*"#blackandblue",
            "#BookBucketChallenge",
            "#catstairs",
            "#dubsmash",
            "#harlemshake",
            "#Ebola",
            "#Ferguson",
            "#Mission272",*/
            "#fifaWorldCup",
            "#Hololens"
        ));

        return trends;
    }

    @RequestMapping("/twitter-api/getTexts")
    public Object twitterTexts(
        @RequestParam(value = "tag", required = true) String tagId
    ) {
        Map<String, List<String>> result = new HashMap();

        RestTemplate restTemplate = new RestTemplate();
        String apiURL = Endpoints.ALL_HASHES;

        Map<String,List<String>> allHashes = restTemplate.getForObject(apiURL, Map.class);

        if(allHashes.containsKey(tagId)){
            result.put("texts", allHashes.get(tagId));
        }

        return result;
    }
}
