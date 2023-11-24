<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<title>Appointment List</title>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2> Appointment Management System</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
		<!--  Add "Add Appointment" Button -->
		<input type="button" value="Add Appointment"
			   onclick="window.location.href='showFormForAdd'; return false;"
			   class="add-button"/>
			   <br>
			   <br>
			   <br>
		<!-- Add Table Content here -->
		<table>
			<tr>
				<th> Patient Name</th>
				<th> Appointment Date </th>
				<th> Appointment Time </th>
				<th> Action</th>
			</tr>
			<c:forEach var="tempAppointment" items="${appointments}">

			<!-- Add embedded link to update the customer -->
			<c:url var="updateLink" value="/appointment/showFormForUpdate">
				<c:param name="appointmentId" value="${tempAppointment.id}"/>
			</c:url>
			<c:url var="deleteLink" value="/appointment/showFormForDelete">
				<c:param name="appointmentId" value="${tempAppointment.id}"/>
			</c:url>

					<tr>
						<td> ${tempAppointment.patientName} </td>
						<td> ${tempAppointment.appointmentDate} </td>
						<td> ${tempAppointment.appointmentTime} </td>
						<td>
							<a href="${updateLink}">Update</a>
							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to clear this appointment?'))) return false">
							|Clear</a>
						</td>
					</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>