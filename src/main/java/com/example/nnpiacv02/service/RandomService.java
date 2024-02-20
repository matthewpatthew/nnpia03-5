package com.example.nnpiacv02.service;

import java.util.List;

public interface RandomService {

    List<RandomModel> getAllObjects();
    RandomModel getObjectById(int id);
}
