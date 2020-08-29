package com.example.repo.task01.calculator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    private static final int DELTA = 0;

    private final Calculator classUnderTest = new Calculator();

    @Test
    public void testDoCalculate() {
        assertEquals(246, classUnderTest.doCalculate("123+123"), DELTA);
        assertEquals(0, classUnderTest.doCalculate("123-123"), DELTA);
        assertEquals(21, classUnderTest.doCalculate("10.5*2"), DELTA);
        assertEquals(-21, classUnderTest.doCalculate("10.5*-2"), DELTA);
        assertEquals(2.5, classUnderTest.doCalculate("5/2"), DELTA);
        assertEquals(40, classUnderTest.doCalculate("100/2.5"), DELTA);
        assertEquals(-40, classUnderTest.doCalculate("100/-2.5"), DELTA);
    }

    @Test
    public void testCheckExpressionMatchesRegexOrThrow() {
        classUnderTest.checkExpressionMatchesRegexOrThrow("123+123");
        classUnderTest.checkExpressionMatchesRegexOrThrow("-123-123");
        classUnderTest.checkExpressionMatchesRegexOrThrow("-123--123");
        classUnderTest.checkExpressionMatchesRegexOrThrow("-123*123");
        classUnderTest.checkExpressionMatchesRegexOrThrow("-123*-123");
        classUnderTest.checkExpressionMatchesRegexOrThrow("-123.456/-123");
        classUnderTest.checkExpressionMatchesRegexOrThrow("-123.456+123.456");
    }

    @Test(expected = RuntimeException.class)
    public void testCheckExpressionMatchesRegexOrThrow_throwsForNonDigitsInput() {
        classUnderTest.checkExpressionMatchesRegexOrThrow("hey im a bad string");
    }

    @Test(expected = RuntimeException.class)
    public void testCheckExpressionMatchesRegexOrThrow_throwsForBadNumberFormat_1() {
        classUnderTest.checkExpressionMatchesRegexOrThrow("123+123,4");
    }

    @Test(expected = RuntimeException.class)
    public void testCheckExpressionMatchesRegexOrThrow_throwsForBadNumberFormat_2() {
        classUnderTest.checkExpressionMatchesRegexOrThrow("12sd3+123,4");
    }

}