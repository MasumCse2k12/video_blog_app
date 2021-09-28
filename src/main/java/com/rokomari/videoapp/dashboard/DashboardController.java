package com.rokomari.videoapp.dashboard;

import com.rokomari.videoapp.video.payload.VideoRequest;
import com.rokomari.videoapp.video.payload.VideosResponse;
import com.rokomari.videoapp.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class DashboardController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView dashboardPage() {
        ModelAndView mv = new ModelAndView("blog");
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null){
                mv.addObject("action", true);
            }
            VideosResponse response = videoService.videoSummary(new VideoRequest());
            if(response != null && response.getVideosList() != null){
                mv.addObject("videoList", response.getVideosList());
            }else{
                mv.addObject("videoList", new ArrayList<>());
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
        return mv;
    }
}
