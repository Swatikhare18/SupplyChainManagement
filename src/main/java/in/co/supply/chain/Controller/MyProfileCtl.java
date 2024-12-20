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
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "MyProfileCtl",urlPatterns = "/MyprofileCtl")

public class MyProfileCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    public MyProfileCtl() {
        
    	super();
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
		 
		HttpSession session = request.getSession(true);
		
		UserBean UserBean = (UserBean) session.getAttribute("user");
       
		long id = UserBean.getId();
        
		String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        UserModel model = new UserModel();
        
        if (id > 0 || op != null) {
            
        	System.out.println("in id > 0  condition");
            
        	UserBean bean;
            
            try {
               
            	bean = model.findByPk(id);
                
            	ServletUtility.setbean(bean, request);
                
            } catch (Exception e) {        
            	
            	e.printStackTrace();
            	//ApplicationException
                
            	ServletUtility.handleException(e, request, response);
                
            	return;
            }
        }
       
        ServletUtility.forward(getView(), request, response);
	
	      }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("in DOPOST");
		
		UserModel model = new UserModel();
		
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		UserBean bean = new UserBean();
		
		bean = (UserBean) populateBean(request);
		
		if (OP_UPDATE.equalsIgnoreCase(op)){
			
			System.out.println("in do post2");
			
			long i = model.Update(bean);
			
			
			System.out.println("RoleName :" +bean.getRoleName());
			
			System.out.println("RoleId :" +bean.getRoleId());
			
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
		
		return SCMView.MYPROFILE_VIEW;
	}

}
