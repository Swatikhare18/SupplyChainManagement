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
		style="position: relative; min-height: 48vh">

		<h2 align="center" style="color:green">Requirements List</h2>
	<%-- 	<h4 style="color: red;" class="text-center"><%=request.getAttribute("msg")%></h4> --%>
		
		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

		<form action="<%=SCMView.Requirements_LIST_Ctl%>" method="post">
		
			<table class="table table-striped table-dark">
			
				<tr>
					<th scope="col">Product ID</th>
					
					<th scope="col">Product Name</th>
					
					<th scope="col">Quantity</th>
					
					<th scope="col">Location</th>
					
					<th scope="col">Action</th>
					
					<th scope="col"></th>
					
				</tr>
				
				<%
					int index = 1;
				
					List list = ServletUtility.getList(request);
					
					Iterator it = list.iterator();
					
					while (it.hasNext()) {
						
						RequirmentBean bean = (RequirmentBean) it.next();
						
				%>
				
				
				
				
				<%if(bean.getStatus().equals("SendDealer")) {%>
				
				<tr>
				    <td><%=bean.getProductID()%></td>
				    
					<td><%=bean.getProductName()%></td>
					
					 <td><%=bean.getQuantity()%></td>
					 
					<td><%=bean.getLocation()%></td>
					
					<%if(bean.getRequire().equalsIgnoreCase("Required")){%>
					
					<td><a class="btn btn-secondary">Sent</a></td>
					
					<%}else{%>
					
					<td><a class="btn btn-success"
						href="<%=SCMView.MATERIAL_AVAILABLE_CTL%>?id=<%=bean.getId()%>&Productid=<%=bean.getProductID()%>&ProductName=<%=bean.getProductName()%>&quantity=<%=bean.getQuantity()%>&userid=<%=bean.getUserId()%>&id=<%=bean.getId()%>">Send Inventories</a></td>
					
						<%}%>
						
					<%}%>
					
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