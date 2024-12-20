package in.co.supply.chain.Controller;

public interface SCMView {
	
	public String APP_CONTEXT = "/SupplyChainManagement";
	public String PAGE_FOLEDER = "/jsp";
	
	
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String WELCOME_VIEW = PAGE_FOLEDER + "/Welcome.jsp";
	
	
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String LOGIN_VIEW = PAGE_FOLEDER + "/LoginView.jsp";
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/registration";
	public String USER_REGISTRATION_VIEW = PAGE_FOLEDER +"/UserRegistrationView.jsp";
	
	
	public String USER_LIST_CTL = APP_CONTEXT +  "/userListCtl";
	public String USER_LIST_VIEW = PAGE_FOLEDER + "/UserList.jsp";
	
	
	public String USER_CTL = APP_CONTEXT +  "/userCtl";
	public String USER_VIEW = PAGE_FOLEDER + "/UserView.jsp";
	
	
	public String PRODUCT_CTL = APP_CONTEXT + "/product";
	public String PRODUCT_VIEW = PAGE_FOLEDER + "/ProductView.jsp";
	
	
	public String PRODUCT_LIST_CTL  = APP_CONTEXT + "/productList";
	public String PRODUCT_LIST_VIEW = PAGE_FOLEDER + "/ProductList.jsp";
	
	
	public String Inventory_CTL = APP_CONTEXT + "/Inventory";
	public String Inventory_VIEW = PAGE_FOLEDER + "/InventoryView.jsp";
	
	
	public String INVENTORY_LIST_CTL = APP_CONTEXT +  "/InventoryList";
	public String INVENTORY_LIST_VIEW = PAGE_FOLEDER + "/InventoryListView.jsp";
	
	
	public String Requirements_Ctl = APP_CONTEXT + "/RequirementsCtl";
	public String Requirements_VIEW = PAGE_FOLEDER + "/RequirementsView.jsp";
	
	
	public String Requirements_LIST_Ctl = APP_CONTEXT + "/RequirementsListCtl";
	public String Requirements_LIST_VIEW = PAGE_FOLEDER + "/RequirementListView.jsp";
	
	
	public String DEALER_Requirements_LIST_Ctl = APP_CONTEXT + "/DealerRequirementsListCtl";
	public String DEALER_Requirements_LIST_VIEW = PAGE_FOLEDER + "/RequirmentDealerView.jsp";
	
	
	public String MATERIAL_AVAILABLE_CTL = APP_CONTEXT + "/MaterialAvailable";
	public String MATERIAL_AVAILABLE_VIEW = PAGE_FOLEDER + "/MaterialAvailable.jsp";
	
	
	public String MYPROFILE_CTL = APP_CONTEXT + "/MyprofileCtl";
	public String MYPROFILE_VIEW  = PAGE_FOLEDER + "/MyProfile.jsp";
	
	
	public String MATERIAL_AVAILABLE_LIST_CTL = APP_CONTEXT + "/MaterialAvailableList";
	public String MATERIAL_AVAILABLE_LIST_VIEW = PAGE_FOLEDER + "/MaterialAvailableListView.jsp";
	
	
	public String PAYMENT_CTL = APP_CONTEXT + "/paymentCtl";
	public String PAYMENT_VIEW = PAGE_FOLEDER + "/PaymentView.jsp";
	
	
	public String PAYMENT_LIST_CTL = APP_CONTEXT + "/paymentlistCtl";
	public String PAYMENT_LIST_VIEW = PAGE_FOLEDER + "/PaymentListView.jsp";
}
