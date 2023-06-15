package com.playdata.todo.dao;

import com.playdata.todo.config.JdbcConnection;
import com.playdata.todo.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao { // 데이터 엑세스 오브젝트
    public  void insert(User user){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into users(username, password, name)"+
                "value(?,?,?)";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
