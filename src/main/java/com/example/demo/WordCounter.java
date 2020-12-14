package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WordCounter {
    private final MyConfig config;

    public WordCounter(MyConfig config) {
        this.config = config;
    }

    public Map<String, Integer> count(String input) {
        String words[];
        if(config.isCaseSensitive()) {
            words = input.replaceAll("[^a-zA-Z ]", "").split(" ");
        }else {
            words = input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        }

        for (int i = 0; i < words.length; i++) {
            for(String word : config.getSkip()) {
                if (words[i].equals(word)) {
                    words[i] = "";
                }
            }
        }

        Map<String, Integer> output = new HashMap<>();
        for (String word : words) {
            if(word != "") {
                if (!output.containsKey(word)) {
                    output.put(word, 1);
                }else  {
                    output.put(word, output.get(word) + 1);
                }
            }

        }
//        Map<String, Integer> outputTest = new HashMap<>();
//        outputTest.put("testing", 3);
        return output;
    }


    public Map<String, Integer> countSimplified(String input) {
        String words[];
        words = input.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");

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



    public boolean test (String input) {
        return config.isCaseSensitive();
    }

}
//Use @Component method (@Service, @RestController, etc...)





