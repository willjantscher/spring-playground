package com.example.demo;
//./mvnw spring-boot:run

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WordCounterController {

    //use constructor injection
    private WordCounter wordCounter;

    public WordCounterController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/words/count")
    public Map<String, Integer> count (@RequestBody String input) {
        return wordCounter.count(input);
    }
//curl -i -X POST -H 'Content-Type: text/plain' -d 'How now, brown cow' localhost:8080/words/count

    @PostMapping("/words/count/testing")
    public boolean test (@RequestBody String input) {
        return wordCounter.test(input);
    }
}
