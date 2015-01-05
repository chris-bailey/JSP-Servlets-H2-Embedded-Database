<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart Demo</title>
<link rel="stylesheet" href="styles/standard.css">
</head>
<body>
	<h1>CD List</h1>
	<table border="1">
		<thead>
			<tr>
				<th><b>Description</b></th>
				<th><b>Price</b></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.description}</td>
				<td>${product.currency}</td>
				<td><a
					href="<c:url value="cart?prodId=${product.prodId}&quantity=1"/>">Add
						To Cart</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
