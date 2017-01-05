package com.ccizer.webapps.todolist.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * <h1>Login Controller</h1>
 *
 * Provides the login form to the guest user.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Controller
public class LoginController {
    /**
     *
     * Model and View definition for the path "/login".
     *
     * @param error
     * @return ModelAndView
     */
    @PreAuthorize("isAnonymous()")
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login","error", error);
    }
}
