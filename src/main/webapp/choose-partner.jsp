<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:out value="${testString}"></c:out> --%>
<c:forEach var="friend" items="${friendsList['response']}">
	<div>
		<img src = "${friend['photo']}" alt = "${friend['first_name']} ${friend['last_name']}"></img>
		<p>${friend['uid']} - ${friend['first_name']} ${friend['last_name']}</p>
	</div>	
</c:forEach>
