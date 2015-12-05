package dto;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
	private String userId;
	private String userName;
	private String password;
	private String role;
	private String userStatus;
	private Date dateOfBirth;
	private String address;
	private String email;
	private String phoneNumber;
	private Date createDate;
	private int onloanNumber;

	
	public User(String userId, String userName, String password, String role, String userStatus, Date dateOfBirth,
			String address, String email, String phoneNumber, Date createDate, int onloanNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.userStatus = userStatus;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createDate = createDate;
		this.onloanNumber = onloanNumber;
	}

	public User() {
		super();
		
	}
	

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getOnloanNumber() {
		return onloanNumber;
	}
	public void setOnloanNumber(int onloanNumber) {
		this.onloanNumber = onloanNumber;
	}

	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", userStatus=" + userStatus + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", createDate=" + createDate + ", onloanNumber="
				+ onloanNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public User(String userId, String userName, String password, String role, String userStatus, String address,
			String email, String phoneNumber, Date createDate, int onloanNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.userStatus = userStatus;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createDate = createDate;
		this.onloanNumber = onloanNumber;
	}

	public User(String userId, String userName, String password, String role, String userStatus, 
			String address, String email, String phoneNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.userStatus = userStatus;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	

}
