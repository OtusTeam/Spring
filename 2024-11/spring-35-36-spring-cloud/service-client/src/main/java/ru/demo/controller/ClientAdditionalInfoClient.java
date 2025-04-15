package ru.demo.controller;

import static ru.demo.filter.MdcFilter.HEADER_X_REQUEST_ID;

import java.net.URI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.demo.model.ClientData;

public interface ClientAdditionalInfoClient {

    @GetMapping(value = "/additional-info", consumes = "application/json")
    ClientData additionalInfo(
            @RequestHeader(HEADER_X_REQUEST_ID) String xRequestId, URI baseUri, @RequestParam("name") String nameVal);
}
