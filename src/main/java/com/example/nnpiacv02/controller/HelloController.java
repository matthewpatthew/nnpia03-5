package com.example.nnpiacv02.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("${app.basePath}")
    public String helloWorld() {
        return "Hello world from Spring Boot application.";
    }

    @GetMapping("${app.basePath}{parameter}")
    public String paramEndpoint(@PathVariable("parameter") String parameter){
        return "Parametr je: "  + parameter;
    }

    @RequestMapping(value = "${app.basePath}/myPath/{path}", method = RequestMethod.GET)
    public String rmParamEndpoint(@PathVariable("path") String path){
        return "Requested path: " + path;
    }

    @GetMapping("${app.basePath}/query") //http://localhost:8080/api/v1/query?query=rndmsg
    public String queryParam(@RequestParam("query") String message){
        return "Hello world from query with param, message: " + message;
    }










}
