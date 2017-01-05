package com.ccizer.webapps.todolist.repository;

import com.ccizer.webapps.todolist.domain.ToDoList;
import org.springframework.data.repository.CrudRepository;

/**
 * <h1>List Repository for CRUD operations</h1>
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
public interface ListRepository extends CrudRepository<ToDoList, Long> {
    Iterable<ToDoList> findByUserId(long idUser);
}
