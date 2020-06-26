package ru.otus.spring.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "exam")
public class ExamProps {
    private Long totalquestioncount;
    private Long passquestioncount;
    private String source;

    public Long getTotalquestioncount() {
        return totalquestioncount;
    }

    public void setTotalquestioncount(Long totalquestioncount) {
        this.totalquestioncount = totalquestioncount;
    }

    public Long getPassquestioncount() {
        return passquestioncount;
    }

    public void setPassquestioncount(Long passquestioncount) {
        this.passquestioncount = passquestioncount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
