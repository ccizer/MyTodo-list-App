package com.ccizer.webapps.todolist.service;

import com.ccizer.webapps.todolist.domain.Step;
import com.ccizer.webapps.todolist.domain.ToDoList;

/**
 * <h1>List Service Interface</h1>
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
public interface ListService {
    long addList(ToDoList toDoList);

    ToDoList getListById(long id);

    Iterable<Step> getListsSteps(long id);

    ToDoList assignList(long id, String username);

    void deleteListById(long id);
}
