package com.playdata.todo.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class History {
    public static void setHistory(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        String requestURI = req.getRequestURI();
        String history = "history";
        int index = 0;
        if(cookies == null) {
            resp.addCookie(new Cookie(history + index , requestURI));
            return;
        }
        int max = 0;
        for (int i  = 0; i< cookies.length; i++) {
            Cookie c = cookies[i];
            if(c.getName().contains(history)
                    &&
                    index < Integer.parseInt(c.getName().replace(history,"")
                    )) {
                index = Integer.parseInt(
                        c.getName().replace(history, "")
                );
                max = i;
            }
        }
        index++;
        if(!cookies[max].getValue().equals(requestURI))
            resp.addCookie(new Cookie(history + index , requestURI));

    }
    public static void back(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        String history = "history";
        int index = 0;
        int max = 0;

        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().contains(history)){
                index = Integer.parseInt(
                        c.getName().replace(history, "")
                );
                max = i;
            }
        }
        int value = Integer.parseInt(cookies[max].getName().replace(history,""));
        int value2 = value -1;
        String lang = history + value2;



        resp.sendRedirect(cookies[max].getValue());


        // TODO : 뒤로가기
        // 하나도 없으면 로그인으로 가고
        // 뒤로가기 하면 최근것 만 찾아갈건데 이걸 개선해야함.
    }
}