<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets">
	<h:head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add person</title>
	</h:head>
	<h:body>
		<form method="post">
			<input name="name" type="text"/>
			<input name="pesel" type="text"/>
			<input name="adress" type="text"/>
			<input type="submit">Save</input>
		</form>
	</h:body>
</html>