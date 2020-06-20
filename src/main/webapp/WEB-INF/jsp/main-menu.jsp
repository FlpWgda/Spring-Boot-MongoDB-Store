<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%@ page
language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
%>
<title>Insert title here</title>
<style>
	.error {color:red}
</style>
</head>
<body>
    <div>
    	<c:if test = "${not empty noweKonto }">
        	<h2>Utworzono nowe konto</h2>
       	</c:if>
        <input type="button" value="Zaloguj się"
				onclick="window.location.href='/login'; return false;"
			/>   
        <input type="button" value="Utwórz konto"
				onclick="window.location.href='/register'; return false;"
			/>   
    </div>
</body>
</html>