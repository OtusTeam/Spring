package ru.otus.model;

public abstract class Equation {
    protected final int leftPart;
    protected final int rightPart;
    protected final int result;

    public Equation(int leftPart, int rightPart) {
        this.leftPart = leftPart;
        this.rightPart = rightPart;
        this.result = calcResult();
    }

    protected abstract int calcResult();

    public int getLeftPart() {
        return leftPart;
    }

    public int getRightPart() {
        return rightPart;
    }

    public int getResult() {
        return result;
    }
}
