package ru.otus.example.mongodbdemo.utils;

import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RawResultPrinterImpl implements RawResultPrinter {
    @SuppressWarnings("unchecked")
    @Override
    public void prettyPrintRawResult(Document document) {
        List<Document> results = (List<Document>) document.get("results");
        String resultsAsString = results.stream().map(Document::toString).collect(Collectors.joining("\n"));
        System.out.println(resultsAsString);
    }
}
