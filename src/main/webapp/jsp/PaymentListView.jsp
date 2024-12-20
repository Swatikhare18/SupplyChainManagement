<%@page import="in.co.supply.chain.Bean.PaymentBean"%>

<%@page import="in.co.supply.chain.Bean.MaterialBean"%>

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

<title>Payment List</title>

</head>

<body>

	<%@include file="Header.jsp"%>
	
	<br>
	
	<%UserBean user = (UserBean)session.getAttribute("user");%>
	
	<div class="container mt-2"
		style="position: relative; min-height: 42vh">

		<h2 align="center" style="color:green">Payment List</h2>

		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

		<form action="<%=SCMView.PAYMENT_LIST_CTL%>" method="post">
		
			<table class="table table-striped table-dark">
			
				<tr>
					<th scope="col">Amount</th>
					
					<th scope="col">Card Number</th>
					
					<th scope="col">CVV</th>
					
					<th scope="col">Card Expairy</th>
					
					<%if(user.getRoleId()==1) {%>
					
				<th scope="col">Action</th>
				
				<%}else{%>
				
				<%}%>
					
					<th scope="col"></th>
					
				</tr>
				
				<%
					int index = 1;
				
					List list = ServletUtility.getList(request);
					
					Iterator it = list.iterator();
					
					while (it.hasNext()) {
						
						PaymentBean bean = (PaymentBean) it.next();
				%>
				
				<tr>
					<td><%=bean.getAmount()%> RS</td>
					
					<td><%=bean.getCardNo()%></td>
					
					<td><%=bean.getCvv()%></td>
					
					<td><%=bean.getCardExpairy()%></td>
	                
	                <%if(user.getRoleId()==1) {%>
	                
				<td><a class="btn btn-lg btn-success" href="<%=SCMView.PAYMENT_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
				
				<%}else{%>
				
				<%}%>
				
			</tr>
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