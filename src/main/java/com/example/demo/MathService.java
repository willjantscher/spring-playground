package com.example.demo;

public class MathService {
    public static Integer calculate(String operation, int x, int y) {
        if(operation.equals("subtract")) {
            return Math.subtractExact(x, y);
        } else  if(operation.equals("multiply")) {
            return Math.multiplyExact(x, y);
        } else if(operation.equals("divide")) {
            return Math.floorDiv(x, y);
        } else {
            return Math.addExact(x, y);
        }
    }
    public static Integer sum(Integer[] array) {
        Integer sum = 0;
        for(int i = 0; i < array.length; i ++) {
            sum += array[i];
        }
        return sum;
    }
}
