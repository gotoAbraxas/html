package com.playdata.todo.servlet;

import com.playdata.todo.config.History;
import com.playdata.todo.dao.TodoDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String uname = (String) session.getAttribute("uname");
        req.setAttribute("uname",uname);

      String keyword = req.getParameter("keyword");

        if(keyword == null) {
            req.setAttribute("todolist", new TodoDao().findAll());
        } else if (keyword != null) {
            req.setAttribute("todolist", new TodoDao().findSometing(keyword));

        }


        req.getRequestDispatcher("views/main.jsp").forward(req,resp);


//        History.setHistory(req, resp);
//        Cookie[] cookies = req.getCookies();
//        String uid = null;
//        String uname = null;
//        for (Cookie c:
//             cookies) {
//            if(c.getName().equals("uid")) uid = c.getValue();
//            if(c.getName().equals("uname")) uname = c.getValue();
//        }
//        HttpSession session = req.getSession();
//        if (session.getAttribute("uname") != null) {
//
//            resp.setContentType("text/html;charset=utf-8");
//            PrintWriter writer = resp.getWriter();
//            writer.println("<!DOCTYPE html>\n" +
//                    "<html lang=\"en\">\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <title>main</title>\n" +
//                    "</head>\n" +
//                    "<body>\n" +
//                    "        <h1>" +  "환영합니다."+ session.getAttribute("uname")+ "</h1>\n" +
//                    "<a href=\"/back\">back</a>"+
//            //        "<a href=\"/todo\">todo</a>"+
//                    "<a href=\"/userupdate\">userupdate</a>"+
//                    "<img src=\"/img/img.png\">\n" +
//                    "</body>\n" +
//                    "</html>");
//            writer.close();
//        }
//        else{
//            resp.sendRedirect("/login");
//        }
    }

    }

