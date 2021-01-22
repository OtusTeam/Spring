package ru.otus.example.mongodbdemo.utils;

import com.google.gson.*;
import com.mongodb.DBRef;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RawResultPrinterImpl implements RawResultPrinter {
    @SuppressWarnings("unchecked")
    @Override
    public void prettyPrintRawResult(Document document) {
        List<Document> results = (List<Document>) document.get("results");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(ObjectId.class, (JsonSerializer<ObjectId>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.toHexString()))
                .registerTypeAdapter(DBRef.class, (JsonSerializer<DBRef>) (src, typeOfSrc, context) -> {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("$ref", new JsonPrimitive(src.getCollectionName()));
                    jsonObject.add("$id", new JsonPrimitive(src.getId().toString()));
                    return jsonObject;
                })
                .create();

        String resultsAsString = gson.toJson(results);
        //String resultsAsString = results.stream().map(Document::toString).collect(Collectors.joining("\n"));
        System.out.println(resultsAsString);
    }
}
