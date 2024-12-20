package in.co.supply.chain.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Model.ProductModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;

@WebServlet(name = "ProductCtl", urlPatterns = "/product")

public class ProductCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
	
	public static final String OP_SUBMIT = "Submit";
	
	public static final String OP_UPDATE = "Update";

	public ProductCtl() {
		
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
		
		return pass;
	}

	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		ProductBean bean = new ProductBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		// bean.setProductId(DataUtility.getLong(request.getParameter("productid")));
		// bean.setOrderId("orderid");
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
//		bean.setProductCost(DataUtility.getLong(request.getParameter("productcost")));
//		bean.setQuantity("quantity");
//		bean.setLocaton("location");
//		bean.setStatus("status");
//		bean.setUserid(0);
		populateDTO(bean, request);
		
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		ProductModel model = new ProductModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id > 0) {
			
			ProductBean bean = null;
			
			bean = model.findBypk(id);
			
			ServletUtility.setbean(bean, request);
		}
		
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		ProductModel model = new ProductModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		ProductBean bean = new ProductBean();
		
		bean = (ProductBean) populateBean(request);
		
		if (bean.getId() > 0) {
			
			long i = model.update(bean);
			
			ServletUtility.setbean(bean, request);
			
			ServletUtility.setSuccessMessage("Update", request);
			
		} else {
			
			try {
				
				try {
					
					long i = model.add(bean);
					
					ServletUtility.setbean(bean, request);
					
					
					ServletUtility.setSuccessMessage(" Product Added Successfully", request);
					
				} catch (DuplicateRecordException e) {
					
					ServletUtility.setbean(bean, request);
					
					ServletUtility.setErrorMessage(e.getMessage(), request);
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		ServletUtility.forward(getView(), request, response);
	}

	
	@Override
	protected String getView() {
		
		
		return SCMView.PRODUCT_VIEW;
		
	}

}
