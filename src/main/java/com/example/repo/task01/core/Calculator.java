package com.example.repo.task01.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    // регулярка, принимающая один из символов +-*/
    private static final String ALLOWED_OPERATIONS_REGEX = "([+\\-*/]){1}";
    // регулярка, принимающая любое положительное или отрицательное число
    public static final String NUMBER = "-?((\\d+)|(\\d+\\.\\d+))";
    // комбинация регулярок выше с проверкой начала и конца строки
    private static final String EXPRESSION_REGEX = "^" + NUMBER + ALLOWED_OPERATIONS_REGEX + NUMBER + "$";

    public double calculate() {
        System.out.println("Введите выражение:");

        final String expression;
        try (final BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            // считываем строчку и убираем все пробелы
            expression = input.readLine().replaceAll("\\s", "");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        // проверяем, что ввод - валидное выражение
        checkExpressionMatchesRegexOrThrow(expression);

        // считаем
        return doCalculate(expression);
    }

    protected void checkExpressionMatchesRegexOrThrow(final String expression) {
        if (!expression.matches(EXPRESSION_REGEX)) {
            throw new RuntimeException(
                    "Введенное выражение не соответствует паттерну. Выражение: " + expression
            );
        }
    }

    protected double doCalculate(final String expression) {
        final String[] chunks = split(expression, ALLOWED_OPERATIONS_REGEX);
        final double left = parse(chunks[0]);
        final double right = parse(chunks[2]);

        switch (chunks[1]) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                return left / right;
            default:
                throw new RuntimeException("Неожиданная операция: " + chunks[1]);
        }
    }

    private double parse(final String number) {
        return Double.parseDouble(number);
    }

    private String[] split(final CharSequence input, final String pattern) {
        return split(input, Pattern.compile(pattern));
    }

    private String[] split(final CharSequence input, final Pattern pattern) {
        final Matcher matcher = pattern.matcher(input);
        int start = 0;
        final List<String> result = new ArrayList<>();
        if (matcher.find()) {
            result.add(input.subSequence(start, matcher.start()).toString());
            result.add(matcher.group());
            start = matcher.end();
        }
        if (start != input.length()) {
            result.add(input.subSequence(start, input.length()).toString());
        }
        return result.toArray(new String[0]);
    }
}
