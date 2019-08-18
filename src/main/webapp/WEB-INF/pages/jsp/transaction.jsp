<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/includes/taglibs.jsp"%>

<html>
<head>
<title>Application Dynamic Drop Down</title>
</head>
<body>
	<div class="judul">
		<h1>Application Dynamic Drop Down</h1>
	</div>

	<div>
		<strong style="color: blue;">${message}</strong>
	</div>

	<br />
	<a class="tombol" href="/" >List Transaction</a>

	<h3>Transaction</h3>
	<table>
		<tr>
			<td>Phone Number</td>
			<td><input id="number" type="text" name="userName" maxlength="12"/> <input
				id="userId" type="hidden" name="userId" value="${userId}"></td>
		</tr>
		<tr>
			<td>Operator</td>
			<td><select id="operator" name="operator" class="select2">
					<option value="">-- Select Operator --</option>
					<c:forEach items="${listOperator}" var="listOperator">
						<option value="<c:out value="${listOperator.name }"/>" />${listOperator.name }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Pulsa</td>
			<td><select id="pulsa" name="pulsa" class="select2">
					<option value="">-- Select Pulsa --</option>
					<c:forEach items="${listPulsa}" var="listPulsa">
						<option value="<c:out value="${listPulsa.name }"/>" />${listPulsa.name }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>Harga</td>
			<td><label id="harga" /></td>
			<c:set var="val" value="${harga}" />
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="button" class="btn" onclick="saveTransaction();">Save
					Transaction</button>
		</tr>
	</table>
	&copy; Developer
	<!-- &copy; ndms.arifin@gmail.com -->
</body>

</html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$("#operator").change(function() {
		var optionSelected = $(this).find("option:selected");
		var valueSelected = optionSelected.val();
		var type = 'voucher';
		if (valueSelected != '') {
			populatePulsaDropdown(type, valueSelected, '');
		}
	});

	function populatePulsaDropdown(val, val2, val3) {
		jQuery.ajax({
			url : '/voucher?type=' + val + '&operator=' + val2,
			success : function(data) {
				var select = $('#pulsa');
				select.find('option').remove();
				$('<option>').val('').text("--- Select Pulsa ---").appendTo(
						select);
				document.getElementById("harga").innerHTML = "-";
				$.each(data, function(index, voucher) {
					//  console.log(city.name); //to print name of city
					if (val2 != '' && val2 == voucher.id)
						$('<option selected="selected">').val(voucher.id).text(
								voucher.pulsa).appendTo(select);
					else
						$('<option>').val(voucher.id).text(voucher.pulsa)
								.appendTo(select);
				});
				$("#pulsa").select2("val", null);

			},
			error : function(data) {
				//alert(data + "error");
				$.each(data, function(index, city) {
					console.log(city.name); //to print name of employee
				});
			}
		});
	}

	$("#pulsa").change(function() {
		var optionSelectedOperator = $("#operator").find("option:selected");
		var valueSelectedOperator = optionSelectedOperator.val();
		var optionSelected = $(this).find("option:selected");
		var valueSelected = optionSelected.val();
		var type = 'harga';
		if (valueSelected != '') {
			populateHargaDropdown(type, valueSelectedOperator, valueSelected);
		}
	});

	function populateHargaDropdown(val, val2, val3) {
		jQuery
				.ajax({
					url : '/harga?type=' + val + '&operator=' + val2
							+ '&pulsa=' + val3,
					success : function(data) {
						var labelHarga = $("#harga").val();
						var resultHarga = data;
						document.getElementById("harga").innerHTML = resultHarga;
					},
					error : function(data) {
						//alert(data + "error");
						$.each(data, function(index, city) {
							console.log(city.name); //to print name of employee
						});
					}
				});
	}

	function saveTransaction() {
		var type = 'transaction';
		var number = document.getElementById('number').value;
		var numberParsing = Number(number);
		var userid = document.getElementById('userId').value;
		var optionSelectedOperator = $("#operator").find("option:selected");
		var operator = optionSelectedOperator.val();
		var harga = $('#harga').text();
		if (number == undefined)
			return;
		if (isNaN(Math.floor(number))) {
			alert("phone number number only ?");
			return;
		}
		if (number == '') {
			alert("phone number ?");
			return;
		}
		if (operator == '') {
			alert("operator ?");
			return;
		}
		if (harga == '' || harga == '-') {
			alert("harga ?");
			return;
		}
		var ask = confirm('Are you sure save ? ');
		if (ask) {

			window.location = "/transaction?type=" + type + "&userid=" + userid
					+ "&operator=" + operator + "&harga=" + harga;

			/* $.get("/transaction?type=" + type + "&userid=" + userid
					+ "&operator=" + operator + "&harga=" + harga, function(
					data, status) {
			}); */
			return true;
		} else {
			return false;
		}

	}
</script>
