package com.core;

import com.core.calculator.Calculator;
import com.core.calculator.Operator;
import com.core.calculator.exceptions.InvalidCommandException;

// 写一个计算器类（Calculator），可以实现两个数的加、减、乘、除运算，并可以进行undo和redo操作
public class Main {

    public static void main(String[] args) throws Exception {
        Calculator c = Calculator.of();
        c.acceptNum(2);
        c.acceptOp(Operator.add);
        c.acceptNum(3);
        c.acceptOp(Operator.mul);
        c.acceptNum(10);
        System.out.printf("result: %s", c.getResult());
    }
}