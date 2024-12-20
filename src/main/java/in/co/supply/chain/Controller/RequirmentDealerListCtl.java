package in.co.supply.chain.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.RequirmentBean;
import in.co.supply.chain.Model.RequireMentModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.ServletUtility;



@WebServlet(name = "RequirmentDealerListCtl", urlPatterns = "/DealerRequirementsListCtl")
public class RequirmentDealerListCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    public RequirmentDealerListCtl() {
        
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		try {
			
			List list = model.Requirementlist();
			
			ServletUtility.setList(list, request);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		ServletUtility.forward(getView(), request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	@Override
	protected String getView() {
		
		return SCMView.DEALER_Requirements_LIST_VIEW;
	}

}
