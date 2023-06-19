<%@ page import="com.playdata.todo.dto.TodoJoinUser" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: playdata
  Date: 2023-06-19
  Time: 오전 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
  <tr>
    <td>id</td>
    <td>content</td>
    <td>작성일</td>
    <td>작성자</td>
    <td>check</td>
    <td> 수정 </td>

  </tr>
<%List<TodoJoinUser> todos = (List<TodoJoinUser>) request.getAttribute("todolist");
  if(todos.size() !=0){
  for (TodoJoinUser todo:todos
       ) {%>
  <tr>
  <td><%=todo.getId()%></td>
    <td><%=todo.getContent()%></td>
    <td><%=todo.getCreateAt()%></td>
    <td><%=todo.getName()%></td>
    <td><%=todo.isChecked()%></td>
      <%
      if(session.getAttribute("uid") == todo.getUid()){
      %>
      <td>
        <a href="/todoupdate?id=<%=todo.getId()%>">수정</a>
      </td><%
      }
    %>
  </tr>
  <%
    }
  }%>
</table>
