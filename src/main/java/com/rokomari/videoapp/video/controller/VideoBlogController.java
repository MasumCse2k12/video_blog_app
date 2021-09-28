package com.rokomari.videoapp.video.controller;

import com.rokomari.videoapp.common.payload.ApiResponse;
import com.rokomari.videoapp.common.utils.Defs;
import com.rokomari.videoapp.video.payload.Videos;
import com.rokomari.videoapp.video.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VideoBlogController {
    private static Logger LOGGER = LoggerFactory.getLogger(VideoBlogController.class);
    @Autowired
    private VideoService videoService;

    @ModelAttribute("video")
    public Videos populateVideo() {
        return new Videos();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("video") Videos video, final RedirectAttributes redirectAttributes, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView(new RedirectView(req.getContextPath() + "/dashboard"));
        String msg = "";
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            ApiResponse response = videoService.crateVideo(video);
            if (response != null) {
                msg = response.getMessage();
                System.out.println("MESSAGE:" + response.getMessage());

            }
        } catch (Throwable t) {
            t.printStackTrace();
            msg = t.getMessage();
            if (t.getMessage().contains(Defs.NOT_AUTHORIZED)) {
                msg = "User not authorized";
            }
        }
        redirectAttributes.addFlashAttribute("video", video);
        redirectAttributes.addFlashAttribute("msg", msg);
        return mv;
    }

}
