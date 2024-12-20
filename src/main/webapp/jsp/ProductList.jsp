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

<title>Product List</title>

</head>

<body>

<%@include file="Header.jsp"%>

<br>

<%UserBean user = (UserBean)session.getAttribute("user");%>

 <div class="container mt-2"
		style="position: relative; min-height: 40vh">
		
   
<h2 align="center" style="color:green">Product List</h2>

	<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
	
	<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=SCMView.PRODUCT_LIST_CTL%>" method="post">
	
		<table class="table table-striped table-dark">
		
			<tr >
				<th scope="col" >Product Name</th>
				
				<th scope="col">Action</th>
				
				<th scope="col"></th>
				
			</tr>
			
			<%
			
				int index = 1;
			
				List list = ServletUtility.getList(request);
				
				Iterator it = list.iterator();
				
				while (it.hasNext()) {
					
					ProductBean bean = (ProductBean) it.next();
			%>
			
			<tr>
			
				<td><%=bean.getProductName()%></td>
				
				
<%if(user.getRoleId()==1){%>

               <td><a class="btn btn-lg btn-success"
					href="<%=SCMView.PRODUCT_CTL%>?id=<%=bean.getId()%>">Edit</a></td>

				<td><a class="btn btn-lg btn-success"
					href="<%=SCMView.PRODUCT_LIST_CTL%>?id=<%=bean.getId()%>&pname=<%=bean.getProductName()%>">Delete</a></td>
					
<%}else{ %>					

  <td><a class="btn btn-success"
					href="<%=SCMView.Requirements_Ctl%>?id=<%=bean.getId()%>&productName=<%=bean.getProductName()%>">Requirement</a></td>

<%} %>

			
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