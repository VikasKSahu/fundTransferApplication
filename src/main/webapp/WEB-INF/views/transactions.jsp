<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta name ="layout" content = "main" charset="ISO-8859-1">
<title>Transactions details</title>
</head>
<body>
<div>
			  <h2>All transactions for </h2>
			  <h2>Account Number:${transactions.forAccountNumber }</h2>
			  <h2>Final Balance:${transactions.currentBalance }</h2>
    <div align="left">
      <table border="1" >
        <tr>
          <th>Account Number</th>
          <th>Withdrawl</th>
          <th>Deposit</th>
          <th>Transaction Reference Number</th>
          <th>Transaction Date</th>
        </tr>
        <c:forEach var="transaction" items="${transactions.transactionHistory}">
          <tr>
            <td>
              <c:out value="${transaction.accountNumber}" />
            </td>
            <td>
              <c:out value="${transaction.debitAmount}" />
            </td>
            <td>
              <c:out value="${transaction.creditAmount}" />
            </td>
            <td>
              <c:out value="${transaction.transactionReference}" />
            </td>
            <td>
              <c:out value="${transaction.transactionDateTime}" />
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
		</div>
</body>
</html>