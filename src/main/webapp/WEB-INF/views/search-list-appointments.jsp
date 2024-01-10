<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Appointment List</title>
	</head>
	<body>
	<div>
    	<h2> Search Appointment </h2>
            <form:form action="/search?page=0&size=5" method="POST">
    			<div>
    			    <div>
    			      <div><i aria-hidden="true"></i></div>
    			    </div>
    			    <input type="text" placeholder="Search By Patient Name" name="theSearchName" value = "${theSearchName}">
    			    <input type="date" name="theSearchDate" value = "${theSearchDate}">
    			    <input type="submit" value="Search"/>
    			</div>
            </form:form>
    		<table>
            			<tr>
            			    <th> S No. </th>
            				<th> Patient Name
            				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=patientName,desc"> Desc </a>
                                    &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=patientName"> Asc </a>
                             </th>
            				<th> Appointment Date
            				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=appointmentDate,desc"> Desc </a>
                                    &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=appointmentDate"> Asc </a>
            				</th>
            				<th> Appointment Time
            				        &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=appointmentTime,desc"> Desc </a>
                                    &nbsp &nbsp <a href= "/list?page=0&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=appointmentTime"> Asc </a>
            				</th>
            				<th> Action</th>
            			</tr>
            			<c:set var="index" value="${page * 5 + 1}" />
            			<c:forEach var="tempAppointment" items="${appointments}">

            			<!-- Add embedded link to update the customer -->
            			<c:url var="updateLink" value="/appointment/showFormForUpdate">
            				<c:param name="appointmentId" value="${tempAppointment.id}"/>
            			</c:url>
            			<c:url var="deleteLink" value="/appointment/showFormForDelete">
            				<c:param name="appointmentId" value="${tempAppointment.id}"/>
            			</c:url>

            					<tr>
            					    <td>${index}</td>
            						<td> ${tempAppointment.patientName} </td>
            						<td> ${tempAppointment.appointmentDate} </td>
            						<td> ${tempAppointment.appointmentTime} </td>
            						<td>
            							<a href="${updateLink}">Update</a>
            							<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to clear this appointment?'))) return false">
            							|Clear</a>
            						</td>
            					</tr>
            					<c:set var="index" value="${index + 1}" />
            			</c:forEach>
            		</table>
    	</div>
    	<br><br>
    	<c:choose>
            <c:when test="${totalPage == 0}">
                No Record Found
            </c:when>
            <c:otherwise>
                <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                        &nbsp &nbsp<a href="/list?page=${loop.index}&size=5&theSearchName=${theSearchName}&theSearchDate=${theSearchDate}&sort=${sortBy}">${loop.index + 1}</a></li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
	</body>
</html>