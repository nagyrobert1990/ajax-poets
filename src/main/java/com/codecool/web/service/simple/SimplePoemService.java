package com.codecool.web.service.simple;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;
import com.codecool.web.service.PoemService;
import com.codecool.web.service.exception.ServiceException;

import java.util.List;

public class SimplePoemService implements PoemService {

    private final PoemDao poemDao;

    public SimplePoemService(PoemDao poemDao) {
        this.poemDao = poemDao;
    }

    @Override
    public List<Poem> getAllPoemsByPoetId(int poetId) throws Throwable {
        return poemDao.findPoemsByPoetId(poetId);
    }

    @Override
    public Poem getPoemFromPoetById(int poetId, int poemId) throws Throwable {
        try {
            Poem poem = poemDao.findPoemFromPoetById(poetId, poemId);
            if (poem.equals(null)){
                throw new ServiceException("Poem not found!");
            }
            return poem;
        } catch (NumberFormatException ne) {
            throw new ServiceException("Integer number needed!");
        } catch (IllegalArgumentException ie) {
            throw new ServiceException(ie.getMessage());
        }
    }

    @Override
    public int getNumberOfSubstringsInPoem(int poemId, String subString) throws Throwable {
        try {
            int numOfSubstrings = poemDao.countNumberOfSubstringsInPoem(poemId, subString);
            if (numOfSubstrings == -1) {
                throw new ServiceException("Invalid database operation!");
            }
            if (subString == null || subString.equals("") ) {
                throw new ServiceException("Substring cannot be empty!");
            }
            return numOfSubstrings;
        } catch (NumberFormatException ex) {
            throw new ServiceException("Poem id must be an integer!");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
