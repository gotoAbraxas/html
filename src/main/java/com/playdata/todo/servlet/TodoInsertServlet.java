package com.playdata.todo.servlet;

import com.playdata.todo.dao.TodoDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TodoInsertServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/todos.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = (Integer) req.getSession().getAttribute("uid");
        String content= req.getParameter("content");

        new TodoDao().insert(uid,content);

        resp.sendRedirect("/main?keyword="+content);
    }
}
