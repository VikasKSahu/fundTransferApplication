<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta name ="layout" content = "main" charset="ISO-8859-1">
		<title>List all accounts</title>
	</head>
	<body>
		<div>
			  <h2>List all accounts</h2>
    <div align="left">
      <table border="1" >
        <tr>
          <th>Account Number</th>
          <th>current Balance</th>
          <th>Email Address</th>
        </tr>
        <c:forEach var="account" items="${accounts}">
          <tr>
            <td>
              <c:out value="${account.accountNumber}" />
            </td>
            <td>
              <c:out value="${account.currentBalance}" />
            </td>
            <td>
              <c:out value="${account.email}" />
            </td>
            <td>
              <a href="/fundTransfer/transaction?accountNumber=<c:out
                         value="${account.accountNumber}"/>">View Transactions</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
		</div>
	</body>
</html>