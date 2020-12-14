package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import junit.runner.Version;

@SpringBootTest
//@WebMvcTest(WordCounterController.class)
public class WordCounterControllerTest {

//    private final WordCounter wordCounter;
//
//    public WordCounterControllerTest(WordCounter wordCounter) {
//        this.wordCounter = wordCounter;
//    }

    @Autowired
    WordCounter wordCounter;

//    @MockBean
//    WordCounter wordCounterMocked;
//    @Autowired
//    private MockMvc mvc;

//    @BeforeEach
//    public void setup() {
//        Map<String, Integer> mockedOutput = new HashMap<>();
//        mockedOutput.put("a", 2);
//        mockedOutput.put("test", 3);
//        mockedOutput.put("of", 1);
//        mockedOutput.put("this", 2);
//        mockedOutput.put("is", 2);
//        mockedOutput.put("stuff", 1);
//        when(wordCounterMocked.count("this is a test a test test of stuff this is")).thenReturn(mockedOutput);
//    }
//
//    @Test
//    public void testWordCounterMockBean() {
//        Map<String, Integer> output = wordCounterMocked.count("this is a test a test test of stuff this is");
//        assertEquals("{a=2, test=3, of=1, this=2, is=2, stuff=1}", output.toString());
//    }
    @Test
    public void testWordCounterAutowired() throws Exception {
        Map<String, Integer> output = this.wordCounter.count("this is a test a test test of stuff this is is ");
        assertEquals("{a=2, test=3, of=1, this=2, is=3, stuff=1}", output.toString());
    }
//
//    @Test
//    public void testWordCounterControllerMockBean() throws Exception {
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/words/count")
//                .contentType(MediaType.TEXT_PLAIN)
//                .content("this is a test a test test of stuff this is");
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string("{\"a\":2,\"test\":3,\"of\":1,\"this\":2,\"is\":2,\"stuff\":1}"));
//    }
//
//    @Test
//    public void testWordCounterControllerAutowired() throws Exception {
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/words/count")
//                .contentType(MediaType.TEXT_PLAIN)
//                .content("some stuff stuff something a test test");
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string("some data"));
//    }

}
