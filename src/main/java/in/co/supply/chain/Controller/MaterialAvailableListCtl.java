package in.co.supply.chain.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.co.supply.chain.Bean.MaterialBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Model.MaterialAvailableModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.ServletUtility;


@WebServlet(name = "MaterialAvailableListCtl", urlPatterns = "/MaterialAvailableList")

public class MaterialAvailableListCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    public MaterialAvailableListCtl() {
       
    	
    	super();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MaterialAvailableModel  model = new MaterialAvailableModel();
		
		MaterialBean bean = new MaterialBean();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id>0) {
			
			
			model.delete(id);
			
			ServletUtility.setSuccessMessage("Data Deleted SuccessFully", request);
		}
		
		
		List list = null;
		
		HttpSession session = request.getSession(false);
		
		UserBean bean2 = (UserBean) session.getAttribute("user");
		
		long roleid = bean2.getRoleId();
		
		if (roleid == 2) {
			
			try {
				
				
				list = model.Materallist(bean2.getId());
				
			} catch (Exception e) {
			
			}
		} else {
		
			try {
			
			list = model.list();
			
			System.out.println("1111111111111");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}
		
		
		ServletUtility.setList(list, request);
		
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	@Override
	protected String getView() {
		
		
		return SCMView.MATERIAL_AVAILABLE_LIST_VIEW;
		
		
	}

}
