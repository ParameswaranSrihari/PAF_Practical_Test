<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="investmentService.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Investment.js"></script>
<title>Investment Management</title>
</head>
<body>
<h1>Investment Management</h1>

<form id="formItem" name="formItem" method="post" action="index.jsp">
 name:
<input id="iname" name="iname" type="text"
 class="form-control form-control-sm">
<br> date:
<input id="date" name="date" type="text"
 class="form-control form-control-sm">
<br> amount:
<input id="amount" name="amount" type="text"
 class="form-control form-control-sm">
<br> description:
<input id="description" name="description" type="text"
 class="form-control form-control-sm">
<br>

<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">
<%
Investment Obj = new Investment();
 out.print(Obj.readInvestment());
%>
</div>
</body>
</html>