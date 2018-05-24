package com.codecool.web.servlet;

import com.codecool.web.dao.PoetDao;
import com.codecool.web.dao.database.DatabasePoetDao;
import com.codecool.web.model.Poet;
import com.codecool.web.service.PoetService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePoetService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public final class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoetDao poetDao = new DatabasePoetDao(connection);
            PoetService poetService = new SimplePoetService(poetDao);

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Poet poet = poetService.loginPoet(email, password);
            req.getSession().setAttribute("poet", poet);

            sendMessage(resp, HttpServletResponse.SC_OK, poet);
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
