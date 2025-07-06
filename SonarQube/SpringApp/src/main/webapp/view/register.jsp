<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Telusko Learning Registration Telusko</title>
</head>
<body>
<center>
   		<h1 style=' color: red; text-align:center'> Registration Application</h1>
         <form:form method="post" modelAttribute="emp" >
            <table>
                  <tr>
                  <th> Employee ID</th>
                  <td> <form:input path="id"/></td>
                  </tr>
                  
                   <tr>
                  <th> Employee Name</th>
                  <td> <form:input path="ename"/></td>
                  </tr>
                  
                  <tr>
                  <th> Employee City</th>
                  <td> <form:input path="ecity"/></td>
                  </tr>
                  
                   <tr>
                  <th> Employee Salary</th>
                  <td> <form:input path="esalary"/></td>
                  </tr>
                  
                   <tr>
                 
                  <td> <input type="submit" value="Register"></td>
                  </tr>
            
            
            </table>
         
         </form:form>
         
   </center>
</body>
</html>
