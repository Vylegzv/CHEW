package com.vanderbilt.isis.chew.vouchers;

import java.util.ArrayList;

public class T extends RegularVoucher {

	public T(Month month) {
		
		super(month);
		voucherCode = VoucherCode.T;
		voucherType = "1 year old Child";
	}

	@Override
	public ArrayList<String> getDescription() {
		
		ArrayList<String> description = new ArrayList<String>();
		
		description.add("One (1) gallon of whole milk");
		description.add("Choose one of these: One (1) quart buttermilk or One (1) 12 oz can evaporated milk");
		description.add("One (1) 64 oz container of juice");
		description.add("Thirty-six (36) oz of cereal (buy 11 oz or larger)");
		description.add("16 oz Whole Wheat Bread/Whole Grain Product");
		
		return description;
	}

	
}



















