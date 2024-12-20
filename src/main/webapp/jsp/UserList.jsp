<%@page import="in.co.supply.chain.Bean.MaterialBean"%>

<%@page import="in.co.supply.chain.Bean.ProductBean"%>

<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>

<%@page import="in.co.supply.chain.Utility.ServletUtility"%>

<%@page import="in.co.supply.chain.Utility.DataUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>User List</title>

</head>

<body>

	<%@include file="Header.jsp"%>
	
	<br>
	
	<div class="container mt-2"
		style="position: relative; min-height: 42vh">

		<h2 style="color:green" align="center">User List</h2>

		
		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

		<form action="<%=SCMView.USER_LIST_CTL%>" method="post">
		
			<table class="table table-striped table-dark">
			
				<tr>
					<th scope="col">First Name</th>
					
					<th scope="col">Last Name</th>
					
					<th scope="col">Login ID</th>
					
					<th scope="col">Password</th>
					
					<th scope="col">Phone No</th>
					
					<th scope="col">RoleName</th>
					
					<th scope="col">Action</th>
					
					<th scope="col"></th>
					
				</tr>
				<%
					int index = 1;
				
					List list = ServletUtility.getList(request);
					
					Iterator it = list.iterator();
					
					while (it.hasNext()) {
						
						UserBean bean = (UserBean) it.next();
				%>
				
				<tr>
					<td><%=bean.getFirstName()%></td>
					
					<td><%=bean.getLastName()%></td>
					
					<td><%=bean.getEmail()%></td>
					
					<td><%=bean.getPassword()%></td>
					
	                <td><%=bean.getPhoneNo()%></td>
	                
					<%
					
					if (bean.getRoleName().equalsIgnoreCase("ADMIN")) {
				%>
				<td>-------</td>
<td></td>

<td></td>
				<%
					} else {
				%>
				
				<td><%=bean.getRoleName()%></td>
				
				<td><a class="btn btn-lg btn-success"
					href="<%=SCMView.USER_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
				
				<td><a class="btn btn-lg btn-success" href="<%=SCMView.USER_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
			
			</tr>
			
			<%
			}
		%>

					<%
						}
					%>
				
				</tbody>
				
			</table>
			
		</form>
		
	</div>
	
	<br>
	
	<%@include file="Footer.jsp"%>
	
</body>

</html>