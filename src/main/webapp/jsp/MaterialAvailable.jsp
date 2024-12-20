<%@page import="in.co.supply.chain.Bean.InventoryBean"%>

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

<title>Material Available</title>

</head>

<body>

	<%@include file="Header.jsp"%>
	
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>

	<%
		List Companylist = (List) request.getAttribute("companyName");
	
			List QuantityList = (List) request.getAttribute("productQuantity");
	%>

	<div class="container" style="position: relative; min-height: 2vh">
	
		<form action="<%=SCMView.MATERIAL_AVAILABLE_CTL%>" method="post">
		
			<br>

			<h2 class="text-center" style="color:green">Material Available</h2>


			<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
			
			<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>
			
			<hr>
			

			<div class="row">
			
				<div class="col-2"></div>
				
				<div class="col-8">

					<jsp:useBean id="bean" scope="request"
						class="in.co.supply.chain.Bean.MaterialBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="userid" value="<%=bean.getUserId()%>">




					<div class="col-md-6">
					
						<label class="form-label">Product ID</label> <input type="text"
							class="form-control" name="productId"
							placeholder="Enter Product Cost here..." readonly="readonly"
							value="<%=DataUtility.getStringData(bean.getProductID())%>">
							
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productId", request)%>
						
						</div>
						
					</div>
					
					
					

					<div class="col-md-6">
					
						<label class="form-label">Product Name</label> <input type="text"
							class="form-control" name="ProductName"
							placeholder="Enter Product Name here..." readonly="readonly"
							value="<%=DataUtility.getStringData(bean.getProductName())%>">
						
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("ProductName", request)%>
						
						</div>
						
					</div>



					<%-- <div class="col-md-6">
						<label class="form-label">Company Name</label>
						<select name=companyName class="form-control">
						 <option>------Select------</option>
						<% 
						Iterator it1 = Companylist.iterator();
						while (it1.hasNext()) {
							InventorieBean bean1 = (InventorieBean) it1.next();
					     	String list = (String)bean1.getCompanyName();
						%>
                        	<option value=<%=list %>><%=list %></option>
						<%}%>
						</select>
					 <input type="hidden" name="list">
					</div>
					
					
					<div class="col-md-6">
						<label class="form-label">Quantity</label>
						<select name=quantity class="form-control">
						 <option disabled="disabled">------Select------</option>
						<% 
						Iterator it2 = QuantityList.iterator();
						while (it2.hasNext()) {
							InventorieBean bean2 = (InventorieBean) it2.next();
					     	String list1 = (String)bean2.getQuantity();
						%>
                        	<option value=<%=list1 %>><%=list1 %></option>
						<%}%>
						</select>
					 <input type="hidden" name="list1">
					</div> --%>



					<div class="col-md-6">
					
						<label class="form-label">Product Cost</label> <input type="text"
							class="form-control" name="cost" readonly="readonly"
							placeholder="Enter Total Cost here..."
							value="<%=DataUtility.getStringData(bean.getTotalCost())%>">
						
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("cost", request)%>
						
						</div>
					
					</div>
					
					

					<div class="col-md-6">
					
						<label class="form-label">Company Name</label> <input type="text"
							class="form-control" name="CompanyName" readonly="readonly"
							placeholder="Enter Company Name here..."
							value="<%=DataUtility.getStringData(bean.getCompanyName())%>">
						
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("CompanyName", request)%>
						
						</div>
						
					</div>
					
					


						<input
							type="hidden" class="form-control" name="totalquantity"
							readonly="readonly" placeholder="Enter Quantity here..."
							value="<%=DataUtility.getStringData(bean.getTotalquantity())%>">
							
							

					<div class="col-md-6">
					
						<label class="form-label">Product Quantity</label> <input
							type="text" class="form-control" name="quantity"
							readonly="readonly" placeholder="Enter Quantity here..."
							value="<%=DataUtility.getStringData(bean.getQuantity())%>">
						
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%>
						
						</div>
						
					</div>




						 <input
							type="hidden" class="form-control" name="lessquantity"
							readonly="readonly" placeholder="Enter Quantity here..."
							value="<%=DataUtility.getStringData(bean.getLessQuantity())%>">
					
					<input
							type="hidden" class="form-control" name="id2"
							readonly="readonly" placeholder="Enter Quantity here..."
							value="<%=DataUtility.getStringData(bean.getID())%>">



					<div class="col-md-6" style="margin-top: 2%; margin-left: 46%">
					
						<div class="form-group">
						
							<input type="submit" name="operation"
								value="<%=InventoryCtl.OP_SUBMIT%>" class="btn btn-primary">
						</div>
						
					</div>



				</div>
				
				<div class="col-2"></div>
				
			</div>
			
		</form>
		
	</div>
	
	<br>
	
	<%@ include file="Footer.jsp"%>
	
</body>

</html>