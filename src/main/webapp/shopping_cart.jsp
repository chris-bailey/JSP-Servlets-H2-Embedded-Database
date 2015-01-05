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
	<h1>Your Shopping Cart</h1>

	<table border="1">
		<thead>
			<tr>
				<th>Quantity</th>
				<th>Description</th>
				<th>Price</th>
				<th>Amount</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.items}" var="li">
				<tr>
					<form action="<c:url value="/cart"/>" method="GET">
						<td><input type="text" name="quantity" value="${li.quantity}" />
							<input type="hidden" name="prodId" value="${li.product.prodId}" />
							<br> <input type="submit" value="Update"></td>
					</form>
					<td>${li.product.description}</td>
					<td>${li.product.currency}</td>
					<td>${li.currency}</td>

					<form action="<c:url value="/cart"/>" method="POST" />
					<td><input type="submit" value="Remove Item" /> <input
						type="hidden" name="prodId" value="${li.product.prodId}" /> <input
						type="hidden" name="quantity" value="0" /></td>
					</form>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="3"><b>To change the quantity</b>, enter the new
					quantity and click on the Update button.</td>
				<td colspan="2"></td>
			</tr>
		</tbody>
	</table>

	<form action="<c:url value="/index.jsp"/>" method="GET">
		<input type="submit" value="Continue Shopping" />
	</form>

</body>
</html>
