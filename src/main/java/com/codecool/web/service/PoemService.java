package com.codecool.web.service;

import com.codecool.web.model.Poem;

import java.util.List;

public interface PoemService {
    List<Poem> getAllPoemsByPoetId(int poetId) throws Throwable;
    Poem getPoemFromPoetById(int poetId, int poemId) throws Throwable;
    int getNumberOfSubstringsInPoem(int poemId, String substring) throws Throwable;
}
