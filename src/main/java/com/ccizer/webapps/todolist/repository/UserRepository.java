package com.ccizer.webapps.todolist.repository;

import com.ccizer.webapps.todolist.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * <h1>User Repository for CRUD operations</h1>
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
