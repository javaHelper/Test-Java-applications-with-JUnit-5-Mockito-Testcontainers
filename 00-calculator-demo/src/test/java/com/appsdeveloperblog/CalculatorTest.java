package com.appsdeveloperblog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test math operation in Calculator class")
class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    static void setup() {
        System.out.println("Executing @BeforeAll method");
    }

    @AfterAll
    static void cleanup() {
        System.out.println("Executing @AfterAll method");
    }

    @BeforeEach
    void beforeEachTestMethod() {
        calculator = new Calculator();
        System.out.println("Executing @BeforeEach method");
    }

    @DisplayName("Test 4/2 = 2")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {
        int result = calculator.integerDivision(4, 2);
        assertEquals(2, result, "4/2 did not produce 2");
    }

    @DisplayName("Divided by zero")
    @Test
    void testIntegerDivision_WhenFourDividedIsDividedByZero_ShouldThrowArithmeticException() {
        int dividend = 4;
        int divisor = 0;
        String expectedResponse = "/ by zero";

        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            calculator.integerDivision(dividend, divisor);
        }, "Division by zero would have thrown an Arithmetic exception");

        assertEquals(expectedResponse, actualException.getMessage());
    }


    @DisplayName("integerSubtraction:Test integer subtration [minuend, subtrahend, expectedResult]")
    @MethodSource("integerSubtractionInputParameters")
    @ParameterizedTest
    void integerSubtraction(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test " + minuend + "-" + subtrahend + "=" + expectedResult);

        int actualResult = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, actualResult,
                minuend + "-" + subtrahend + "did not produce " + expectedResult);
    }

    private static Stream<Arguments> integerSubtractionInputParameters() {
        return Stream.of(
                Arguments.of(33, 1, 32),
                Arguments.of(24, 1, 23),
                Arguments.of(54, 1, 53)
        );
    }


    @DisplayName("integerSubtraction2: Test integer subtration [minuend, subtrahend, expectedResult]")
    @MethodSource()
    @ParameterizedTest
    void integerSubtraction2(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test " + minuend + "-" + subtrahend + "=" + expectedResult);

        int actualResult = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, actualResult,
                minuend + "-" + subtrahend + "did not produce " + expectedResult);
    }

    private static Stream<Arguments> integerSubtraction2() {
        return Stream.of(
                Arguments.of(33, 1, 32),
                Arguments.of(24, 1, 23),
                Arguments.of(54, 1, 53)
        );
    }

    // CSVSource
    @DisplayName("CsvSource: Test integer subtration [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
    @CsvSource({
            "33,1,32",
            "44,1,43",
            "54,1,53"
    })
    void integerSubtractionUsingCSVSource(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test " + minuend + "-" + subtrahend + "=" + expectedResult);

        int actualResult = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, actualResult,
                minuend + "-" + subtrahend + "did not produce " + expectedResult);
    }


    @DisplayName("CsvFileSource: Test integer subtration [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
    @CsvFileSource(resources = "/integerDivision.csv")
    void integerSubtractionUsingCSVFile(int minuend, int subtrahend, int expectedResult) {
        System.out.println("Running Test " + minuend + "-" + subtrahend + "=" + expectedResult);

        int actualResult = calculator.integerSubtraction(minuend, subtrahend);
        assertEquals(expectedResult, actualResult,
                minuend + "-" + subtrahend + "did not produce " + expectedResult);
    }


    @ParameterizedTest
    @ValueSource(strings = {"John","kate","Alice"})
    void valueSourceDemonstration(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName);
    }
}