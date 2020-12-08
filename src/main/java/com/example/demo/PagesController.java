package com.example.demo;
//to run the server ./mvnw spring-boot:run


import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    //curl -i localhost:8080/math/pi
    @GetMapping("/math/calculate")
    public String getIndividualParams(@RequestParam(defaultValue = "add") String operation,
                                      @RequestParam int x,
                                      @RequestParam int y) {
        String operator = "+";
        if(operation.equals("add")) {
            operator = "+";
        } else if (operation.equals("subtract")) {
            operator = "-";
        } else if (operation.equals("multiply")) {
            operator = "*";
        } else if (operation.equals("divide")) {
            operator = "/";
        }

        return x + " " + operator + " " + y + " = " + MathService.calculate(operation, x, y);
//        int result;
//        String operator;
//        if (operation.equals("add")) {
//            result = x + y;
//            operator = "+";
//        } else  if(operation.equals("multiply")) {
//            result = x * y;
//            operator = "*";
//        } else  if(operation.equals("subtract")) {
//            result = x - y;
//            operator = "-";
//        } else  if(operation.equals("divide")) {
//            result = x / y;
//            operator = "/";
//        } else {
//           return x + y + "fool" + operation;
//        }
//        return String.format("%s %s %s = %s", x, operator, y, result);




    }
    @PostMapping("/math/sum")
    public String getMultipleParams(@RequestParam Integer[] n) {
        StringBuilder output = new StringBuilder();
        output.append(n[0]);
        for(int i = 1; i < n.length; i ++) {
            output.append(" + " + n[i]);
        }
        output.append(" = " + MathService.sum(n).toString());
        return output.toString();
    }


    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String getRectangleVolume(@PathVariable Map dimensions) {
        int length = Integer.parseInt(dimensions.get("length").toString());
        int width = Integer.parseInt(dimensions.get("width").toString());
        int height = Integer.parseInt(dimensions.get("height").toString());
        int volume = length * width * height;
//        return "The volume of a " String.valueOf(volume);
        return String.format("The volume of a %sx%sx%s rectangle is %s",length, width, height, volume);
    }

}
