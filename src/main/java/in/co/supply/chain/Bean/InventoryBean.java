package in.co.supply.chain.Bean;



public class InventoryBean extends BaseBean{
	
	
	private String productName;
	private String productCost;
	private String  quantity;
	private String companyName;
    private String productID;
	

	
    
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

	
    public String getProductCost() {
		return productCost;
	}

	
    public void setProductCost(String productCost) {
		this.productCost = productCost;
	}

	
    public String getQuantity() {
		return quantity;
	}

	
    public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	
    public String getCompanyName() {
		return companyName;
	}

	
    public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
    @Override
	public String getkey() {
		return id+"";
	}

	
    @Override
	public String getvalue() {
		return productName;
	}

}
