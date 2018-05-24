package com.codecool.web.service;

import com.codecool.web.model.Poem;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface PoemService {
    List<Poem> getAllPoemsByPoetId(int poetId) throws SQLException;
    Poem getPoemFromPoetById(int poetId, int poemId) throws SQLException, ServiceException;
    int getNumberOfSubstringsInPoem(int poemId, String substring) throws SQLException, ServiceException;
}
