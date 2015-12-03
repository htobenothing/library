package user.dto;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
private int userID;
private String userName;
private String password;
private String userStatus;
private Date dateOfBirth;
private String address;
private String email;
private String phone;
private String role;
private Date createdDate;

public User(int userID, String userName, String password, String userStatus, Date dateOfBirth, String address,
		String email, String phone, String role, Date createdDate) {
	super();
	this.userID = userID;
	this.userName = userName;
	this.password = password;
	this.userStatus = userStatus;
	this.dateOfBirth = dateOfBirth;
	this.address = address;
	this.email = email;
	this.phone = phone;
	this.role = role;
	this.createdDate = createdDate;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUserStatus() {
	return userStatus;
}
public void setUserStatus(String userStatus) {
	this.userStatus = userStatus;
}

public User() {
	super();
}
public int getUserID() {
	return userID;
}
public void setUserID(int memberID) {
	this.userID = memberID;
}
public String getUserName() {
	return userName;
}
public void setUserName(String name) {
	this.userName = name;
}
public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateofbirth) {
	this.dateOfBirth = dateofbirth;
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
public String getPhone() {
	return phone;
}
public void setPhone(String contactnumber) {
	this.phone = contactnumber;
}
public String getRole() {
	return role;
}
public void setRole(String faculty) {
	this.role = faculty;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdate) {
	this.createdDate = createdate;
}
}
