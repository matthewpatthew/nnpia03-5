package com.example.nnpiacv02.service.impl;


import com.example.nnpiacv02.service.RandomModel;
import com.example.nnpiacv02.service.RandomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RandomServiceImpl implements RandomService {

    public RandomServiceImpl(){
        objectMap.put(1, new RandomModel(1, "První", "Popis1"));
        objectMap.put(2, new RandomModel(2, "Druhý", "Popis2"));
        objectMap.put(3, new RandomModel(3, "Třetí", "Popis3"));
    }

    private final HashMap<Integer, RandomModel> objectMap = new HashMap<>();
    @Override
    public List<RandomModel> getAllObjects() {
        return new ArrayList<>(objectMap.values());
    }

    @Override
    public RandomModel getObjectById(int id) {
        return objectMap.get(id);
    }
}
