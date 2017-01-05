package com.ccizer.webapps.todolist.service;

import com.ccizer.webapps.todolist.domain.ToDoList;
import com.ccizer.webapps.todolist.domain.User;
import com.ccizer.webapps.todolist.repository.ListRepository;
import com.ccizer.webapps.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>User Service Implementation</h1>
 *
 * User Business Operations.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ListRepository listRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ListRepository listRepository){
        this.userRepository = userRepository;
        this.listRepository = listRepository;
    }
    /**
     *
     * Creates a new user.
     *
     * @param user
     */
    @Override
    public void addUser(User user){
        userRepository.save(user);
    }

    /**
     *
     * Returns User object with the given username info.
     *
     * @param username
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     *
     * Returns User object with the given email info.
     *
     * @param email
     * @return User
     */
    @Override
    public User getUserByEmail(String email) { return userRepository.findByEmail(email);}

    /**
     *
     * Returns all the lists that user owns.
     *
     * @param username
     * @return Iterable
     */
    @Override
    public Iterable<ToDoList> getUsersLists(String username) {
        User user = getUserByUsername(username);
        return listRepository.findByUserId(user.getId());
    }

    /**
     *
     * Checks user details for validation.
     *
     * @param username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<SimpleGrantedAuthority> auth = (List<SimpleGrantedAuthority>) user.getAuthorities();

        if (null == user) {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        } else {
            return user;
        }
    }


}
