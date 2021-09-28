package com.rokomari.videoapp.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySources({
        @PropertySource("classpath:application.properties")})
public class BaseRestTemplate {
    @Autowired
    private Environment env;

    private static String PROTOCOL;


    public static String HOST;


    public static String PORT;

    public static String PATH = "/videoapi";

    private static String SERVICE_URL = "";

    @PostConstruct
    void init() {

        PROTOCOL = env.getProperty("video.service.protocol");
        HOST = env.getProperty("video.service.host");
        PORT = env.getProperty("video.service.port");
        PATH = env.getProperty("video.service.context");

        if (PORT != null && PORT.length() > 0) {
            SERVICE_URL = PROTOCOL + "://" + HOST + ":" + PORT + "/" + PATH;
        } else {
            SERVICE_URL = PROTOCOL + "://" + HOST + "/" + PATH;
        }
    }

    public static String getUrl() {
        return SERVICE_URL;
    }

}

