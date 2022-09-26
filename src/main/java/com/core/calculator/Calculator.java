package com.core.calculator;

import com.core.calculator.exceptions.CalculateException;
import com.core.calculator.exceptions.InvalidCommandException;
import java.util.Stack;

public class Calculator {

    private Stack<Float> results;
    private Operator lastOp;
    private float lastNum;
    private boolean isWaitingNum;

    public static Calculator of() {
        Calculator c = new Calculator();
        c.init();
        return c;
    }

    private void init() {
        results = new Stack<>();
        results.add((float) 0);
        lastOp = null;
        lastNum = 0;
        isWaitingNum = true;
    }

    public void acceptNum(float num) throws InvalidCommandException, CalculateException {
        if (!this.isWaitingNum) {
            throw new InvalidCommandException("cannot accept number");
        }
        lastNum = num;
        float r = calc(results.peek(), lastNum, getOp());
        results.push(r);
        isWaitingNum = false;
    }

    public void acceptOp(Operator op) throws InvalidCommandException {
        if (this.isWaitingNum || op == null) {
            throw new InvalidCommandException("cannot accept operation, waiting for a number to accept");
        }
        lastOp = op;
        this.isWaitingNum = true;
    }

    public float getResult() {
        if (!results.empty()) {
            return results.peek();
        } else {
            return 0;
        }
    }

    public void redo() throws InvalidCommandException, CalculateException {
        if (lastOp == null) {
            return;
        }
        if (isWaitingNum) {
            throw new InvalidCommandException("cannot redo, waiting for a number to accept");
        }
        float r = calc(results.peek(), lastNum, getOp());
        results.push(r);
    }

    public void undo() throws InvalidCommandException {
        if (results.size() <= 1) {
            return;
        }
        if (isWaitingNum) {
            throw new InvalidCommandException("cannot undo, waiting for a number to accept");
        }
        // remove top of stack
        results.pop();
    }

    public Operator getOp() {
        return lastOp == null ? Operator.add : lastOp;
    }

    public void clear() {
        init();
    }

    private float calc(float num1, float num2, Operator op) throws CalculateException, ArithmeticException {
        float r;
        if (op == Operator.add) {
            r = num1 + num2;
        } else if (op == Operator.minus) {
            r = num1 - num2;
        } else if (op == Operator.mul) {
            r = num1 * num2;
        } else if (op == Operator.div) {
            if (num2 == 0) {
                throw new ArithmeticException("by zero");
            }
            r = num1 / num2;
        } else {
            throw new CalculateException("unknown operation");
        }
        return r;
    }
}
