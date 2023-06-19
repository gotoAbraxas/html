package com.playdata.todo.dao;

import com.playdata.todo.config.JdbcConnection;
import com.playdata.todo.dto.Todo;
import com.playdata.todo.dto.TodoJoinUser;
import com.playdata.todo.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {

    public List<TodoJoinUser> findAll(){
        List<TodoJoinUser> list = new ArrayList<TodoJoinUser>();
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "select todos.id," +
                "todos.user_id," +
                "users.name," +
                "todos.create_at," +
                "todos.content," +
                "todos.checked "
                +"from todos "
                +"inner join users on todos.user_id = users.id";
        try{
             PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                list.add(makelist(resultSet));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        if(list.size()!=0){
            return list;
        }
        return null;

    }
    public List<TodoJoinUser> findSometing(String someting){
        List<TodoJoinUser> list = new ArrayList<TodoJoinUser>();
        Connection conn = new JdbcConnection().getJdbc();
       // someting = "%" + someting + "%";

        String sql = "select todos.id," +
                "todos.user_id," +
                "users.name," +
                "todos.create_at," +
                "todos.content," +
                "todos.checked "
                +"from todos "
                +"inner join users on todos.user_id = users.id "
                +"where content like ? ";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%" + someting + "%");

            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                list.add(makelist(resultSet));
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        if(list.size()!=0){
            return list;
        }
        return null;

    }

    public void insert(int uid, String content){

        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into todos(user_id, content,checked) "+
                "value(?,?,false)";
        try{

            //sql 인젝션
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,uid);
            pst.setString(2, content);
            pst.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
        public String findById(int id) {

            Connection conn = new JdbcConnection().getJdbc();
            String sql = "select id, content "
                    + "from todos "
                    + "where id = ?";
            String content = null;


            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet resultSet = pst.executeQuery();
                while (resultSet.next()) {
                    content = resultSet.getString("content");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (content != null) {
                return content;
            }
            return null;
        }

    public  void TodoUpdate(int id,String content,int userId){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "update todos "+
                "set content =  ? "+
                "where id = ? and user_id = ?";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,content);
            pst.setInt(2, id);
            pst.setInt(3, userId);
            pst.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private TodoJoinUser makelist(ResultSet resultSet){
        Integer id,userId;
        String  content, createAt,name;
        boolean checked;
        try {
            id = resultSet.getInt("id");
        }catch (SQLException e) {
            id = null;
        }
        try {
            name = resultSet.getString("name");
        }catch (SQLException e) {
            name = null;
        }
        try {
            userId = resultSet.getInt("user_id");
        }catch (SQLException e) {
            userId = null;
        }
        try {
            content = resultSet.getString("content");
        }catch (SQLException e) {
            content = null;
        }
        try {
            createAt = resultSet.getString("create_at");
        }catch (SQLException e) {
            createAt = null;
        }
        try {
            checked = resultSet.getBoolean("checked");
        }catch (SQLException e) {
            checked = false;
        }
        return new TodoJoinUser(id,content,createAt,checked,name,userId);
    }
}
