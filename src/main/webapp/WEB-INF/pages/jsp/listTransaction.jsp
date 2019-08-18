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
	<a class="tombol" href="/transaction/new" target="_blank">+ Tambah Data Baru</a>

	<h3>Data Transaction</h3>
	<table border="1" class="table">
		<tr>
			<th>No</th>
			<th>Harga</th>
			<th>Operator</th>
			<th>Opsi</th>
		</tr>
		
<c:forEach items="${listTransaction}" var="listTransaction" varStatus="theNumber">
			<tr>
				<td>${theNumber.index+1}</td>
				<td>${listTransaction.harga}</td>
				<td>${listTransaction.operator}</td>
				<td><form id="delete${listTransaction.id}"method="get">
					<input type="hidden" name="id" value="${listTransaction.id}">
					 <a href="/transaction/delete?type='get'&id=${listTransaction.id}">Delete</a>
				</form></td>
			</tr>
		</c:forEach>
	</table>
</body>
&copy; Developer
	<!-- &copy; ndms.arifin@gmail.com -->
</html>
<script>
	function validation(id){
		var form = document.getElementById('delete'+id);
		var ask = confirm('Are you sure delete ? ');
		if(ask){
			form.submit();
			return true;
		}else{
			return false;
		}
	}
	function submitMe()
	{
		document.MyForm.submit();
	return;
	}
</script>