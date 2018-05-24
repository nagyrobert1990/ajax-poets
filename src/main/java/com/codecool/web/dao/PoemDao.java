package com.codecool.web.dao;

import com.codecool.web.model.Poem;

import java.sql.SQLException;
import java.util.List;

public interface PoemDao {
    List<Poem> findPoemsByPoetId(int poetId) throws SQLException;
    Poem findPoemFromPoetById(int poetId, int poemId) throws SQLException;
    int countNumberOfSubstringsInPoem(int poemId, String substring) throws SQLException;
}
