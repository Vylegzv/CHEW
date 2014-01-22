package com.vanderbilt.isis.chew.users;

import java.util.ArrayList;

import com.vanderbilt.isis.chew.vouchers.CashVoucher;
import com.vanderbilt.isis.chew.vouchers.RegularVoucher;

public abstract class User {

	private String userName;
	private String userEthnicity;

	/** Composition over Inheritance */
	RegularVoucher regularVoucher;
	CashVoucher cashVoucher;

	/** Constructors **/
	public User() {
	};

	public User(String name, String ethnicity) {

		this.userName = name;
		this.userEthnicity = ethnicity;
	}
	
	/** Setter and getter methods **/
	public void setName(String name){
		
		this.userName = name;
	}
	
	public void setEthnicity(String ethnicity){
		
		this.userEthnicity = ethnicity;
	}
	
	public String getName(){
		
		return this.userName;
	}
	
	public String getEthnicity(){
		
		return this.userEthnicity;
	}

	/** Get Vouchers **/
	public void setRegularVoucher(RegularVoucher regularVoucher) {

		this.regularVoucher = regularVoucher;
	}

	public void setCashVoucher(CashVoucher cashVoucher) {

		this.cashVoucher = cashVoucher;
	}
	
	/** Cash Voucher methods **/
	public double getCashValue(){
		
		return cashVoucher.getAmountAllowed();
	}
	
	public String getCashVoucherDescr(){
		
		return cashVoucher.getDescription();
	}
	
	/** Regular Voucher methods **/
	// to display in a listview
	public ArrayList<String> getRegularVoucherDescr(){
		
		return null;
	}

}
