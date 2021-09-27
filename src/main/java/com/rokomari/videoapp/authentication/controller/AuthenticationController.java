/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rokomari.videoapp.authentication.controller;


import com.rokomari.videoapp.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Masum
 */
@Controller
public class AuthenticationController {

    @Autowired
    private ServletContext mcontext;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPageRedirect(Model model) {
        return "redirect:login";
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
