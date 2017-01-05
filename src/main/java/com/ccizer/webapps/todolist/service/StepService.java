package com.ccizer.webapps.todolist.service;

import com.ccizer.webapps.todolist.domain.Step;

/**
 * <h1>Step Service Interface</h1>
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
public interface StepService {
    long addStep(Step step);

    Step getStepById(long id);

    Step assignStep(long stepId, long listId);

    void deleteStepById(long id);

    void deleteStepsByListId(long listId);

    void updateCheckValue(long id);
}
