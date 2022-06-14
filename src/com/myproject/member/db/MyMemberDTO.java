package com.myproject.member.db;

import java.sql.Timestamp;

public class MyMemberDTO {
	
	private String id;
	private String pass;
	private String email;
	private String zipcode;
	private String address;
	private String phone;
	private Timestamp date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
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
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "MyMemberDTO [id=" + id + ", pass=" + pass + ", email=" + email + ", zipcode=" + zipcode + ", address="
				+ address + ", phone=" + phone + ", date=" + date + "]";
	}
	
	
}
