package com.vanderbilt.isis.chew.vouchers;

import java.util.ArrayList;

public class T2 extends RegularVoucher{

	public T2(Month month) {
		
		super(month);
		voucherCode = VoucherCode.T2;
		voucherType = "1 year old Chile";
	}

	@Override
	public ArrayList<String> getDescription() {
		
		ArrayList<String> description = new ArrayList<String>();
		
		description.add("Two (2) gallons of whole milk");
		description.add("Sixteen (16) oz store brand cheese (8 or 16 oz pkg)");
		description.add("One (1) dozen large white grade A eggs");
		description.add("One (1) 64 oz container of juice");
		description.add("Sixteen (16) oz dried beans/peas or Four (4) cans 15-16 oz canned beans");
		description.add("16 oz Whole Wheat Bread/Whole Grain Product");
		
		return description;
	}

}
























