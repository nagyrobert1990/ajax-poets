package com.codecool.web.dao.database;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.model.Poem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabasePoemDao extends AbstractDao implements PoemDao {

    public DatabasePoemDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Poem> findPoemsByPoetId(int poetId) throws Throwable {
        List<Poem> poems = new ArrayList<>();
        String sql = "SELECT id, title, content, publish_date WHERE poet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, poetId);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    poems.add(fetchPoem(resultSet));
                }
            }
        }
        return poems;
    }

    @Override
    public Poem findPoemFromPoetById(int poetId, int poemId) throws Throwable {
        String sql = "SELECT is, title, content, publish_date WHERE poet_id = ? AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, poetId);
            statement.setInt(2, poemId);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return fetchPoem(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public int countNumberOfSubstringsInPoem(int poemId, String substring) throws Throwable {
        String sql = "SELECT length((SELECT content FROM works WHERE id = ?)) " +
                "- length(replace((SELECT content FROM works WHERE id = ?), ?, '')) " +
                "AS numberOfSubstrings";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, poemId);
            statement.setInt(2, poemId);
            statement.setString(3, substring);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return resultSet.getInt("numberOfSubstrings");
                }
            }
            return -1;
        }
    }

    private Poem fetchPoem(ResultSet resultSet) throws Throwable {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        int publishDate = resultSet.getInt("publishDate");
        return new Poem(id, title, content, publishDate);
    }
}
