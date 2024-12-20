package in.co.supply.chain.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Model.UserModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;



@WebServlet(name = "LoginCtl", urlPatterns = "/login")


public class LoginCtl extends BaseCtl {
	
	
	private static final long serialVersionUID = 1L;

	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SINGUP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		boolean pass = true;

		String op = request.getParameter("operation");
		
		
		
		if (OP_SINGUP.equalsIgnoreCase(op) || OP_LOG_OUT.equalsIgnoreCase(op)) {
			
			return pass;
		}
		
		String login = request.getParameter("login");

		
		if (DataValidator.isNull(login)) {
			
			request.setAttribute("login", PropertyReader.getvalue("error.require", "Email Id"));
			pass = false;

		} else if (!DataValidator.isEmail(login)) {
			
			request.setAttribute("login", PropertyReader.getvalue("error.email", "Email Id "));
			pass = false;
		}
		
		
		
		if (DataValidator.isNull(request.getParameter("password"))) {
			
			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
			pass = false;
		}
		
		return pass;
	}

	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		
		UserBean bean = new UserBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setEmail(DataUtility.getString(request.getParameter("login")));
		
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		
		return bean;
	}

	
	public LoginCtl() {
		
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		UserBean bean = (UserBean)populateBean(request);
		
		
		if (OP_LOG_OUT.equalsIgnoreCase(op)) {
			
			
			session = request.getSession(false);
			
			session.invalidate();
			
			ServletUtility.setSuccessMessage("You have been logged out successfully", request);
			
			ServletUtility.forward(getView(), request, response);
			
			return;
		}
		
		ServletUtility.forward(getView(), request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel model = new UserModel();

		if (OP_SINGUP.equalsIgnoreCase(op)) {
			
			ServletUtility.redirect(SCMView.USER_REGISTRATION_CTL, request, response);
			
			return;
		}
		

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			
			UserBean bean = (UserBean) populateBean(request);
			
			try {
				
				bean = model.Authenticate(bean.getEmail(), bean.getPassword());
				
				if (bean != null) {
						
			            
					session.setAttribute("user", bean);
						
					ServletUtility.setbean(bean, request);
						
					ServletUtility.redirect(SCMView.WELCOME_CTL, request, response);
						
					return;
					
				} else {
					
					
					bean = (UserBean) populateBean(request);
					
					ServletUtility.setbean(bean, request);
					
					ServletUtility.setErrorMessage("Invalid Id and Password", request);
					
					ServletUtility.forward(getView(), request, response);
				}
				
			} catch (Exception e) {
			}

		}

		ServletUtility.forward(getView(), request, response);
		
	}

	@Override
	protected String getView() {
		
		return SCMView.LOGIN_VIEW;
	}

}
