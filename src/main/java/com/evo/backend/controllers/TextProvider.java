package com.evo.backend.controllers;

import com.evo.backend.configs.Endpoints;
import com.evo.backend.entities.News;
import com.evo.backend.entities.Push;
import com.evo.backend.entities.Users;
import com.evo.backend.modules.PushUtils;
import com.evo.backend.repos.NewsRepository;
import com.evo.backend.repos.PushRepository;
import com.evo.backend.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prashanth.a on 10/05/15.
 */
@RestController
public class TextProvider {

    private List<String> tags = Arrays.asList( /*"#blackandblue", "#BookBucketChallenge", "#catstairs", "#dubsmash", "#harlemshake", "#Ebola", "#Ferguson", "#Mission272", "#fifaWorldCup",*/ "#Hololens" );

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private PushRepository pushRepository;

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping("/app/signup")
    public Object signup(
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "deviceId", required = true) String deviceId,
        @RequestParam(value = "pass", required = false) String pass,
        @RequestParam(value = "topics", required = false) String topics
    ) {
        String[] interestedTopics;
        if(topics!=null && topics.length()>0){
            interestedTopics = topics.split(",");
        }

        Users existing = usersRepository.findByDeviceId(deviceId);
        if(existing!=null && !existing.getId().isEmpty()){
            return true;
        }

        Users user = new Users();
        user.setName(name);
        user.setDeviceId(deviceId);
        user.setPass("default");

        usersRepository.save(user);

        return true;
    }

    @RequestMapping("/app/reset")
    public Object reset(){

        newsRepository.deleteAll();
        pushRepository.deleteAll();
        usersRepository.deleteAll();


        for(String tag: tags){
            News news = new News();
            news.setTag(tag);
            news.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");
            news.setHeading(tag.toUpperCase().replace("#",""));
            newsRepository.save(news);
        }

        Users user = new Users();
        user.setDeviceId("APA91bHkD6UWLHQiZwB26tCKxXaOMA43c31R9hnrMyMPtb_Yaivd9vnFg41czoz-BAGNGiM3AZN-UMfVhjtp7sLf4ZHSIu88y-mqphIPAvGpUPVKoqiSuqmIY4zBfUTdMjX5xS_w3V3d");
        user.setPass("");
        user.setName("Suseee");
        usersRepository.save(user);

        return true;
    }

    @RequestMapping("/twitter-api/trending")
    public Object twitterTrends(){
        Map<String, List<String>> trends = new HashMap();
        trends.put("tags", tags);
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

    @RequestMapping("/app/push")
    public Object push(
        @RequestParam(value = "label", required = true) String tagId
    ){
        News news = newsRepository.findByTag(tagId);
        if(news!=null && news.getId().length()>0){
            //get device ids and push this news to all users
            List<Users> users = usersRepository.findAll();
            for(Users user: users){
                PushUtils util = new PushUtils(user.getDeviceId());
                util.setData(news.getContent());
                util.setNewsId(news.getId());
                util.pushMessage();
            }
        }
        else{
            Push lazyPush = new Push();
            lazyPush.setPushed(false);
            lazyPush.setTag(tagId);
            pushRepository.save(lazyPush);
        }
        return "success";
    }

    @RequestMapping("/app/news")
    public Object getNews(
        @RequestParam(value = "newsId", required = true) String newsId
    ) {
        News news = newsRepository.findOne(newsId);
        return news;
    }


}
