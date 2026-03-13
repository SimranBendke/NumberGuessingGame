<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="dao.GameDAO, model.Game, java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Game History</title>
</head>
<body>

<h2>Number Guessing Game - History</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Attempts</th>
        <th>Status</th>
        <th>Time Played</th>
    </tr>

<%
    GameDAO dao = new GameDAO();
    List<Game> list = dao.getAllHistory();

    for(Game g : list) {
%>
    <tr>
        <td><%= g.getId() %></td>
        <td><%= g.getUsername() %></td>
        <td><%= g.getAttempts() %></td>
        <td><%= g.getStatus() %></td>
        <td><%= g.getPlayedAt() %></td>
    </tr>
<%
    }
%>

</table>

</body>
</html>
