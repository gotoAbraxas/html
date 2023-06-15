package com.playdata.todo.servlet;

import com.playdata.todo.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  req.getRequestDispatcher("views/main.html").forward(req,resp);

        if (UserDao.me!=null) {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>main</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "        <h1>" + UserDao.me.getName() + "환영합니다.</h1>\n" +
                    "<img src=\"/img/img.png\">\n" +
                    "</body>\n" +
                    "</html>");
            writer.close();
        }
        else{
            resp.sendRedirect("/login");
        }
    }
}
