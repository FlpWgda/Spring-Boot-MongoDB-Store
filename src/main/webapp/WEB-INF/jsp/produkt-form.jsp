<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
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

	<form:form action="saveProdukt" modelAttribute="produkt" method="POST">
		<form:hidden path="idP" />
		
		
		<table>
			<tbody>
				<tr>
					<td><label>Nazwa</label></td>
					<td><form:input path="nazwaP" />
					<form:errors path="nazwaP" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label>Liczba sztuk</label></td>
					<td><form:input path="liczbaSztuk" />
						<form:errors path="liczbaSztuk" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label>Cena</label></td>
					<td><form:input path="cena" />
						<form:errors path="cena" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label>Producent</label></td>
					<td><form:radiobuttons path="producent" items="${producent}" />
						<form:errors path="producent" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label>Kategorie</label></td>
					<td><form:checkboxes path="kategorie" items="${kategoria}" /></td>
					
				</tr>
				<tr>
					<td><label>Opis</label></td>
					<td><form:textarea path="opis" />
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Dodaj" /></td>
				</tr>
				
				
			</tbody>
		</table>
		
	</form:form>
	
	<p>
		<a href="${pageContext.request.contextPath}/produkt/list">Powrot do listy</a> 
	</p>	
</body>
</html>