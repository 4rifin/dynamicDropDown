<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ include file="/WEB-INF/includes/taglibs.jsp"%> --%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Application Dynamic Drop Down </title>
</head>
<body>
	<div class="judul">
		<h1>Application Dynamic Drop Down with JAVA and PostgreSQL</h1>
	</div>

	<br />
	<a class="tombol" href="/addCustomer" target="_blank">+ Tambah Data Baru</a>

	<h3>Data Customer</h3>
	<table border="1" class="table">
		<tr>
			<th>No</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Opsi</th>
		</tr>
		
<c:forEach items="${listCustomer}" var="listCustomer" varStatus="theNumber">
			<tr>
				<td>${theNumber.index+1}</td>
				<td>${listCustomer.firstName}</td>
				<td>${listCustomer.lastName}</td>
				<td><a class="edit" href="#" target="_blank">Edit</a> | <a class="hapus" href="#" target="_blank">Hapus</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>