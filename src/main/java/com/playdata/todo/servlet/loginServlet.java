package com.playdata.todo.servlet;

import com.playdata.todo.dao.UserDao;
import com.playdata.todo.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        req.getRequestDispatcher("views/join.html").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username =  req.getParameter("username");
        String password = req.getParameter("password");
        resp.setStatus(201);

        UserDao userDao = new UserDao();
        if (userDao.login(username,password)){

            resp.sendRedirect("/main");
        }else{
            resp.sendRedirect("/user");
        }

        //    super.doPost(req, resp);
    }
}
