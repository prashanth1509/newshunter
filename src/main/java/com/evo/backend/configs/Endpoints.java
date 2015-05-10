package com.evo.backend.configs;

import com.evo.backend.modules.Utils;

/**
 * Created by prashanth.a on 09/05/15.
 */
public class Endpoints {

    public static final String ALL_TOPICS = "https://api.myjson.com/bins/1s1xp";
    public static final String ALL_HASHES = "https://api.myjson.com/bins/1uet9";
    public static final String CLASSIFIER = /*"http://localhost:8080/php-apps/test.php";*/ "http://127.0.0.1:8000";

    public static String getTwitterTrendsUrl(){
        return Utils.isLocal()?"http://localhost:8087/twitter-api/trending":"https://sleepy-cliffs-3321.herokuapp.com/twitter-api/trending";
        //return "https://api.myjson.com/bins/1njwd";
    }

    public static String getTwitterTagDataUrl(){
        return Utils.isLocal()?"http://localhost:8087/twitter-api/getTexts":"https://sleepy-cliffs-3321.herokuapp.com/twitter-api/getTexts";
    }

    public static String getPusherUrl(){
        return Utils.isLocal()?"http://localhost:8087/twitter-api/push":"https://sleepy-cliffs-3321.herokuapp.com/twitter-api/push";
    }

}
