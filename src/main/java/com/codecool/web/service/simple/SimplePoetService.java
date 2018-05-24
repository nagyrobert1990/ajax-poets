package com.codecool.web.service.simple;

import com.codecool.web.dao.PoetDao;
import com.codecool.web.model.Poet;
import com.codecool.web.service.PoetService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public class SimplePoetService implements PoetService {

    private final PoetDao poetDao;

    public SimplePoetService(PoetDao poetDao) {
        this.poetDao = poetDao;
    }

    @Override
    public Poet loginPoet(String email, String password) throws SQLException, ServiceException {
        try {
            Poet poet = poetDao.findByEmail(email);
            if (poet == null || !poet.getPassword().equals(password)) {
                throw new ServiceException("Bad login");
            }
            return poet;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
