package kz.yyediluly.dao;

import kz.yyediluly.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    public List<Question> getQuestions() throws IOException;
}
