package com.example.nnpiacv02.service;

import com.example.nnpiacv02.entity.RandomModel;

import java.util.List;

public interface RandomService {

    List<RandomModel> getAllObjects();

    RandomModel getObjectById(int id);
}
