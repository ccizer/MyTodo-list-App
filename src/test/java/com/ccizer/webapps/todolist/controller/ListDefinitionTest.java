package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.repository.ListRepository;
import com.ccizer.webapps.todolist.service.ListService;
import com.ccizer.webapps.todolist.service.ListServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * <h1>List Definition Tests</h1>
 *
 * Tests list definitions by mocking list form.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListDefinitionTest {

    MockMvc mockMvc;

    @InjectMocks
    ListController listController;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ListServiceImpl listServiceImpl;

    @Mock
    private ListService listService;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/ProjectTodoList/src/main/resources/templates");
        viewResolver.setSuffix(".html");

        listController.setCurrentPrincipalName("ccizer");

        this.mockMvc = MockMvcBuilders.standaloneSetup(listController)
                .setViewResolvers(viewResolver)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    /**
     *
     * Tests whether error is taken when the name info is not given.
     *
     */
    @Test
    public void TestListDefinitionNot() throws Exception {

        this.mockMvc.perform(post("/lists")
                .param("name","")
                .param("description","test description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("namemessage"));

    }

    /**
     *
     * Tests whether there is not any error taken when all the info given.
     *
     * <b>Note:</b>The username info must be given before testing.
     *
     */
    @Test
    public void TestListDefinitionOk() throws Exception {

        this.mockMvc.perform(post("/lists")
                .param("name","test")
                .param("description","description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeCount(0));

    }
}
