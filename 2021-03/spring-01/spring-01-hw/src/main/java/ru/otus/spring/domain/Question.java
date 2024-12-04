package ru.otus.spring.domain;

public class Question {
    private String que;
    private Integer answ;

    public String getQue() {
        return que;
    }

    public Integer getAnsw() {
        return answ;
    }

    public Question(String que, Integer answ) {
        this.que = que;
        this.answ = answ;
    }
}
