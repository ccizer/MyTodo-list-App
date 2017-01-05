package com.ccizer.webapps.todolist.service;

import com.ccizer.webapps.todolist.domain.ToDoList;
import com.ccizer.webapps.todolist.domain.User;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

/**
 * <h1>User Service Interface</h1>
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Service
public interface UserService {

    void addUser(User user);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    Iterable<ToDoList> getUsersLists(String username);
}
