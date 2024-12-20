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

<title>Inventory Add</title>

</head>

<body>

<%@include file="Header.jsp"%>

<%
	UserBean user = (UserBean)session.getAttribute("user");
%>

<%
	List productList = (List)request.getAttribute("productName");
%>

	<div class="container mt-2"
		style="position: relative; min-height: 51vh">
		
		<form action="<%=SCMView.Inventory_CTL%>" method="post">
		
		<br>
		
		<h4 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h4>
		
		<h4 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h4>

			<div class="row">
			
				<div class="col-2"></div>
				
				<div class="col-8">

					<jsp:useBean id="bean" scope="request"
						class="in.co.supply.chain.Bean.InventoryBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> 
						
		<%
 									if(bean.getId()>0){
 								%>
 								
		<h2 class="text-center">Update Inventory</h2>
		
		<%
			}else{
		%>
		
		<h2 class="text-center" style="color:green">Add Inventory</h2>
		
		<%
			}
		%>
		<hr>
		

               		<div class="col-md-6">
               		
						<label class="form-label">Product Cost</label> <input type="text"
							class="form-control" name="productCost" placeholder="Enter Product Cost here..." 
							value="<%=DataUtility.getStringData(bean.getProductCost())%>">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("productCost", request)%></div>
					
					</div>
					
					
					
						<div class="col-md-6">
						
						<label class="form-label">Company Name</label> <input type="text"
							class="form-control" name="companyName" placeholder="Enter Company Name here..."
							value="<%=DataUtility.getStringData(bean.getCompanyName())%>">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("companyName", request)%></div>
					
					   </div>
                 
<%
                 	if(bean.getId()>0){
                 %>
                 
                        <div class="col-md-6">
                      
						<label class="form-label">Product Name</label>
						
						<select name=productName class="form-control">
						
                        <option><%=bean.getProductName()%></option>
                        
						</select>
						
						</div>

<%
	}else{
%>
                        <div class="col-md-6">
                        
						<label class="form-label">Product Name</label>
						
						<select name = "productName" class="form-control">
						
						 <option value="------Select------">------Select------</option>
						 
						<%
							Iterator it1 = productList.iterator();
						
										while (it1.hasNext()) {
											
										ProductBean bean1 = (ProductBean) it1.next();
										
						%>
						
                        <option value="<%=bean1.getProductName()%>"><%=bean1.getProductName()%></option>
                        
						<%
							}
						%>
						
						</select>
						
					</div>

<%
	}
%>

							


						<div class="col-md-6">
						
						<label class="form-label">Product Quantity</label> <input type="text"
							class="form-control" name="quantity"
							placeholder="Enter Product Quantity here..." 
							value="<%=DataUtility.getStringData(bean.getQuantity())%>">
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%></div>
					
					</div>
					
					<%
											if(bean.getId()>0){
										%>
					
					<div class="col-md-6 text-center" style="margin-top: 2%; margin-left: 26%">
							
							<div class="form-group">
								<input type="submit" name="operation"
									value="<%=InventoryCtl.OP_UPDATE%>" class="btn btn-primary">
							</div>
							
						</div>
						
						
					<%
																		}else{
																	%>
					
					<div class="col-md-6 text-center" style="margin-top: 2%; margin-left: 26%">
							
							<div class="form-group">
								<input type="submit" name="operation"
									value="<%=InventoryCtl.OP_SUBMIT%>" class="btn btn-lg btn-success ">
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