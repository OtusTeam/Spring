package ru.otus.model;

public class DivisionEquation extends Equation {

    public DivisionEquation(int leftPart, int rightPart) {
        super(leftPart, rightPart);
    }

    @Override
    protected int calcResult() {
        return leftPart / rightPart;
    }

    @Override
    public String toString() {
        return String.format("%d / %d = ?", leftPart, rightPart);
    }
}
