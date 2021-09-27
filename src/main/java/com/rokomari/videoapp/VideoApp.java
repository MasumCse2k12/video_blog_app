package com.rokomari.videoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VideoApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VideoApp.class, args);
	}

}
