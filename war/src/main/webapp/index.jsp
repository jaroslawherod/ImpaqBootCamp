<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
GET:
<form action="GetRequest" method="get">
name: <input type="text" name="name" > <br>
address: <input type="text" name="address" > <br> 
id: <input type="text" name="id" > <br>
<input type="submit" value="Submit">
</form>
<br><br>
POST:
<form action="GetRequest" method="post">
name: <input type="text" name="name" > <br>
address: <input type="text" name="address" > <br> 
id: <input type="text" name="id" > <br>
<input type="submit" value="Submit">
</form>

</body>
</html>