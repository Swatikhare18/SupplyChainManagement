package in.co.supply.chain.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.InventoryBean;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Model.InventoryModel;
import in.co.supply.chain.Model.ProductModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "InventorieCtl",urlPatterns = "/Inventory")


public class InventoryCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
	public static final String OP_SUBMIT = "Submit";
	public static final String OP_UPDATE= "Update";

	
    public InventoryCtl() {
       
    	super();
    }
    
    @Override
	protected void preload(HttpServletRequest request) {
    	
    	ProductModel model = new ProductModel();
    	
    	ProductBean bean  = new ProductBean();
    	
		try {
			
			List productList = model.list();
			
			request.setAttribute("productName", productList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    
	}

	 @Override
		protected boolean validate(HttpServletRequest request) {
			
		 
		    
		 System.out.println("in validation");
			
		 boolean pass = true;

			
		 if (DataValidator.isNull(request.getParameter("productCost"))) {
				
				request.setAttribute("productCost", PropertyReader.getvalue("error.require", "productCost"));
				pass = false;

			}
			
			
		 if (DataValidator.isNull(request.getParameter("companyName"))) {
				
				request.setAttribute("companyName", PropertyReader.getvalue("error.require", "companyName"));
				pass = false;

			} 
			
			
		 if (DataValidator.isNull(request.getParameter("quantity"))) {
				
				request.setAttribute("quantity", PropertyReader.getvalue("error.require", "quantity"));
				pass = false;
			} 
			
			
		 if (DataValidator.isNull(request.getParameter("productName"))) {
				
				request.setAttribute("productName", PropertyReader.getvalue("error.require", "productName"));
				pass = false;
			} 
			
			
			
		 return pass;
		 
		}

	
	 @Override
	protected BaseBean populateBean(HttpServletRequest request) {
    
	 InventoryBean bean = new InventoryBean();
     
	 bean.setId(DataUtility.getLong(request.getParameter("id")));
    
	 bean.setProductCost(DataUtility.getString(request.getParameter("productCost")));
     
	 bean.setCompanyName(DataUtility.getString(request.getParameter("companyName")));
     
	 bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
     
	 bean.setProductName(DataUtility.getString(request.getParameter("productName")));
     
     System.out.println("request.getParameter(\"productName\"): "+request.getParameter("productName"));
     
     System.out.println("request.getParameter(\"productName1\"): "+bean.getProductName());
     
     
	 return bean;
	 
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		InventoryModel model = new InventoryModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		String productId = DataUtility.getString(request.getParameter("Productid"));
		
		System.out.println("productId" +productId);
		
		if (id>0){
			
			InventoryBean bean = null;
			
			try {
				
				bean = model.findBypk(id);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			ServletUtility.setbean(bean, request);
		}
	    
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in Do POST");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		InventoryModel model = new InventoryModel();
		
		InventoryBean bean = (InventoryBean) populateBean(request);
		
		System.out.println("pname :" + bean.getProductName());
//		String productName = DataUtility.getString(request.getParameter("productName"));
//		bean.setProductName(productName);
//	    System.out.println("ID :" +bean.getId());
	   
		if (bean.getId()>0){
	    		
			System.out.println("in post Update method");
				
			long i = model.update(bean);
				
			ServletUtility.setbean(bean, request);
				
			ServletUtility.setSuccessMessage("Update inventory Details", request);
		}
	    
		else {
				try {
					
					System.out.println("in post add method");
					
					long i = model.add(bean);
					
					ServletUtility.setbean(bean, request);
					
					ServletUtility.setSuccessMessage("Inventory Added Successfully", request);
					
				}catch (DuplicateRecordException e) {
					
					ServletUtility.setbean(bean, request);
					
					ServletUtility.setErrorMessage(e.getMessage(), request);
				}  
				catch (Exception e) {
					
					e.printStackTrace();
				}
				
	    }
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		
		return SCMView.Inventory_VIEW;
	}

	
}
