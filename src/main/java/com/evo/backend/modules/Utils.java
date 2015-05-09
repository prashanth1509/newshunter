package com.evo.backend.modules;

/**
 * Created by prashanth.a on 10/05/15.
 */
public class Utils {
    public static boolean isLocal(){
        return System.getProperty("user.name").equals("prashanth.a");
    }
}
