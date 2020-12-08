package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //
public class PagesController {

        @GetMapping("/hello")       //path that will access the below method
        public String helloWorld() {
            return "Hello World";
        }

}
