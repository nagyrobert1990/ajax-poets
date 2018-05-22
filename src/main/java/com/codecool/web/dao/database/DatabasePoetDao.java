package com.codecool.web.dao.database;

import com.codecool.web.dao.PoetDao;
import com.codecool.web.model.Poet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabasePoetDao extends AbstractDao implements PoetDao {

    public DatabasePoetDao(Connection connection) {
        super(connection);
    }

    @Override
    public Poet findByEmail(String email) throws Throwable {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty!");
        }
        String sql = "SELECT id, name, email, password FROM poets WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return fetchPoet(resultSet);
                }
            }
        }
        return null;
    }

    private Poet fetchPoet(ResultSet resultSet) throws Throwable {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Poet(id, name, email, password);
    }
}
