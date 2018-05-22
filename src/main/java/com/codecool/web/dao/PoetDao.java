package com.codecool.web.dao;

import com.codecool.web.model.Poet;

public interface PoetDao {
    Poet findByEmail(String email) throws Throwable;
}
