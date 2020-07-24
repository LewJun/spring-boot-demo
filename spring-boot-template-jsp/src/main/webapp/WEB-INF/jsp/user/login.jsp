<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/_inc/_inc_basePath.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>login.jsp</title>
</head>
<body>
<form method="post" action="user/login">
  username:<input name="username" placeholder="username"/>
  password:<input name="password" type="password" placeholder="password"/>
  <input type="submit" value="Submit">
</form>
</body>
</html>