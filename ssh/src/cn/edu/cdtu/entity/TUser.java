package cn.edu.cdtu.entity;
// default package
// Generated 2015-6-28 18:16:30 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Entity;

/**
 * TUser generated by hbm2java
 */
public class TUser {

	private String userId;
	private String userName;
	private String userPassword;
	private String inputClass;//��֤��
	// �޸��������
	private String newpass;//������
	private String reloginpass;//ȷ������
	public String getReloginpass() {
		return reloginpass;
	}

	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}

	private Set<TOrder> TOrders = new HashSet<TOrder>(0);
	private Set<TOrder> TCartitems = new HashSet<TOrder>(0);
	public String getInputClass() {
		return inputClass;
	}

	public void setInputClass(String inputClass) {
		this.inputClass = inputClass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public TUser() {
	}

	public TUser(String userId) {
		this.userId = userId;
	}

	public TUser(String userId, String userName, String userPassword,
			Set TOrders, Set TCartitems) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.TOrders = TOrders;
		this.TCartitems = TCartitems;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public Set getTOrders() {
		return this.TOrders;
	}

	public void setTOrders(Set TOrders) {
		this.TOrders = TOrders;
	}

	public Set getTCartitems() {
		return this.TCartitems;
	}

	public void setTCartitems(Set TCartitems) {
		this.TCartitems = TCartitems;
	}

}