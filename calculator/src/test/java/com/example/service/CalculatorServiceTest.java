package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    void testSum() {
        BigDecimal result = calculatorService.sum(new BigDecimal("5"), new BigDecimal("3"));
        assertEquals(new BigDecimal("8"), result);
    }

    @Test
    void testSubtract() {
        BigDecimal result = calculatorService.subtract(new BigDecimal("5"), new BigDecimal("3"));
        assertEquals(new BigDecimal("2"), result);
    }

    @Test
    void testMultiply() {
        BigDecimal result = calculatorService.multiply(new BigDecimal("5"), new BigDecimal("3"));
        assertEquals(new BigDecimal("15"), result);
    }

    @Test
    void testDivide() {
        BigDecimal result = calculatorService.divide(new BigDecimal("6"), new BigDecimal("2"));
        assertEquals(new BigDecimal("3.0000000000"), result);
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> 
            calculatorService.divide(new BigDecimal("5"), BigDecimal.ZERO)
        );
    }
}
