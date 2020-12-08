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
    public String getRectangleVolume(@PathVariable Map dimensions) {    //can specify Map data types like Map<String, Integer>
        int length = Integer.parseInt(dimensions.get("length").toString());
        int width = Integer.parseInt(dimensions.get("width").toString());
        int height = Integer.parseInt(dimensions.get("height").toString());
        int volume = length * width * height;
        return String.format("The volume of a %sx%sx%s rectangle is %s",length, width, height, volume);
    }
    //curl -i -X PATCH "localhost:8080/math/volume/3/4/5"

    @PostMapping("/math/area")
    public String getCircleArea(@RequestParam Map<String, String > body) {
//        return body.toString();
        if(body.get("type").equals("circle")){
            if (body.get("radius") != null) {
                double radius = Double.parseDouble(body.get("radius"));
                double area = returnPi() * Math.pow(radius, 2);
                return String.format("Area of a circle with a radius of %s is %s", radius, area);
            }else return "Invalid";

        } else if(body.get("type").equals("rectangle")) {
            if(body.get("width") != null && body.get("height") != null) {
                int width = Integer.parseInt(body.get("width"));
                int height = Integer.parseInt(body.get("height"));
                int area = Math.multiplyExact(width, height);
                return String.format("Area of a %sx%s rectangle is %s", width, height, area);
            }else return "Invalid";
        } else return "uh oh";
    }
    //curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'type=circle&radius=4' "http://localhost:8080/math/area"


}
