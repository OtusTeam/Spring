package kz.yyediluly.dao;


import kz.yyediluly.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

public class QuestionDaoImpl implements QuestionDao{

    private Resource csv;

    public List<Question> getQuestions() throws IOException {
       List<Question> list = new ArrayList<>();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("csv/Questions.csv");
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
            for(String r: data.split("\\r?\\n")) {
                list.add(new Question(r));
            }
        } catch (Exception e) {
            System.out.println("ExceptionDAO");
        }

       return list;
    }

    public void setCsv(Resource csv) {
    }

    public Resource getCsv() {
        return csv;
    }
}

