package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.domain.Step;
import com.ccizer.webapps.todolist.service.ListService;
import com.ccizer.webapps.todolist.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

/**
 * <h1>Step Controller</h1>
 *
 * Step Controller class to create, print and delete the steps of the lists.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Controller
public class StepController {
    @Autowired
    private ListService listService;
    @Autowired
    private StepService stepService;

    private long currentlistID;

    @Autowired
    private StepController() {
    }
    
    /**
     *
     * Model and View GET definition for the path "/steps/{id}".
     * Provides to print the steps of the given list id.
     *
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value = "/steps/{id}", method = RequestMethod.GET)
    public ModelAndView getStepsPage(@PathVariable Long id) {
        if (listService.getListById(id) == null) {
            throw new NoSuchElementException("List with id" + id + "not found");
        } else {

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("listname", listService.getListById(id).getName());
            model.put("steplist", listService.getListsSteps(id));
            model.put("step", new Step());

            setCurrentListID(id);

            return new ModelAndView("steps", model);
        }
    }

    /**
     *
     * Updates the check status of the given step id.
     *
     * @param id
     * @return String
     */
    @RequestMapping(value = "/steps/{id}", method = RequestMethod.PUT)
    public String handleStepUpdate(@PathVariable("id") Long id){
        stepService.updateCheckValue(id);
        return "redirect:/steps/" + getCurrentListID();
    }

    /**
     *
     * Deletes the step with the given id.
     *
     * @param id
     * @return String
     */
    @RequestMapping(params="delete", value = "/steps/{id}", method = RequestMethod.DELETE)
    public String handleStepDelete(@PathVariable Long id){
        stepService.deleteStepById(id);
        return "redirect:/steps/" + getCurrentListID();
    }

    /**
     *
     * Creates new step definition for the current list.
     *
     * @param step
     * @param bindingResult
     * @param attr
     * @return String
     */
    @RequestMapping(value = "/steps", method = RequestMethod.POST)
    public String handleStepAdd(@Valid @ModelAttribute("step") Step step, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("step", step);
            attr.addFlashAttribute("stepmessage", "Name not found!");
            return "redirect:/steps/" + getCurrentListID() ;
        }

        stepService.assignStep(stepService.addStep(step), getCurrentListID());
        return "redirect:/steps/" + getCurrentListID();
    }

    public void setCurrentListID(long listID) {
        this.currentlistID = listID;
    }

    public long getCurrentListID() {
        return this.currentlistID;
    }
}
