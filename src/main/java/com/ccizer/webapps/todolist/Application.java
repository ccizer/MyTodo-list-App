package com.ccizer.webapps.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>MyTodo List Application Starter</h1>
 *
 * MyTodo list application provides to do lists with steps for
 * registered users on the system.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
