package com.example.nnpiacv02.controller;

import org.springframework.web.bind.annotation.*;

@RestController
//Controller je obecný typ třídy v Spring MVC frameworku, který zpracovává HTTP požadavky.
//RestController je specializovaný typ Controller, který je určen specificky pro vytváření
// REST API, které poskytuje datové služby prostřednictvím HTTP protokolu.
public class HelloController {

    @GetMapping("")
    public String helloWorld() {
        return "Hello world from Spring Boot application.";
    }

    @GetMapping("{parameter}")
    public String paramEndpoint(@PathVariable("parameter") String parameter) {
        return "Parametr je: " + parameter;
    }

    @RequestMapping(value = "/myPath/{path}", method = RequestMethod.GET)
    public String rmParamEndpoint(@PathVariable("path") String path) {
        return "Requested path: " + path;
    }

    @GetMapping("/query") //http://localhost:8080/api/v1/query?name=rndmsg
    public String queryParam(@RequestParam("name") String message) {
        return "Hello world from query with param, message: " + message;
    }


}
