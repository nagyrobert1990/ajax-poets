package com.codecool.web.service;

import com.codecool.web.model.Poet;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public interface PoetService {

    Poet loginPoet(String email, String password) throws SQLException, ServiceException;
}
