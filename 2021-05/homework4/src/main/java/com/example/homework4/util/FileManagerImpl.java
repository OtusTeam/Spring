package com.example.homework4.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FileManagerImpl implements FileManager {
    private final String fileName;

    public FileManagerImpl(@Value("${application.locale}")String tag, @Value("${application.quiz-file}") String quizFile, @Value("${application.quiz-file-ru}") String quizFileRu) {
        this.fileName = tag.equals("ru-RU")?quizFileRu:quizFile;
    }

    @Override
    public InputStream getFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream(fileName);
        return stream;
    }
}
