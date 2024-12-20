<%@page import="in.co.supply.chain.Controller.RequirementsCtl"%>

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

<title>Requirement Add</title>

</head>

<body>

<%@include file="Header.jsp"%>

<%UserBean user =  (UserBean)session.getAttribute("user");%>

	<div class="container">
	
		<form action="<%=SCMView.Requirements_Ctl%>" method="post">
		
		<br>
		
		
		<h2 class="text-center" style="color:green">Add Requirement</h2>
		
		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>
		
		<hr>

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">

					<jsp:useBean id="bean" scope="request"
						class="in.co.supply.chain.Bean.RequirmentBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> 
					
					
						
                    <div class="col-md-6">
                    
						<label class="form-label">Product Name</label> <input type="text"
							class="form-control" name="productName" placeholder="Enter Product Name here..." 
							value="<%=DataUtility.getStringData(bean.getProductName()) %>" readonly="readonly">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productName", request)%></div>
					
					</div>




               		<div class="col-md-6">
               		
						<label class="form-label">Quantity</label> <input type="text"
							class="form-control" name="quantity" placeholder="Enter Quantity here...">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%></div>
				
					</div>
					
					
					
					
						
						<div class="col-md-6">
						
						<label class="form-label">Product Id</label> <input type="text"
							class="form-control" name="productid" placeholder="Enter Product Id here..." 
							value="">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productid", request)%></div>
					
					    </div>
                 

						
						<div class="col-md-6">
						
						<label class="form-label">Location</label> <input type="text"
							class="form-control" name="location"
							placeholder="Enter location here..." value="">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("location", request)%></div>
				
				    	</div>
					
					
					
			<div class="col-md-6">
							
			<label class="form-label">Description</label>
			<textarea rows="3" cols="3" name="description"
				placeholder="Enter description" class="form-control"></textarea>
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></div>

<br>
						
						<div class="col-md-6 text-center" style="margin-top: 2%; margin-left: 80%">
							
							<div class="form-group">
								<input type="submit" name="operation"
									value="<%=RequirementsCtl.OP_SUBMIT%>" class="btn btn-lg btn-success">
							</div>
							
						</div>
						
					   </div>
					
					
					
				<div class="col-2"></div>
</div>
			</div>
			
		</form>
		
	</div>
	
	<br>
	
	<%@ include file="Footer.jsp"%>
	
</body>

</html>