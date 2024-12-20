package in.co.supply.chain.Controller;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.InventoryBean;
import in.co.supply.chain.Bean.MaterialBean;
import in.co.supply.chain.Bean.RequirmentBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Model.InventoryModel;
import in.co.supply.chain.Model.MaterialAvailableModel;
import in.co.supply.chain.Model.RequireMentModel;
import in.co.supply.chain.Model.UserModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "MaterialAvailableCtl", urlPatterns = "/MaterialAvailable")

public class MaterialAvailableCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	public MaterialAvailableCtl() {
		
		super();
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		
		System.out.println("in validation");
		
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productId"))) {
			
			request.setAttribute("productId", PropertyReader.getvalue("error.require", "Product Id"));
			pass = false;

		}
		
		
		if (DataValidator.isNull(request.getParameter("ProductName"))) {
			
			
			request.setAttribute("ProductName", PropertyReader.getvalue("error.require", "Product Name"));
			pass = false;

		} 
		
		
		if (DataValidator.isNull(request.getParameter("cost"))) {
			
			request.setAttribute("cost", PropertyReader.getvalue("error.require", "Product Cost"));
			pass = false;

		} 
		
		
		if (DataValidator.isNull(request.getParameter("CompanyName"))) {
			
			request.setAttribute("CompanyName", PropertyReader.getvalue("error.require", "Company Name"));
			pass = false;

		} 
		
		
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			
			request.setAttribute("quantity", PropertyReader.getvalue("error.require", "Quantity"));
			pass = false;

		} 
		
		return pass;
	}
	
	
	
	

	@Override
	protected void preload(HttpServletRequest request) {

		
		InventoryModel model = new InventoryModel();
		
		List modelList = model.list();
		
		request.setAttribute("companyName", modelList);
		
		request.setAttribute("productQuantity", modelList);

//		Iterator it1 = modelList.iterator();
//		while (it1.hasNext()) {
//			InventorieBean bean1 = (InventorieBean) it1.next();
//	     	String list = (String)bean1.getProductName();
//             System.out.println("companyName :"+list);
//		}
//		
//			RequireMentModel ReqModel = new RequireMentModel();
//			List Reqlist = ReqModel.Requirementlist();
//			request.setAttribute("Pname", Reqlist);
//			Iterator it2 = Reqlist.iterator();
//			while (it2.hasNext()) {
//				RequirmentBean  bean2 = (RequirmentBean) it2.next();
//		     	   String list1 =  bean2.getProductName();
//	             System.out.println("PName :"+list1);
//			}
//			
//			
//			List onelist = model.list();
//			List twolist = ReqModel.Requirementlist();
//
//			
//			if (onelist.equals(twolist)) {
//				System.out.println("true");
//			}else {
//				System.out.println("false");
//			}

//    			boolean isEqual = onelist.equals(twolist);
//			    System.out.println("isEqual :" + isEqual);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		System.out.println("in DO GET  condition");
		
		RequireMentModel model11 = new RequireMentModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		System.out.println("IDSSSS : " + id);
		
		if (id>0) {
			
			
			long l = model11.Sent("Required", id);
		}
		
		
		MaterialBean bean = new MaterialBean();
		
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		String productId = DataUtility.getString(request.getParameter("Productid"));
		
		String productName = DataUtility.getString(request.getParameter("ProductName"));
		
		String quantity = DataUtility.getString(request.getParameter("quantity"));
		
		long userid = DataUtility.getLong(request.getParameter("userid"));
		
		
		bean.setProductID(productId);
		
		bean.setProductName(productName);
		
		bean.setQuantity(quantity);
		
		bean.setUserId(userid);
		
		bean.getProductName();

		// get model
		InventoryModel model = new InventoryModel();
			
		try {
				
				InventoryBean bean1 = new InventoryBean();
				
				bean1 = model.findBypNAme(bean.getProductName());
				
				if (bean1 ==  null) {
					//ServletUtility.setbean(bean1, request);
					
					ServletUtility.setErrorMessage("Products out of Inventery", request);
					//ServletUtility.redirect(SCMView.DEALER_Requirements_LIST_Ctl, request, response);
					//ServletUtility.forward(SCMView.DEALER_Requirements_LIST_Ctl, request, response);
					ServletUtility.forward(SCMView.DEALER_Requirements_LIST_VIEW, request, response);
					
					return;
				}
				
				
				String s1 = bean1.getProductName();
				
				String s2 = bean.getProductName();
				
				System.out.println("Equls :" + s1.equals(s2));
				
				if (s1.equals(s2)) {
					
					
					String cost = bean1.getProductCost();
					
					long intcost = Long.parseLong(cost);
					
					String quantitis = bean.getQuantity();
					
					long quaInt = Long.parseLong(quantitis);
					
					long totalCost = (intcost * quaInt);
					
					String costtotal = String.valueOf(totalCost);
					
					bean.setTotalCost(costtotal);
					
					String cName = bean1.getCompanyName();
					
					bean.setCompanyName(cName);
					
					String totalquantity = bean1.getQuantity();
					
					bean.setTotalquantity(totalquantity);
					
					long qun = Long.parseLong(totalquantity);
					
					String reqquan = bean.getQuantity();
					
					long reqqua = Long.parseLong(reqquan);
					
					long lessquan = (qun - reqqua);
					
					String totalless = String.valueOf(lessquan);
					
					bean.setLessQuantity(totalless);
					
					long id2 = bean1.getId();
					
					bean.setID(id2);

				} else {
					
					
					System.out.println("NOT EQUALS :" + !s1.equals(s2));
					
					ServletUtility.setErrorMessage("Product Not Available", request);
					
					ServletUtility.forward(SCMView.DEALER_Requirements_LIST_Ctl, request, response);
					
					return;
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		
			ServletUtility.setbean(bean, request);
		
			ServletUtility.forward(getView(), request, response);
	}


	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		
		MaterialBean bean = new MaterialBean();
		
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setCompanyName(DataUtility.getString(request.getParameter("CompanyName")));
		
		bean.setProductName(DataUtility.getString(request.getParameter("ProductName")));
		
		bean.setProductID(DataUtility.getString(request.getParameter("productId")));
		
		bean.setTotalCost(DataUtility.getString(request.getParameter("cost")));
		
		bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
		
		bean.setUserId(DataUtility.getLong(request.getParameter("userid")));
		
		bean.setTotalquantity(DataUtility.getString(request.getParameter("totalquantity")));
		
		bean.setStatus("pay");
		
		populateDTO(bean, request);
		
		return bean;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long id1 = DataUtility.getLong(request.getParameter("id2"));
		
		String lessproduct = DataUtility.getString(request.getParameter("lessquantity"));
		
		InventoryModel model1 = new InventoryModel();
		
		InventoryBean bean2 = new InventoryBean();
		
		System.out.println("in Do Post");
		
		MaterialAvailableModel model = new MaterialAvailableModel();
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		MaterialBean bean = new MaterialBean();
		
		bean = (MaterialBean) populateBean(request);

		if (OP_SUBMIT.equalsIgnoreCase(op)) {
			
			try {
				
				long IDS  = model1.lessQuan(lessproduct, id1);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			try {
				
				long pk = model.add(bean);
				
				ServletUtility.setbean(bean, request);
				
				ServletUtility.setSuccessMessage("Successfully", request);
				
				ServletUtility.forward(getView(), request, response);
				
				
				return;
				
			} catch (DuplicateRecordException e) {
				
				ServletUtility.setbean(bean, request);
				
				ServletUtility.setErrorMessage(e.getMessage(), request);

			} catch (ApplicationException e) {
				
				e.printStackTrace();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			ServletUtility.forward(getView(), request, response);
		}
	}

	
	@Override
	protected String getView() {
		
		
		return SCMView.MATERIAL_AVAILABLE_VIEW;
	}

}
