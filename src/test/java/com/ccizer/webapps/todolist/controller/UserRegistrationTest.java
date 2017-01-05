package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.repository.UserRepository;
import com.ccizer.webapps.todolist.service.UserService;
import com.ccizer.webapps.todolist.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * <h1>User Definition Tests</h1>
 *
 * Tests user definitions by mocking user form.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegistrationTest {

    MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/ProjectTodoList/src/main/resources/templates");
        viewResolver.setSuffix(".html");

        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    /**
     *
     * Tests whether error is taken when the username info is not given.
     *
     */
    @Test
    public void TestRegisterNot() throws Exception {
        this.mockMvc.perform(post("/register")
                .param("username","")
                .param("password","12345")
                .param("name", "can")
                .param("lastName", "cizer")
        ).andExpect(model().attributeHasFieldErrors("user","username"))
                .andExpect(view().name("register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     *
     * Tests whether there is not any error taken when all the info given.
     *
     *
     */
    @Test
    public void TestRegisterOk() throws Exception {
        this.mockMvc.perform(post("/register")
                .param("username","sedo")
                .param("password","1234567")
                .param("name", "can")
                .param("lastName", "cizer")
                .param("email","canciz@gmail.com")
        ).andExpect(model().attributeErrorCount("user", 0))
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

    }
}
