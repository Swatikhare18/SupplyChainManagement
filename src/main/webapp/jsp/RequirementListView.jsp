<%@page import="in.co.supply.chain.Bean.RequirmentBean"%>

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

<title>Requirements List</title>

</head>

<body>

	<%@include file="Header.jsp"%>
	
	<br>

	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	
	<div class="container mt-2"
		style="position: relative; min-height: 45vh">
		

		<h2 align="center" style="color:green">Requirements List</h2>

		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

		<form action="<%=SCMView.Requirements_LIST_Ctl%>" method="post">
		
			<table class="table table-striped table-dark">
			
				<tr>
					<th scope="col">Product ID</th>
					
					<th scope="col">Product Name</th>
					
					<th scope="col">Location</th>
					
					<th scope="col">Quantity</th>
					
					<th scope="col">Description</th>
					
					<th scope="col">Action </th>
					
					<th scope="col"></th>
					
				</tr>
				
				
				
				<%
					int index = 1;
				
					List list = ServletUtility.getList(request);
					
					Iterator it = list.iterator();
					
					while (it.hasNext()) {
						
						RequirmentBean bean = (RequirmentBean) it.next();
				%>
				
				<tr>
					<td><%=bean.getProductID()%></td>
					
					<td><%=bean.getProductName()%></td>
					
					<td><%=bean.getLocation()%></td>
					
					<td><%=bean.getQuantity()%></td>
					
					<td><%=bean.getDiscription()%></td>

					<%
						if (bean.getStatus().equals("SendDealer")) {
					%>
					
					<td><a class="btn btn-secondary">Sent</a></td>

					<%
						} else {
					%>

					<td><a class="btn btn-success"
						href="<%=SCMView.Requirements_LIST_Ctl%>?Sid=<%=bean.getId()%>">Send
							Dealer</a></td>


					<td><a class="btn btn-danger"
						href="<%=SCMView.Requirements_LIST_Ctl%>?id=<%=bean.getId()%>">Delete</a></td>
						
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
	
	<%@include file="Footer.jsp"%>
	
</body>

</html>