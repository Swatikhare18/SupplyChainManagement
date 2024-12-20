package in.co.supply.chain.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import in.co.supply.chain.Bean.BaseBean;
import in.co.supply.chain.Bean.PaymentBean;
import in.co.supply.chain.Bean.UserBean;
import in.co.supply.chain.Exception.ApplicationException;
import in.co.supply.chain.Exception.DuplicateRecordException;
import in.co.supply.chain.Model.MaterialAvailableModel;
import in.co.supply.chain.Model.PaymentModel;
import in.co.supply.chain.Utility.DataUtility;
import in.co.supply.chain.Utility.DataValidator;
import in.co.supply.chain.Utility.PropertyReader;
import in.co.supply.chain.Utility.ServletUtility;



@WebServlet(name = "PaymentCtl", urlPatterns = "/paymentCtl")


public class PaymentCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;
       
    public PaymentCtl() {
        
    	super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {
		
    	
    	System.out.println("in validation");
		
    	boolean pass = true;
		
		
    	if (DataValidator.isNull(request.getParameter("cardNo"))) {
			
			request.setAttribute("cardNo", PropertyReader.getvalue("error.require", "Card Number"));
			pass = false;
		}
		
		
		
    	if (DataValidator.isNull(request.getParameter("cvv"))) {
			
			request.setAttribute("cvv", PropertyReader.getvalue("error.require", "CVV"));
			pass = false;
		}
		
		
    	if (DataValidator.isNull(request.getParameter("cardexpairy"))) {
			
			request.setAttribute("cardexpairy", PropertyReader.getvalue("error.require", "Card Expairy"));
			pass = false;
		}
		
		return pass;
	}
    
	
    @Override
	protected BaseBean populateBean(HttpServletRequest request) {
		
		PaymentBean bean = new PaymentBean();
		
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setAmount(DataUtility.getString(request.getParameter("chargeAmount")));
		
		bean.setCardNo(DataUtility.getString(request.getParameter("cardNo")));
		
		bean.setCvv(DataUtility.getString(request.getParameter("cvv")));
		
		bean.setCardExpairy(DataUtility.getString(request.getParameter("cardexpairy")));
		
		bean.setStatus("pay");
		
		
		HttpSession session = request.getSession();
		
	    UserBean userid =	(UserBean) session.getAttribute("user");
	    
	    long userID  = userid.getId();
	    
	    bean.setUserId(userID);
		
	    populateDTO(bean, request);
		
		return bean;
	}

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		MaterialAvailableModel model = new MaterialAvailableModel();
		
		
		if (id>0) {
			
			long l = model.Paid("Paid", id);
		}
		
		PaymentBean bean = new PaymentBean();
		
		String totalcost = DataUtility.getString(request.getParameter("totalcost"));
		
		
		bean.setAmount(totalcost);
		
		ServletUtility.setbean(bean, request);
		
		ServletUtility.forward(getView(), request, response);
	}

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in Do Post");
		
		PaymentModel model = new PaymentModel();
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		PaymentBean bean = new PaymentBean();
		
		bean = (PaymentBean) populateBean(request);
		
		if (OP_PAY.equalsIgnoreCase(op)) {
				
			
			try {
					
					long pk = model.add(bean);
					
					ServletUtility.setbean(bean, request);
					
					ServletUtility.setSuccessMessage("Payment Done Successfully", request);
					
					ServletUtility.forward(getView(), request, response);
					
					return;
					
				} catch (DuplicateRecordException e) {
					
					ServletUtility.setbean(bean, request);
					
					ServletUtility.setErrorMessage(e.getMessage(), request);
					
					ServletUtility.forward(getView(), request, response);
					
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
		
		
		return SCMView.PAYMENT_VIEW;
		
	}

}
