package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h1>Home Controller</h1>
 *
 * Home Controller class for Home page.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Controller
public class HomeController {

    /**
     *
     * Model and View definition for the paths "/" and "/home".
     *
     * @param user
     * @return ModelAndView
     */
    @RequestMapping(value = {"/", "/home"})
    public ModelAndView getHomePage(@AuthenticationPrincipal User user) {
        return new ModelAndView("home", "user", user);
    }
}
