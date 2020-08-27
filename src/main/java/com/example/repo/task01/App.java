package com.example.repo.task01;

import com.example.repo.task01.core.Calculator;

public class App {

    public static void main(final String[] args) {
        final double result = new Calculator().calculate();
        System.out.println(result);
    }
}
