package ru.petrelevich.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/hi")
    public String hi(@RequestParam(name = "name") String name) throws UnknownHostException {
        return String.format(
                "Hi, %s. It works, host: %s", name, InetAddress.getLocalHost().getHostName());
    }

    @PostMapping("/response/{name}")
    public Response response(@PathVariable("name") String name, @RequestBody Request params) {
        return new Response(name, String.format("%s-%s", params.param1(), params.param2()));
    }
}
