/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokomari.videoapp.authentication.controller;


import com.rokomari.videoapp.common.utils.Utils;
import com.rokomari.videoapp.video.payload.VideoRequest;
import com.rokomari.videoapp.video.payload.VideosResponse;
import com.rokomari.videoapp.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Masum
 */
@Controller
public class AuthenticationController {

    @Autowired
    private ServletContext mcontext;
    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, boolean error, @RequestParam(value = "msg", required = false) String errorMessage) throws Exception {
        String errorMsg = "";
        if(error){
            errorMsg = (String)mcontext.getAttribute("c_error");
            System.out.println(errorMsg);
            if(Utils.isOk(errorMsg)){
                model.addAttribute("error", errorMsg);
            }else{
                if(LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("en")) {
                    model.addAttribute("error", "Internal server error. Please try again later!");
                }
            }
        }
        if(model.getAttribute("c_error") != null){
            model.addAttribute("error", model.getAttribute("c_error"));
        }

        return "login";
    }

    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView blogPage(Model model) throws Exception {
        ModelAndView mv = new ModelAndView("blog");
        try{
            mv.addObject("action", false);
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String blogPageRedirect(Model model) {
        return "redirect:blog";
    }


    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (javax.servlet.http.Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "redirect:/login?signout=true";
    }
}
