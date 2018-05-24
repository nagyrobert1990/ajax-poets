package com.codecool.web.dao;

import com.codecool.web.model.Poet;

import java.sql.SQLException;

public interface PoetDao {
    Poet findByEmail(String email) throws SQLException;
}
