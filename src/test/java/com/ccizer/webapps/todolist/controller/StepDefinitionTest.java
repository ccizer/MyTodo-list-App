package com.ccizer.webapps.todolist.controller;

import com.ccizer.webapps.todolist.repository.StepRepository;
import com.ccizer.webapps.todolist.service.StepService;
import com.ccizer.webapps.todolist.service.StepServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <h1>Step Definition Tests</h1>
 *
 * Tests step definitions by mocking step form.
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StepDefinitionTest {

    MockMvc mockMvc;

    @InjectMocks
    StepController stepController;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private StepServiceImpl stepServiceImpl;

    @Mock
    private StepService stepService;


    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/ProjectTodoList/src/main/resources/templates");
        viewResolver.setSuffix(".html");

        stepController.setCurrentListID((long)1);

        this.mockMvc = MockMvcBuilders.standaloneSetup(stepController)
                .setViewResolvers(viewResolver)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    /**
     *
     * Tests whether error is taken when the name info is not given.
     *
     */
    @Test
    public void TestStepDefinitionNot() throws Exception {

        this.mockMvc.perform(post("/steps")
                .param("name",""))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("stepmessage"));

    }

    /**
     *
     * Tests whether there is not any error taken when all the info given.
     *
     * <b>Note:</b>The list id info must be given before testing.
     *
     */
    @Test
    public void TestStepDefinitionOk() throws Exception {

        this.mockMvc.perform(post("/steps")
                .param("name","test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeCount(0));

    }

}
