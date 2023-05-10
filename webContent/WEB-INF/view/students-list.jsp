<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Students</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Student Registration Management</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
		
			<input type="button" value="Add Student"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>
			
			<!--  add a search box -->
			<form:form action="search" method="GET">
				Search by name : <input type="text" name="theSearchName" />
				
				<input type="submit" value="Search" class="add-button" />
			</form:form>
		
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th><a href="sortByFirstName">First Name</a></th>
					<th><a href="sortByLastName">Last Name</a></th>
					<th><a href="/sortByEmail">Email</a></th>
					<th></th>
				</tr>
				
				<!-- loop over and print our Students -->
				<c:forEach var="tempStudent" items="${students}">
				
				<!-- construct an "update" link with student id -->
					<c:url var="updateLink" value="/student/showFormForUpdate">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>					

					<!-- construct an "delete" link with student id -->
					<c:url var="deleteLink" value="/student/delete">
						<c:param name="studentId" value="${tempStudent.id}" />
					</c:url>		
				
					<tr>
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.email} </td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
						</td>
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>