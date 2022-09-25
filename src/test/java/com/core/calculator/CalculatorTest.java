package com.core.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    public void testSimpleCase() throws Exception {
        Calculator c = Calculator.of();
        c.acceptNum(2);
        c.acceptOp(Operator.mul);
        c.acceptNum(5);
        assertEquals(c.getResult(), 2 * 5);

        c.clear();
        c.acceptNum(1);
        c.acceptOp(Operator.div);
        c.acceptNum(10);
        assertEquals(c.getResult(), 1f / 10f);

        c.clear();
        c.acceptNum(1);
        c.acceptOp(Operator.add);
        c.acceptNum(2);
        c.acceptOp(Operator.add);
        c.acceptNum(3);
        c.acceptOp(Operator.minus);
        c.acceptNum(4);
        c.acceptOp(Operator.add);
        c.acceptNum(5);
        assertEquals(c.getResult(), 1 + 2 + 3 - 4 + 5);
    }

    @Test
    public void testRedo() throws Exception {
        Calculator c = Calculator.of();

        c.acceptNum(10);
        c.acceptOp(Operator.mul);
        c.acceptNum(5);
        c.redo();
        c.redo();
        c.redo();
        assertEquals(c.getResult(), 10 * 5 * 5 * 5 * 5);

        c.clear();
        c.acceptNum(10);
        c.acceptOp(Operator.add);
        c.acceptNum(20);
        c.redo();
        c.acceptOp(Operator.minus);
        c.acceptNum(5);
        c.redo();
        assertEquals(c.getResult(), 10+20+20-5-5);
    }
}