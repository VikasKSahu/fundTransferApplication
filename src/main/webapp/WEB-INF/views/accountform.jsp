<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Create New Account</title>
</head>
<style>
      .error {
         color: #ff0000;
      }

      .errorblock {
         color: #000;
         background-color: #ffEEEE;
         border: 3px solid #ff0000;
         padding: 8px;
         margin: 16px;
      }
   </style>
<body>
<h2>Create new account</h2>
<form:form method="POST" 
          action="/fundTransfer/create" modelAttribute="createAccountRequest">
		<form:errors path = "*" cssClass = "errorblock" element = "div" />
             <table>
                <tr>
                    <td><form:label path="accountNumber">Account Number:</form:label></td>
                    <td><form:input path="accountNumber"/></td>
                </tr>
               
                <tr>
                    <td><form:label path="currentBalance" > Balance: </form:label></td>
                    <td><form:input path="currentBalance"/></td>
                </tr>
                 <tr>
				    <td><font color="red"> <form:errors path="currentBalance"></form:errors></font></td>
				 </tr>
                <tr>
                    <td><form:label path="email" > Email: </form:label></td>
                    <td><form:input path="email" type="email"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr> 
            </table>
        </form:form>
</body>
</html>