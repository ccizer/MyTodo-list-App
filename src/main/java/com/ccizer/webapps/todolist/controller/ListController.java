package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.domain.ToDoList;
import com.ccizer.webapps.todolist.service.ListService;
import com.ccizer.webapps.todolist.service.StepService;
import com.ccizer.webapps.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>List Controller</h1>
 *
 * List Controller class to create, print and delete the to do lists.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Controller
public class ListController {
    private final UserService userService;
    private final ListService listService;
    private final StepService stepService;
    private String currentPrincipalName;

    @Autowired
    public ListController(UserService userService, ListService listService, StepService stepService) {
        this.userService = userService;
        this.listService = listService;
        this.stepService = stepService;
    }

    /**
     *
     * Model and View GET definition for the path "/lists".
     * Provides to print the to do lists for the owned user.
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public ModelAndView getListsPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        setCurrentPrincipalName(authentication.getName());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("lists", userService.getUsersLists(getCurrentPrincipalName()));
        model.put("todolist", new ToDoList());
        return new ModelAndView("lists", model);

    }

    /**
     *
     * Deletes the to do list and its steps for the given list id.
     *
     * @param id
     * @return String
     */
    @RequestMapping(params="delete", value = "/lists/{id}", method = RequestMethod.DELETE)
    public String handleListDelete(@PathVariable Long id){
        stepService.deleteStepsByListId(id);
        listService.deleteListById(id);
        return "redirect:/lists";
    }

    /**
     *
     * Creates a new to do list for the current user on the system.
     *
     * @param todolist
     * @param bindingResult
     * @param attr
     * @return String
     */
    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    public String handleListAdd(@Valid @ModelAttribute("todolist") ToDoList todolist, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {

            attr.addFlashAttribute("todolist", todolist);
            if (bindingResult.hasFieldErrors("name")) {
                attr.addFlashAttribute("namemessage", "Name not found!");
            }

            if (bindingResult.hasFieldErrors("description")) {
                attr.addFlashAttribute("descmessage", "Description not found!");
            }

            return "redirect:/lists";
        }

        listService.assignList(listService.addList(todolist), getCurrentPrincipalName());
        return "redirect:/lists";
    }

    protected String getCurrentPrincipalName()
    {
        return currentPrincipalName;
    }

    protected void setCurrentPrincipalName(String principalName){
        this.currentPrincipalName = principalName;
    }
}
