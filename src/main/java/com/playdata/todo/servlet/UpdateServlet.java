package com.playdata.todo.servlet;

import com.playdata.todo.config.History;
import com.playdata.todo.dao.UserDao;
import com.playdata.todo.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("views/userupdate.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int id = (Integer) session.getAttribute("uid");

        String username = req.getParameter("name");
        String password = req.getParameter("password");

        new UserDao().userUpdate(id,username,password);
//        Cookie cookie = new Cookie("uid",user.getId().toString());
//        Cookie cookie2 = new Cookie("uname",user.getName());
//        cookie.setMaxAge(100);
//
//        resp.addCookie(cookie);
//        resp.addCookie(cookie2);

            resp.sendRedirect("/main");


}
}
