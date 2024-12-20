package in.co.supply.chain.Bean;



public class MaterialBean extends BaseBean{

	private String productID;
	private String productName;
	private String totalCost;
	private String companyName;
	private String quantity;
	private String totalquantity;
	private String lessQuantity;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	
	private long ID;
	
   
	public long getID() {
		return ID;
	}

	
	public void setID(long iD) {
		ID = iD;
	}

	
	public String getLessQuantity() {
		return lessQuantity;
	}

	
	public void setLessQuantity(String lessQuantity) {
		this.lessQuantity = lessQuantity;
	}

	
	public String getTotalquantity() {
		return totalquantity;
	}

	
	public void setTotalquantity(String totalquantity) {
		this.totalquantity = totalquantity;
	}

	
	private String invqun;
    private String pName;
	private long userId;
	
	
	
	public long getUserId() {
		return userId;
	}

	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	public String getInvqun() {
		return invqun;
	}

	
	public void setInvqun(String invqun) {
		this.invqun = invqun;
	}

	
	public String getpName() {
		return pName;
	}

	
	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getQuantity() {
		return quantity;
	}

	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	
	public String getProductID() {
		return productID;
	}

	
	public void setProductID(String productID) {
		this.productID = productID;
	}

	
	public String getProductName() {
		return productName;
	}

	
	public void setProductName(String productName) {
		this.productName = productName;
	}


	
	public String getTotalCost() {
		return totalCost;
	}

	
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	
	public String getCompanyName() {
		return companyName;
	}

	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getvalue() {
		// TODO Auto-generated method stub
		return null;
	}

}
