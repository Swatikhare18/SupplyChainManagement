package in.co.supply.chain.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Model.UserModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "UserCtl",urlPatterns = "/userCtl")
public class UserCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    public UserCtl() {
        
    	super();
    }
   
    @Override
	protected boolean validate(HttpServletRequest request) {
		
    	System.out.println("in validation");
		
    	boolean pass = true;

		
    	if (DataValidator.isNull(request.getParameter("firstName"))) {
			
			request.setAttribute("firstName", PropertyReader.getvalue("error.require", "First Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			
			request.setAttribute("firstName", PropertyReader.getvalue("error.name", " First Name"));
			pass = false;
		}
		
		
		
    	if (DataValidator.isNull(request.getParameter("lastName"))) {
			
			request.setAttribute("lastName", PropertyReader.getvalue("error.require", "Last Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			
			request.setAttribute("lastName", PropertyReader.getvalue("error.name", " LastName"));
			pass = false;
		}

		
		
    	if (DataValidator.isNull(request.getParameter("login"))) {
			
			request.setAttribute("login", PropertyReader.getvalue("error.require", "Email Id"));
			pass = false;

		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			
			request.setAttribute("login", PropertyReader.getvalue("error.login", "Email Id"));
			pass = false;
		}

		
    	if (DataValidator.isNull(request.getParameter("password"))) {
			
			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
			pass = false;

		}
		
    	else if (!DataValidator.isPassword(request.getParameter("password"))) {
			
			request.setAttribute("password", PropertyReader.getvalue("error.password", "Password"));
			return false;
		}

		
    	if (DataValidator.isNull(request.getParameter("mobile"))) {
			
			request.setAttribute("mobile", PropertyReader.getvalue("error.require", "mobile No"));
			pass = false;
		}
		
    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			
			request.setAttribute("role", PropertyReader.getvalue("error.require", "Role"));
			pass = false;
		}
		
		return pass;
		
	}

    
@Override
protected BaseBean populateBean(HttpServletRequest request) {
	
	UserBean bean = new UserBean();

	
	bean.setId(DataUtility.getLong(request.getParameter("id")));
	
	bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
	
	bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
	
	bean.setEmail(DataUtility.getString(request.getParameter("login")));
	
	bean.setPassword(DataUtility.getString(request.getParameter("password")));
	
	bean.setPhoneNo(DataUtility.getString(request.getParameter("mobile")));
	
	bean.setRoleId(DataUtility.getLong(request.getParameter("role")));
	
	
	
	
	if (bean.getRoleId() == 2) {
		
		bean.setRoleName("Client");
		
	} else {
		
		bean.setRoleName("Dealer");
	}
	
	populateDTO(bean, request);
	
	return bean;
	
}




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserModel model = new UserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id > 0) {
			
			UserBean bean = null;
			
			try {
				
				bean = model.findByPk(id);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			ServletUtility.setbean(bean, request);
		}
		
		ServletUtility.forward(getView(), request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserModel model = new UserModel();
		
		System.out.println("in do post");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		UserBean bean = new UserBean();
		
		bean = (UserBean) populateBean(request);

		
		
		if (bean.getId() > 0) {
			
			System.out.println("in do post2");
			
			long i = model.Update(bean);
			
			ServletUtility.setSuccessMessage("Data Updated Successfully", request);
		} else {
			
			try {
				
				long pk = model.add(bean);
				
				ServletUtility.setSuccessMessage("Data Add Successfully", request);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		ServletUtility.forward(getView(), request, response);
		
	}

	@Override
	protected String getView() {
		
		
		return SCMView.USER_VIEW;
		
	}

}
