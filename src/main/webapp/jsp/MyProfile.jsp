<%@page import="in.co.supply.chain.Controller.MyProfileCtl"%>

<%@page import="in.co.supply.chain.Controller.UserRegistrationCtl"%>

<%@page import="in.co.supply.chain.Utility.ServletUtility"%>

<%@page import="in.co.supply.chain.Utility.DataUtility"%>

<%@page import="in.co.supply.chain.Utility.HTMLUtility"%>

<%@page import="java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>MyProfile</title>

</head>

<body>

<%@ include file="Header.jsp" %>

<div id="fh5co-contact" class="fh5co-section-gray">

			<div class="container">
			
				<div class="row">
					
					<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						
						<h3>MYProfile</h3>
						<b><font color="red" class="text-center"> <%=ServletUtility.getErrorMessage(request)%></font></b>
						<b><font color="green" class="text-center"> <%=ServletUtility.getSuccessMessage(request)%>
						</font></b>
					
					</div>
			
			    </div>
			    
			    
			    
				
				<form action="<%=SCMView.MYPROFILE_CTL%>" method="post">
				
				<jsp:useBean id="bean" class="in.co.supply.chain.Bean.UserBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>">
				
					<div class="row animate-box">
					
						<div class="col-md-6">
						
							<div class="row">
							
							<div class="col-md-6">
							
								<label>First Name</label>
									
									<div class="form-group">
									
										<input type="text" class="form-control" placeholder="Enter First Name"
										name="firstName" value="<%=DataUtility.getStringData(bean.getFirstName())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
									
									</div>
									
								</div>
								
								
								
								
								
								<div class="col-md-6">
								
								<label>Last Name</label>
									
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Enter Last Name"
										name="lastName" value="<%=DataUtility.getStringData(bean.getLastName())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
									
									</div>
									
								</div>
								
								
								
								
								
								
								
								<div class="col-md-12">
								
								<label>Email</label>
									
									
									<div class="form-group">
									
										<input type="text" class="form-control" placeholder="Enter Email Id"
										name="login" value="<%=DataUtility.getStringData(bean.getEmail())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
									
									</div>
									
								</div>
								
					
							
							
							
							<div class="col-md-6">
								
								<label>Password</label>
									
									<div class="form-group">
										<input type="password" class="form-control" placeholder="Enter Password"
										name="password" value="<%=DataUtility.getStringData(bean.getPassword())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
									
									</div>
									
								</div>
								
								
								
								
								
						<div class="col-md-6">
								
						    <label>Role</label> 
						    
						    <div class="form-group">
						    
						    <%
							HashMap map = new HashMap();
						    
						    map.put("1", "Admin");
						    
							map.put("2", "Client");
							
							map.put("3", "Dealer");
							%>
								<%=HTMLUtility.getList("role",String.valueOf(bean.getRoleId()), map)%> 
				       	</div>
				     	
				       </div>
				     	
				     	

								
								
								<div class="col-md-12">
								
								<label>Phone No</label>
								
									<div class="form-group">
									
										<input type="text" class="form-control" placeholder="Enter Phone No"
										name="mobile" value="<%=DataUtility.getStringData(bean.getPhoneNo())%>">
										<font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
										
									</div>
									
								</div>
								
								
								
								<div class="col-md-12">
								
									<div class="form-group">
									
										<input type="submit" name="operation" value="<%=MyProfileCtl.OP_UPDATE%>" class="btn btn-primary">
									
									</div>
									
								</div>
								
							</div>
							
						</div>
						
					</div>
					
					
				</form>
	</div>
	
	</div>
	
<%@ include file="Footer.jsp" %>

</body>

</html>