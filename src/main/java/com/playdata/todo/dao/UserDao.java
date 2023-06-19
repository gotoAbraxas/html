package com.playdata.todo.dao;

import com.playdata.todo.config.JdbcConnection;
import com.playdata.todo.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public User login(String id, String password){
        List<User> users = new ArrayList<User>();

        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select id,username,name,create_at "
                +"from users "
                +"where username = ? and password = ?";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                users.add(makeUser(resultSet));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        if(users.size()!=0){
            return users.get(0);
        }
        return users.get(0);
    }

    private User makeUser(ResultSet resultSet){
        Integer id;
        String password, username, name, createAt;
        try {
            id = resultSet.getInt("id");
        }catch (SQLException e) {
            id = null;
        }
        try {
            password = resultSet.getString("password");
        }catch (SQLException e) {
            password = null;
        }
        try {
            username = resultSet.getString("username");
        }catch (SQLException e) {
            username = null;
        }
        try {
            name = resultSet.getString("name");
        }catch (SQLException e) {
            name = null;
        }
        try {
            createAt = resultSet.getString("createAt");
        }catch (SQLException e) {
            createAt = null;
        }
        return new User(id,username,name,password,createAt);
    }
    public  void userUpdate(int id,String name,String password){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "update users "+
                "set password = ?, name = ? "+
                "where id = ?";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,password);
            pst.setString(2, name);
            pst.setInt(3, id);
            pst.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
