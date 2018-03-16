package com.cryptoconverter.api.infrastructure;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * class used to contain information in the DB of authorized user able to access of the API
 * @author User
 *
 */
@Entity
@Table(schema="PUBLIC",name="authorized_user")
public class AuthorizedUser {
	private String userName;
	private String password;
	private String role;
	
	public AuthorizedUser() {
		super();
	}
	public AuthorizedUser(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	@Id
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="password")  // this have to be encrypted 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "AuthorizedUser [userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}
}
