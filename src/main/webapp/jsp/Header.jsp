<%@page import="in.co.supply.chain.Controller.LoginCtl"%>

<%@page import="in.co.supply.chain.Controller.SCMView"%>

<%@page import="in.co.supply.chain.Bean.UserBean" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

    <meta property="og:title" content=""/>

	<meta property="og:image" content=""/>
	
	<meta property="og:url" content=""/>
	
	<meta property="og:site_name" content=""/>
	
	<meta property="og:description" content=""/>
	
	<meta name="twitter:title" content="" />
	
	<meta name="twitter:image" content="" />
	
	<meta name="twitter:url" content="" />
	
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
</head>

<body>

<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
      //  String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + "("+userBean.getRoleName()+")";
        
    } else {
    	
        welcomeMsg += "Guest";
    }

%>

<header id="fh5co-header-section">

			<div class="container">
			
				<div class="nav-header">
				
					<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
					
					<h1 id="fh5co-logo"><a href="<%=SCMView.WELCOME_CTL%>">Supply Chain Management</a></h1>
					
					<!-- START #fh5co-menu-wrap -->
					
					<nav id="fh5co-menu-wrap" role="navigation">
					
						<ul class="sf-menu" id="fh5co-primary-menu">
						
							<li class="active"><a href="<%=SCMView.WELCOME_CTL%>">Home</a></li>
							
							
							<%if(userLoggedIn){ %>
							
							<%if(userBean.getRoleId()==1){%>
							
							<li><a href="<%=SCMView.USER_LIST_CTL%>">Users List</a></li>
								
							<li><a href="<%=SCMView.PRODUCT_CTL%>">Add Products </a></li>
							
							<li><a href="<%=SCMView.PRODUCT_LIST_CTL%>">products List</a></li>
							
							<li><a href="<%=SCMView.Inventory_CTL%>">Inventory</a></li>
							
							<li><a href="<%=SCMView.INVENTORY_LIST_CTL%>">Inventory List</a></li>
							
							<li><a href="<%=SCMView.Requirements_LIST_Ctl%>">Check Requirement</a></li>
							
							<li><a href="<%=SCMView.MATERIAL_AVAILABLE_LIST_CTL%>">Check Material</a></li>
							
							<li><a href="<%=SCMView.PAYMENT_LIST_CTL%>">payments History</a></li>
							
							<%}else if(userBean.getRoleId()==2){ %>
							
							<li><a href="<%=SCMView.PRODUCT_LIST_CTL%>">Products List</a></li>
							
							<li><a href="<%=SCMView.MATERIAL_AVAILABLE_LIST_CTL%>">Check Material</a></li>
							
							<li><a href="<%=SCMView.PAYMENT_LIST_CTL%>">payments History</a></li>
							
							
							<%}else if(userBean.getRoleId()==3){ %>
							
							<li><a href="<%=SCMView.Inventory_CTL%>">Inventory</a></li>
							
							<li><a href="<%=SCMView.INVENTORY_LIST_CTL%>">Inventory List</a></li>
							
							<li><a href="<%=SCMView.DEALER_Requirements_LIST_Ctl%>">Check Requirement</a></li>
							
							<%} %>
							
							<li>
								<a href="" class="fh5co-sub-ddown"><%=welcomeMsg%></a>
								
								<ul class="fh5co-sub-menu">
								
									 <li><a href="<%=SCMView.MYPROFILE_CTL%>">My Profile</a></li>
									 
									<!--<li><a href="">Change Password</a></li> -->
									
									<li><a href="<%=SCMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a></li>
									
								</ul>
								
							</li>
							
							
							<%}else{ %>
							<li class="active" ><a href="<%=SCMView.LOGIN_CTL%>">Sign In</a></li>
							
							<li class="active"><a href="<%=SCMView.USER_REGISTRATION_CTL%>">Sign Up</a></li>
							
							<li><a href=""><%=welcomeMsg%></a></li>
							
							<%} %>
							
						</ul>
					</nav>
				</div>
			</div>
			
		</header>
		
</body>

</html>