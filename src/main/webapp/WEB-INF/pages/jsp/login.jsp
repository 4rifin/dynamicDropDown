<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>

<html>
<head>
<title>Application Dynamic Drop Down </title>
</head>
<body>
	<div class="judul">
		<h1>Application Dynamic Drop Down </h1>
	</div>

	<div>
		<strong style="color: blue;">${message}</strong>
	</div>

	<br/>
	<h3>Login</h3>
		<table>
			<tr>
				<td>username</td>
				<td><input id="username" type="text" name="username">
				</td>					
			</tr>	
			<tr>
				<td>password</td>
				<td><input id="password" type="password" name="password"></td>					
			</tr>
			<tr>
				<td></td>
				<td>
				    <form id="formLogin" method="get">
				    	<button type="button" class="btn" onclick="loginClick();">Login</button>
					</form>
				</td>	
			</tr>				
		</table>
		&copy; Developer <!-- &copy; ndms.arifin@gmail.com -->
</body>

</html>
<script type="text/javascript" >
function loginClick(){
	var type = 'login';
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	if (username == undefined)
	    return;
	window.location = "/login?type="+type+"&username="+username+"&password="+password;
	$.get("/login?type="+type+"&username="+username+"&password="+password, function(data, status){
	});
}
</script>        