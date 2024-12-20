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

<title>Material List</title>

</head>

<body>

	<%@include file="Header.jsp"%>
	
	<br>
	
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	
	<div class="container mt-2"
		style="position: relative; min-height: 41vh">

		<h2 align="center" style="color:green">Material List</h2>

		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

		<form action="<%=SCMView.MATERIAL_AVAILABLE_LIST_CTL%>" method="post">
		
			<table class="table table-striped table-dark">
			
				<tr>
					<th scope="col">Product ID</th>
					
					<th scope="col">Product Name</th>
					
					<th scope="col">Quantity</th>
					
					<th scope="col">Total Cost</th>
					
					<th scope="col">Company Name</th>
					
					<th scope="col">Action</th>
					
					<th scope="col"></th>
					
				</tr>
				
				<%
					int index = 1;
				
					List list = ServletUtility.getList(request);
					
					Iterator it = list.iterator();
					
					while (it.hasNext()) {
						
						MaterialBean bean = (MaterialBean) it.next();
				%>
				
				<tr>
					<td><%=bean.getProductID()%></td>
					
					<td><%=bean.getProductName()%></td>
					
					<td><%=bean.getQuantity()%></td>
					
					<td><%=bean.getTotalCost()%> Rs</td>
					
	                <td><%=bean.getCompanyName()%></td>
	                
					<%
						if (user.getRoleId() == 1) {
							
					%>
					
					<td><a class="btn btn-lg btn-success"
						href="<%=SCMView.PRODUCT_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>

					<%
					
						} else {
							
					%>
					
<%if(bean.getStatus().equalsIgnoreCase("Paid")){%>

<td><a class="btn btn-secondory">Paid</a></td>

<%}else{%>

					<td><a class="btn btn-success"
						href="<%=SCMView.PAYMENT_CTL%>?totalcost=<%=bean.getTotalCost()%>&id=<%=bean.getId()%>">Payment</a></td>
						
						<%
						}
					%>
					
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