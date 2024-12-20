package in.co.supply.chain.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Bean.RequirmentBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Model.ProductModel;
import in.co.supply.chain.Model.RequireMentModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;




@WebServlet(name = "RequirementsCtl" , urlPatterns = "/RequirementsCtl")
public class RequirementsCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
	
	public static final String OP_SUBMIT = "Submit";
	
       
    public RequirementsCtl() {
        
    	super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		
    	System.out.println("in validation");
		
    	boolean pass = true;
		
    	
    	if (DataValidator.isNull(request.getParameter("productName"))) {
			
    		request.setAttribute("productName", PropertyReader.getvalue("error.require", "productName"));
			pass = false;
		}
    	
		
    	
    	if (DataValidator.isNull(request.getParameter("productid"))) {
			
			request.setAttribute("productid", PropertyReader.getvalue("error.require", "productid"));
			pass = false;
		}
		
		
    	
    	if (DataValidator.isNull(request.getParameter("quantity"))) {
			
			request.setAttribute("quantity", PropertyReader.getvalue("error.require", "quantity"));
			pass = false;
		}
		
		
    	if (DataValidator.isNull(request.getParameter("location"))) {
			
			request.setAttribute("location", PropertyReader.getvalue("error.require", "location"));
			pass = false;
		}
		
		
    	if (DataValidator.isNull(request.getParameter("description"))) {
			
			request.setAttribute("description", PropertyReader.getvalue("error.require", "description"));
			pass = false;
		}
		
		
		
		return pass;
		
	}
    
    
    @Override
	protected BaseBean populateBean(HttpServletRequest request) {
		RequirmentBean  bean = new RequirmentBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setProductID(DataUtility.getString(request.getParameter("productid")));
		
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		
		bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
		
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		
		bean.setDiscription(DataUtility.getString(request.getParameter("description")));
		
		bean.setStatus("required");
		
		bean.setRequire("req");
		
		HttpSession session = request.getSession();
	    
		UserBean userid =	(UserBean) session.getAttribute("user");
	    
		long userID  = userid.getId();
	    
		bean.setUserId(userID);
		
		populateDTO(bean, request);
		
		return bean;
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		RequirmentBean bean = new RequirmentBean();
		
		String productName = DataUtility.getString(request.getParameter("productName"));
		
		bean.setProductName(productName);
		
		ServletUtility.setbean(bean, request);
		
		ServletUtility.forward(getView(), request, response);
		
	   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		RequireMentModel model = new RequireMentModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		String productName = DataUtility.getString(request.getParameter("productName"));
		
		RequirmentBean reqBean = new RequirmentBean();
		
		reqBean.setProductName(productName);
		
		RequirmentBean bean = new RequirmentBean();
          
		bean = (RequirmentBean) populateBean(request);
         
		if (OP_SUBMIT.equalsIgnoreCase(op)){
			
			try {
						
					System.out.println("in post add method");
						
					long i = model.add(bean);
						
					ServletUtility.setbean(bean, request);
						
					ServletUtility.setSuccessMessage(" Requirement Added Successfully", request);
					
					} catch (DuplicateRecordException e) {
						
						ServletUtility.setbean(bean, request);
						
						ServletUtility.setErrorMessage(e.getMessage(), request);
						
						ServletUtility.forward(getView(), request, response);
						
					} catch (ApplicationException e) {
						
						e.printStackTrace();
						
					} catch (Exception e) {
						
						e.printStackTrace();
						
					}
	    }
		
		ServletUtility.forward(getView(), request, response);
	}

	
	@Override
	protected String getView() {
		
		
		return SCMView.Requirements_VIEW;
		
	}

}
