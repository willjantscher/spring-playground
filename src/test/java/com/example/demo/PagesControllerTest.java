package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;    //second option?
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagesController.class)  //note the name is the class, not the test class
public class PagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIndexEndpoint() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        this.mvc.perform(request)
                .andExpect(status().isOk())     //returns status of 200 if good
                .andExpect(content().string("Hello World"));   //make sure to do correct imports
//        this.mvc.perform(get("/"))
    }
}
