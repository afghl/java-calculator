package com.core.calculator;

import com.core.calculator.exceptions.CalculateException;
import com.core.calculator.exceptions.InvalidCommandException;

public class Calculator {

    private float result;
    private Operator lastOp;
    private float lastNum;
    private boolean isWaitingNum;

    public static Calculator of() {
        Calculator c = new Calculator();
        c.init();
        return c;
    }

    private void init() {
        lastOp = null;
        lastNum = 0;
        isWaitingNum = true;
    }

    public void acceptNum(float num) throws InvalidCommandException, CalculateException {
        if (!this.isWaitingNum) {
            throw new InvalidCommandException();
        }
        lastNum = num;
        result = calc(result, lastNum, getOp());
        isWaitingNum = false;
    }

    public void acceptOp(Operator op) throws InvalidCommandException {
        if (this.isWaitingNum || op == null) {
            throw new InvalidCommandException();
        }
        lastOp = op;
        this.isWaitingNum = true;
    }

    public float getResult() {
        return result;
    }

    public void redo() throws InvalidCommandException, CalculateException {
        if (isWaitingNum || lastOp == null) {
            throw new InvalidCommandException();
        }
        result = calc(result, lastNum, getOp());
    }

    public Operator getOp() {
        return lastOp == null ? Operator.add : lastOp;
    }

    public void clear() {
        this.result = 0;
        init();
    }

    private float calc(float num1, float num2, Operator op) throws CalculateException {
        if (op == Operator.add) {
            return num1 + num2;
        } else if (op == Operator.minus) {
            return num1 - num2;
        } else if (op == Operator.mul) {
            return num1 * num2;
        } else if (op == Operator.div && num2 != 0) {
            return num1 / num2;
        } else {
            throw new CalculateException();
        }
    }
}
