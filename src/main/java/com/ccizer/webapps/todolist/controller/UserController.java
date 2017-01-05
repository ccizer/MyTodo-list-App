package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.domain.User;
import com.ccizer.webapps.todolist.domain.validator.RegisterValidator;
import com.ccizer.webapps.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * <h1>User Controller</h1>
 *
 * User Controller class to create new users on the system.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Controller
public class UserController {
    private final UserService userService;
    private final RegisterValidator registerValidator;

    @Autowired
    public UserController(UserService userService, RegisterValidator registerValidator){
        this.userService = userService;
        this.registerValidator = registerValidator;
    }

    /**
     *
     * Checks the user definition validations.
     *
     * @param binder
     * @return ModelAndView
     */
    @InitBinder()
    public void initBinder(WebDataBinder binder){
        binder.addValidators(registerValidator);
    }

    /**
     *
     * Model and View definition for the path "/register".
     * Provides the register form model.
     *
     * @return ModelAndView
     */
    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register","user", new User());
    }

    /**
     *
     * Creates new user on the system.
     *
     * @param user
     * @param bindingResult
     * @return String
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public  String handleRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "register";

        userService.addUser(user);
        return "redirect:/";
    }

}
