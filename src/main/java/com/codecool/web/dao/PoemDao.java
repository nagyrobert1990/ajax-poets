package com.codecool.web.dao;

import com.codecool.web.model.Poem;

import java.util.List;

public interface PoemDao {
    List<Poem> findPoemsByPoetId(int poetId) throws Throwable;
    Poem findPoemFromPoetById(int poetId, int poemId) throws Throwable;
    int countNumberOfSubstringsInPoem(int poemId, String substring) throws Throwable;
}
