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
    	<p> ${uzytkownik.login}, ${uzytkownik.rola} <input type="button" value="Wyloguj"
				onclick="window.location.href='/logout'; return false;"
			/> </p>
        <c:if test = "${uzytkownik.rola eq 'admin'}">
        <input type="button" value="Dodaj konto pracownika"
				onclick="window.location.href='/register'; return false;"
			/>    
        </c:if>
        <input type="button" value="Edytuj dane konta"
				onclick="window.location.href='/editPassword'; return false;"
			/>    
        <input type="button" value="Lista produktow"
				onclick="window.location.href='produkt/list'; return false;"
			/> 
		<c:if test = "${uzytkownik.rola eq 'admin'}">	    
        <input type="button" value="Lista uzytkownikow"
				onclick="window.location.href='/userList'; return false;"
			/> 
		</c:if>
		<c:if test = "${(uzytkownik.rola eq 'admin') || (uzytkownik.rola eq 'pracownik')}">	    
        <input type="button" value="Dodaj produkt"
				onclick="window.location.href='produkt/showFormForAdd'; return false;"
			/> 
		</c:if>
        <input type="button" value="Koszyk"
				onclick="window.location.href='produkt/basket'; return false;"
			/> 
		<br>
		<h2>	Historia zamowien	</h2>
		<br>
		<table border="2">
				<tr>
					<th> Data zamowienia</th>
					<th></th>
				</tr>	
		<c:forEach var="tempZamowienie" items="${zamowienia}">
				
					<c:url var="orderDetailsLink" value="/produkt/orderDetails">
						<c:param name="zamowienieId" value="${tempZamowienie.idZ}" />
					</c:url>
					<tr>
						<td> ${tempZamowienie.dataZ }</td>
						<td> 
							<form action = "/produkt/orderDetails" method = "post">
								<input type="hidden" name = "zamowienieId" value="${tempZamowienie.idZ}"/>
								<input type="submit" value="Szczegóły"/>
							</form>
						</td>
					</tr>
				</c:forEach>
        
    </div>
</body>
</html>