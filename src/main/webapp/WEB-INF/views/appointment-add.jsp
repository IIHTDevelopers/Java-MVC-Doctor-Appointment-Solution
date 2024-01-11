<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<head>
	<title>Appointment</title>

</head>


<body>
	<div id="wrapper">
		<div id="header">
			<h2>Appointment Management System</h2>
		</div>
	</div>
	<div id="container">
		<h3> Add Appointment</h3>
		<form:form action="saveAppointment" modelAttribute="appointment" method="POST">
		<!-- Associate the data with a given customer with a hidden form param -->
		<form:hidden path="id"/> <!-- Customer.setId will be called -->
		<table>
			<tbody>
				<tr>
					<td><label> Patient Name:</label></td>
					<td><form:input path="patientName" /></td>
					<td><form:errors path="patientName"/></td>
				</tr>
				<tr>
					<td><label> Appointment Date (dd-MM-yyyy):</label></td>
					<td><form:input path="appointmentDate" type = "date" id="todayDate" /></td>
					<td><form:errors path="appointmentDate"/></td>
				</tr>
				<tr>
					<td><label> Appointment Time (HH:mm:ss):</label></td>
					<td><form:input path="appointmentTime" type = "time" value="00:00:00" step="1" /></td>
					<td><form:errors path="appointmentTime"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>
				</tr>
			</tbody>
		</table>
		</form:form>
	</div>
	<!--  Adding The link to the bottom of the page rather than at the top -->
	<br><br>
	<br><br>
	<div id="container">
			<a href="${pageContext.request.contextPath}/appointment/list">Back to Home Page</a>
	</div>
<script>
  document.getElementById("todayDate").valueAsDate = new Date();
 </script>

</body>
</html>