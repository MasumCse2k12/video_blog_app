package com.rokomari.videoapp.idm.controller;

import com.rokomari.videoapp.common.enums.AccountStatus;
import com.rokomari.videoapp.common.utils.Defs;
import com.rokomari.videoapp.idm.payload.User;
import com.rokomari.videoapp.idm.payload.UserResponse;
import com.rokomari.videoapp.idm.service.UserServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceManager userService;

    @ModelAttribute("user")
    public User populateUser() {
        return new User();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("user") User user, final RedirectAttributes redirectAttributes, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView(new RedirectView(req.getContextPath() + "/login"));
        String msg = "";
        try {
            user.setUsername(user.getEmail()); // username & email same
            user.setStatus(AccountStatus.active.getValue()); // default active
            UserResponse response = userService.crateUser(user);
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
        redirectAttributes.addFlashAttribute("user", user);
        redirectAttributes.addFlashAttribute("msg", msg);
        return mv;
    }

}
