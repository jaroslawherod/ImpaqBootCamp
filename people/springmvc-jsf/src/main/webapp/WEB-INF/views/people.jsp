<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>People</title>
</head>
<body>
	<h:dataTable value="{people}" var="p">
		<h:column>
			<f:facet name="header">Name</f:facet>
    				#{p.name}
    			</h:column>

		<h:column>
			<f:facet name="header">PESEL</f:facet>
    				#{p.pesel}
    			</h:column>

		<h:column>
			<f:facet name="header">Address</f:facet>
    				#{p.adress}
    			</h:column>
	</h:dataTable>
</body>
</html>