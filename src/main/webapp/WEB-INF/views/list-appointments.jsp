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
		</div>
	</div>

<%@include file="search-list-appointments.jsp" %>

</body>
</html>