package com.example.nnpiacv02.controller;

import com.example.nnpiacv02.service.RandomModel;
import com.example.nnpiacv02.service.RandomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomController {

    private final RandomService randomService;

    public RandomController(RandomService randomService) {
        this.randomService = randomService;
    }

    @GetMapping("/objects")
    public List<RandomModel> getAllObjects() {
        return randomService.getAllObjects();
    }

    @GetMapping("/objects/{id}")
    public RandomModel getObjectById(@PathVariable int id) {
        return randomService.getObjectById(id);
    }
}
