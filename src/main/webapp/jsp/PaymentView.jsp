<%@page import="in.co.supply.chain.Utility.DataUtility"%>

<%@page import="in.co.supply.chain.Controller.PaymentCtl"%>

<%@page import="in.co.supply.chain.Utility.ServletUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>PaymentView</title>

</head>

<body>

	<%@ include file="Header.jsp"%>
	
	<br>
	
	
	
	
	
	<div class="container" style="position: relative; min-height: 50vh">

		<main class="login-form">
			<div class="cotainer">
				<div class="row justify-content-center">
					<div class="col-md-10">
						<div class="card">
						
						
						
							
							<div class="card-header">
								<div
									class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
									<div class="col-md-12" style="margin-left: 10%; margin-top: 1%">
										<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
										<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
										<h4 class="text-center" style="color:green">Payment</h4>
									</div>
								</div>
							</div>
							
							
							
							
							<div class="card-body">
								<form action="<%=SCMView.PAYMENT_CTL%>" method="post">
									<div class="form-group row">
										<jsp:useBean id="bean" scope="request"
											class="in.co.supply.chain.Bean.PaymentBean" />


										<input type="hidden" name="id" value="<%=bean.getId()%>">
										<input type="hidden" name="userid"
											value="<%=bean.getUserId()%>"> <label
											class="col-md-4 col-form-label text-md-right mt-3">Amount<font
											color="red">*</font></label>
											
											
											
										<div class="col-md-6">
											<input type="text" class="form-control"
												placeholder="Enter amount No" readonly="readonly"
												name="chargeAmount"
												value="<%=DataUtility.getStringData(bean.getAmount())%>">
										</div>
										
										<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("chargeAmount", request)%></div>

										<label class="col-md-4 col-form-label text-md-right mt-3">Card
											Number<font color="red">*</font>
										</label>
										
										
										
										<div class="col-md-6">
											<input type="text" class="form-control"
												placeholder="Enter Card No" name="cardNo">
										</div>
										
										
										
										<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("cardNo", request)%></div>


										<label class="col-md-4 col-form-label text-md-right mt-3">CVV<font
											color="red">*</font></label>
											
											
											
										<div class="col-md-6">
											<input type="text" class="form-control"
												placeholder="Enter CVV No" name="cvv">
										</div>
										
										<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("cvv", request)%></div>


										<label class="col-md-4 col-form-label text-md-right mt-3">Card
											Expairy<font color="red">*</font>
										</label>
										
										
										
										<div class="col-md-6">
											<input type="text" class="form-control"
												placeholder="Enter Expairy" name="cardexpairy">
										</div>
										
										
										
										<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("cardexpairy", request)%></div>

									</div>
									
									<br>
									
									
									<div class="col-md-12" style="margin-left: 50%; margin-top: 1%">
										<input type="submit" name="operation" class="btn btn-lg btn-success"
											value="<%=PaymentCtl.OP_PAY%>">
									</div>



								</form>
								
							</div>
							
						</div>
						
					</div>
					
				</div>
				
			</div>
			
		</main>
		
	</div>
	
	<br>
	
	<%@ include file="Footer.jsp"%>
	
</body>

</html>