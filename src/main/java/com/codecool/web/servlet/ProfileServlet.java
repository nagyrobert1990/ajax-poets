package com.codecool.web.servlet;

import com.codecool.web.model.Poet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/protected/profile")
public final class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Poet poet = (Poet) req.getSession().getAttribute("poet");
        req.setAttribute("poet", poet);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
