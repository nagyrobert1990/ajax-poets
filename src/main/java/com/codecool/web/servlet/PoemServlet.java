package com.codecool.web.servlet;

import com.codecool.web.dao.PoemDao;
import com.codecool.web.dao.database.DatabasePoemDao;
import com.codecool.web.model.Poem;
import com.codecool.web.model.Poet;
import com.codecool.web.service.PoemService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimplePoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/poem")
public class PoemServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DatabasePoemDao(connection);
            PoemService poemService = new SimplePoemService(poemDao);
            int loggedinPoetId = ((Poet)req.getSession().getAttribute("poet")).getId();
            HttpSession session = req.getSession();

            String poemId = req.getParameter("id");
            session.setAttribute("poemId", poemId);

            Poem poem = poemService.getPoemFromPoetById(loggedinPoetId, Integer.parseInt(poemId));

            sendMessage(resp, HttpServletResponse.SC_OK, poem);
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        } catch (Throwable throwable) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, throwable.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DatabasePoemDao(connection);
            PoemService poemService = new SimplePoemService(poemDao);

            String subString = req.getParameter("substring");
            String poemId = req.getSession().getAttribute("poemId").toString();

            int numberOfSubstring = poemService.getNumberOfSubstringsInPoem(Integer.parseInt(poemId), subString);
            sendMessage(resp, HttpServletResponse.SC_OK, numberOfSubstring);
        } catch (SQLException e) {
            handleSqlError(resp, e);
        } catch (ServiceException e) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Throwable throwable) {
            sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, throwable.getMessage());
        }
    }
}
