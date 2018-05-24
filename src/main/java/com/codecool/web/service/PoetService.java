package com.codecool.web.service;

import com.codecool.web.model.Poet;

public interface PoetService {

    Poet loginPoet(String email, String password) throws Throwable;
}
