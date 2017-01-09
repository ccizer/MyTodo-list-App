package com.ccizer.webapps.todolist.service;

import com.ccizer.webapps.todolist.domain.Step;
import com.ccizer.webapps.todolist.domain.ToDoList;
import com.ccizer.webapps.todolist.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * <h1>Step Service Implementation</h1>
 *
 * Step Business Operations.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Service
@Transactional
public class StepServiceImpl implements StepService{
    @Autowired
    private StepRepository stepRepository;
    @Autowired
    private ListService listService;

    @Autowired
    public  StepServiceImpl(){
    }
    
    /**
     *
     * Creates a new step.
     *
     * @param step
     * @return long
     */
    @Override
    public long addStep(Step step) {
        Step stepNew = new Step(step.getName());
        return stepRepository.save(stepNew).getId();
    }

    /**
     *
     * Returns To do step with the given id.
     *
     * @param id
     * @return ToDoList
     */
    @Override
    public Step getStepById(long id) {
        return stepRepository.findOne(id);
    }

    /**
     *
     * Assigns the list info to the defined step.
     *
     * @param stepId
     * @param listId
     * @return Step
     */
    @Override
    public Step assignStep(long stepId, long listId) {
        ToDoList toDoList = listService.getListById(listId);
        Step step = getStepById(stepId);
        Set<Step> setStep = toDoList.getSteps();
        step.setToDoList(toDoList);
        setStep.add(step);
        toDoList.setSteps(setStep);

        return stepRepository.save(step);
    }

    /**
     *
     * Deletes the step with the given id.
     *
     * @param id
     */
    @Override
    public void deleteStepById(long id) {
        stepRepository.delete(id);
    }

    /**
     *
     * Deletes all the steps with the given list id.
     *
     * @param listId
     */
    @Override
    public void deleteStepsByListId(long listId) {
        stepRepository.deleteByToDoListId(listId);
    }

    /**
     *
     * Checks or unchecks the status of the step with the given id.
     *
     * @param id
     */
    @Override
    public void updateCheckValue(long id) {
        Step step = getStepById(id);
        step.setChecked(!step.getChecked());

        stepRepository.save(step);
    }
}
