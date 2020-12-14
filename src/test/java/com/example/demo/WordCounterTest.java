package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WordCounterTest {

    //this will connect to the Application Context and get teh bean for wordcounter
    @Autowired
    WordCounter wordCounter;

    @Test
    public void testWordCounter() {
        Map<String, Integer> output =  wordCounter.count("this is a test of this word counter and a random string string string test");
        assertEquals("{a=2, random=1, test=2, string=3, and=1, of=1, this=2, is=1, counter=1, word=1}", output.toString());
    }
}
