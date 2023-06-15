package com.playdata.todo.config;

import com.playdata.todo.dao.UserDao;

public class LogoutThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(60000);
            UserDao.me = null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
