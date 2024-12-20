package in.co.supply.chain.Bean;

public class UserBean extends BaseBean{
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	
	
	public String getLastName() {
		return lastName;
	}

	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	public String getEmail() {
		return email;
	}

	
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getPassword() {
		return password;
	}

	
	
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public String getPhoneNo() {
		return phoneNo;
	}

	
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	private String phoneNo;
	private String roleName;
	private long roleId;
	

	
	
	public long getRoleId() {
		return roleId;
	}

	
	
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	
	
	public String getFirstName() {
		return firstName;
	}

	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	
	public String getRoleName() {
		return roleName;
	}

	
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public String getkey() {
		return id + "";
	}

	
	
	public String getvalue() {
		return firstName + "" + lastName;
	}

}
