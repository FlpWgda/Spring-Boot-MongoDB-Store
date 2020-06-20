<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<body>
    <div>
        <div>
            <p> ${user.login}, ${user.password} </p>
        </div>
        <form action="/sessionUser" method="get">
			<button name="Sesja" >Sesja</button>
		</form>
    </div>
</body>
</html>