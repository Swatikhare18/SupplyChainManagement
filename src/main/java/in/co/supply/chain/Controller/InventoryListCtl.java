package in.co.supply.chain.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.InventoryBean;
import in.co.supply.chain.Model.InventoryModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "InventryListCtl" ,urlPatterns = "/InventoryList")

public class InventoryListCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    public InventoryListCtl() {
        
    	super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
    	InventoryModel model = new InventoryModel();
		
		InventoryBean  bean = new InventoryBean();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		
		if (id>0) {
			
			model.Delete(id);
			
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}
		
		List list = null;
		
		try {
			
			list = model.list();
			
		} catch (Exception e) {
		}
		
	   ServletUtility.setList(list, request);
	   
	   ServletUtility.forward(getView(), request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

	
	
	@Override
	protected String getView() {
		
		return SCMView.INVENTORY_LIST_VIEW;
	}

}
