package com.playdata.todo.servlet;

import com.playdata.todo.dao.TodoDao;
import com.playdata.todo.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TodoUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int id = Integer.parseInt(req.getParameter("id"));

        String content = new TodoDao().findById(id);

        req.setAttribute("todoid",req.getParameter("id"));
        req.setAttribute("content",content);

        req.getRequestDispatcher("/views/todoupdate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId =(Integer) session.getAttribute("uid");

        int id = Integer.parseInt(req.getParameter("id"));
        String content = req.getParameter("content");


        new TodoDao().TodoUpdate(id,content,userId);

        resp.sendRedirect("/main");
    }
}
