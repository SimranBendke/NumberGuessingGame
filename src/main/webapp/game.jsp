<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Number Guessing Game</title>
</head>
<body>

<h2>Guess a number between 1 and 100</h2>

<form action="GameServlet" method="post">
    Enter your guess:
    <input type="number" name="guess" required>
    <input type="submit" value="Submit">
</form>

<p style="color:blue;">
    ${message}
</p>

<%
    Boolean gameOver = (Boolean) request.getAttribute("gameOver");
    if (gameOver != null && gameOver) {
%>

    <hr>

    <form action="showhistory.jsp" method="get">
        <input type="submit" value="View Game History">
    </form>

    <br>

    <form action="GameServlet" method="post"
          onsubmit="return confirm('Are you sure you want to delete all history?');">
        <input type="hidden" name="action" value="deleteHistory">
        <input type="submit" value="Delete Game History">
    </form>

<%
    }
%>

</body>
</html>
