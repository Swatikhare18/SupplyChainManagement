package in.co.supply.chain.Bean;

public class RequirmentBean extends BaseBean{

	private String productName;
	private String productID;
	private String quantity;
	private String location;
	private String status;
	private long userId;
	private String require;
	
	
	
	
	public String getRequire() {
		return require;
	}

	
	public void setRequire(String require) {
		this.require = require;
	}

	
	public long getUserId() {
		return userId;
	}

	
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	
	public String getStatus() {
		return status;
	}

	
	
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getProductID() {
		return productID;
	}

	
	
	public void setProductID(String productID) {
		this.productID = productID;
	}

	
	
	public String getQuantity() {
		return quantity;
	}

	
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	
	
	public String getLocation() {
		return location;
	}

	
	
	public void setLocation(String location) {
		this.location = location;
	}

	
	
	public String getDiscription() {
		return discription;
	}

	
	
	public void setDiscription(String discription) {
		this.discription = discription;
	}

	
	private String discription;
	
	
	
	
	public String getProductName() {
		return productName;
	}

	
	
	public void setProductName(String productName) {
		this.productName = productName;
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
