<%@page import="in.co.supply.chain.Bean.ProductBean"%>

<%@page import="in.co.supply.chain.Controller.InventoryCtl"%>

<%@page import="in.co.supply.chain.Utility.HTMLUtility"%>

<%@page import="in.co.supply.chain.Controller.ProductCtl"%>

<%@page import="in.co.supply.chain.Utility.ServletUtility"%>

<%@page import="in.co.supply.chain.Utility.DataUtility"%>

<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>Product</title>

</head>

<body>

<%@include file="Header.jsp"%>

<%UserBean user = (UserBean)session.getAttribute("user");%>

	<div class="container mt-2"
		style="position: relative; min-height: 47vh">
		
		<form action="<%=SCMView.PRODUCT_CTL%>" method="post">
		
		<br>
		
		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">

					<jsp:useBean id="bean" scope="request"
						class="in.co.supply.chain.Bean.ProductBean" />
						

					<input type="hidden" name="id" value="<%=bean.getId()%>"> 
						
		<%if(bean.getId()>0){%>
		
		<h2 class="text-center">Update Product</h2>
		
		<%}else{%>
		
		<h2 class="text-center" style="color:green">Add Product</h2>
		
		<%} %>
		
		<hr>
		
                          <div class="col-sm-4"></div>

                          <div class="col-sm-4">
                          
								<label class="form-label">Product Name</label> <input
									type="text" class="form-control" name="productName"
									placeholder="Enter Product Name here..."
									value="<%=DataUtility.getStringData(bean.getProductName())%>">
								<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productName", request)%></div>
							
							</div>






                  <div class="col-sm-4"></div>

					
					<%if(bean.getId()>0){%>
					
					<div class="col-md-6 text-center" style="margin-top: 2%; margin-left: 25%">
					
							<div class="form-group">
								<input type="submit" name="operation"
									value="<%=ProductCtl.OP_UPDATE%>" class="btn btn-primary">
							</div>
							
						</div>
						
						
					<%}else{%>
					
					<div class="col-md-6 text-center" style="margin-top: 2%; margin-left: 25%">
					
							<div class="form-group">
								<input type="submit" name="operation"
									value="<%=ProductCtl.OP_SUBMIT%>" class="btn btn-lg btn-success">
							</div>
							
						</div>
					
					<%} %>
					
						
						</div>
						
				<div class="col-2"></div>
				
			</div>
			
		</form>
		
	</div>
	
	<br>
	
	<%@ include file="Footer.jsp"%>
	
</body>

</html>