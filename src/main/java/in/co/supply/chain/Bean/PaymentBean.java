package in.co.supply.chain.Bean;

public class PaymentBean extends BaseBean{

	
	private String Amount;
	private String CardNo;
	private String Cvv;
	private String CardExpairy;
	private long userId;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public long getUserId() {
		return userId;
	}

	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	
	public String getAmount() {
		return Amount;
	}

	
	public void setAmount(String amount) {
		Amount = amount;
	}

	
	public String getCardNo() {
		return CardNo;
	}

	
	
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	
	public String getCvv() {
		return Cvv;
	}

	
	
	public void setCvv(String cvv) {
		Cvv = cvv;
	}

	
	
	public String getCardExpairy() {
		return CardExpairy;
	}

	
	
	public void setCardExpairy(String cardExpairy) {
		CardExpairy = cardExpairy;
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
