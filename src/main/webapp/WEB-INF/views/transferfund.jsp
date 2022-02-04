<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Pay someone</title>
</head>
<body>
<h2>Pay someone</h2>
<form:form method="POST" 
          action="/fundTransfer/sendmoney" modelAttribute="fundTransferRequest">
             <table>
                <tr>
                	<td><form:label path="fromAccountNumber"> From Account Number: </form:label></td>
                    <td><form:select path="fromAccountNumber" items="${accounts}">
						   <form:option value="NONE" label="--- Select ---"/>
						   <form:options value="${accounts.accountNumber}" label="${ account.accountNumber}" />
						</form:select>
                   </td>
                  </tr>
                  <tr>
                  	 <td><form:label path="toAccountNumber"> To Account Number: </form:label></td>
                    <td><form:select path="toAccountNumber" items="${accounts}" >
						   <form:option value="NONE" label="--- Select ---"/>
						   <form:options value="${accounts.accountNumber}" label="${ account.accountNumber}"/>
						</form:select>
                    </td>
                </tr>
               
                <tr>
                    <td><form:label path="amount"> Amount: </form:label></td>
                    <td><form:input path="amount"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
</body>
</html>