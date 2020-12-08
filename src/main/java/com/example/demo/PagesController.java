package com.example.demo;
//to run the server ./mvnw spring-boot:run


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //
public class PagesController {

    @GetMapping("/hello")       //path that will access the below method
    public String helloWorld() {
        return "Hello World";
    }
    //GET /tasks HTTP/1.1
    //Host: example.com
    @GetMapping("/tasks")
    public String getTasks() {
        return "These are tasks";
    }
        //test method with curl -X GET http://localhost:8080/tasks
    //POST /tasks HTTP/1.1
    //Host: example.com
    @PostMapping("/tasks")
    public String createTask() {
        return "You just POSTed to /tasks";
    }
        //curl -X POST http://localhost:8080/tasks
    @GetMapping("/math/pi")
    public double returnPi() {
        return  3.141592653589793;
    }
}
