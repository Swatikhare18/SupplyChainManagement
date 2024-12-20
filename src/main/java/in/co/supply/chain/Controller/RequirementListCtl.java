package in.co.supply.chain.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Bean.RequirmentBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Model.ProductModel;
import in.co.supply.chain.Model.RequireMentModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "RequirementListCtl", urlPatterns = "/RequirementsListCtl")
public class RequirementListCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	public RequirementListCtl() {
		
		super();
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		RequirmentBean bean = new RequirmentBean();

		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setProductID(DataUtility.getString(request.getParameter("productid")));
		
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		
		bean.setQuantity(DataUtility.getString(request.getParameter("quantity")));
		
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		
		bean.setDiscription(DataUtility.getString(request.getParameter("description")));

		
		populateDTO(bean, request);
		
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("in Do Get");
		
		RequireMentModel model = new RequireMentModel();
		
		RequirmentBean bean = new RequirmentBean();
		
		long Sid = DataUtility.getLong(request.getParameter("Sid"));
		
		if (Sid > 0) {
			
			long i = model.sendDealer("SendDealer", Sid);
			
			ServletUtility.setSuccessMessage("Send Requirment To Dealer", request);
		}
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id > 0) {
			
			model.delete(id);
			
			ServletUtility.setSuccessMessage("Data Deleted SuccessFully", request);
		}
		
		List list = null;
		
		HttpSession session = request.getSession(false);
		
		UserBean bean2 = (UserBean) session.getAttribute("user");
		
		long roleid = bean2.getRoleId();
		
		if (roleid == 2) {
			
			try {
				list = model.UserRequirementlist(bean2.getId());
				
			} catch (Exception e) {
				
			}
			
		} else {
		try {
			
			list = model.Requirementlist();
			
			System.out.println("1111111111111");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}
		
		ServletUtility.setList(list, request);
		
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	

	@Override
	protected String getView() {
		
		return SCMView.Requirements_LIST_VIEW;
		
		
	}

}
