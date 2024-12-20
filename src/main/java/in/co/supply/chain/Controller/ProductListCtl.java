package in.co.supply.chain.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.co.supply.chain.Bean.ProductBean;
import in.co.supply.chain.Model.ProductModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.ServletUtility;



@WebServlet(name = "ProductListCtl",urlPatterns = "/productList")
public class ProductListCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    
	public ProductListCtl() {
       
		super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductModel  model = new ProductModel();
		
		ProductBean bean = new ProductBean();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		String pname  = DataUtility.getString(request.getParameter("pname"));
		
		if (id>0) {
			
			long i = model.delete(pname);
			
			System.out.println("III :" + pname);
			
			System.out.println("III :" + id);
			
			ServletUtility.setSuccessMessage("Data Deleted SuccessFully", request);
		}
		
		List list = null;
		
		try {
			
			list = model.list();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		ServletUtility.setList(list, request);
		
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	

	@Override
	protected String getView() {
		
		return SCMView.PRODUCT_LIST_VIEW;
	}
	

}
