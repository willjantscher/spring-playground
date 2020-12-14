package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

@Component
public class WordCounter {

    public Map<String, Integer> count(String input) {
        String words[] = input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        Map<String, Integer> output = new HashMap<>();
        for (String word : words) {
            if (!output.containsKey(word)) {
                output.put(word, 1);
            }else  {
                output.put(word, output.get(word) + 1);
            }
        }
        return output;
    }

}
//Use @Component method (@Service, @RestController, etc...)