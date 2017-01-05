package com.ccizer.webapps.todolist.repository;

import com.ccizer.webapps.todolist.domain.Step;
import org.springframework.data.repository.CrudRepository;

/**
 * <h1>Step Repository for CRUD operations</h1>
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
public interface StepRepository extends CrudRepository<Step, Long> {
    Iterable<Step> findByToDoListId(long idToDoList);

    void deleteByToDoListId(long idToDoList);
}
