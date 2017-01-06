package com.ccizer.webapps.todolist.service;

import com.ccizer.webapps.todolist.domain.Step;
import com.ccizer.webapps.todolist.domain.ToDoList;
import com.ccizer.webapps.todolist.domain.User;
import com.ccizer.webapps.todolist.repository.ListRepository;
import com.ccizer.webapps.todolist.repository.StepRepository;
import com.ccizer.webapps.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Set;

/**
 * <h1>List Service Implementation</h1>
 *
 * List Business Operations.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Service
@Transactional
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;
    private final UserService userService;
    private final StepRepository stepRepository;

    @Autowired
    public ListServiceImpl(ListRepository listRepository, UserService userService, StepRepository stepRepository) {
        this.listRepository = listRepository;
        this.userService = userService;
        this.stepRepository = stepRepository;
    }

    /**
     *
     * Creates a new list.
     *
     * @param toDoList
     * @return long
     */
    @Override
    public long addList(ToDoList toDoList) {
        ToDoList toDoListNew = new ToDoList(toDoList.getName(), toDoList.getDescription());
        System.out.println("name:" + toDoListNew.getName() + " lastname:" + toDoListNew.getDescription());
        return listRepository.save(toDoListNew).getId();
    }

    /**
     *
     * Returns To do list with the given id.
     *
     * @param id
     * @return ToDoList
     */
    @Override
    public ToDoList getListById(long id) {
        return listRepository.findOne(id);
    }

    /**
     *
     * Returns the steps of the given list id.
     *
     * @param id
     * @return Iterable
     */
    @Override
    public Iterable<Step> getListsSteps(long id) {
        return stepRepository.findByToDoListId(id);
    }

    /**
     *
     * Assigns the user info to the to do list.
     *
     * @param id
     * @param username
     * @return ModelAndView
     */
    @Override
    public ToDoList assignList(long id, String username) {
        User user = userService.getUserByUsername(username);
        ToDoList toDoList = getListById(id);
        Set<ToDoList> setList = user.getToDoLists();
        toDoList.setUser(user);
        setList.add(toDoList);
        user.setToDoLists(setList);

        return listRepository.save(toDoList);
    }

    /**
     *
     * Deletes the list with the given id.
     *
     * @param id
     */
    @Override
    public void deleteListById(long id) {
        listRepository.delete(id);
    }
}
