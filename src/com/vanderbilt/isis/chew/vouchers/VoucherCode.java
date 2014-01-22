package com.vanderbilt.isis.chew.vouchers;

public enum VoucherCode {
	
	CV6("CVV6"), CV10("CVV10"), A("A"), A2("A2"), B("B"), B2("B2"),
	L("L"), L2("L2"), G("G"), G2("G2"), T2("T2"), T("T"), K("K"),
	K2("K2"), E("E"), E2("E2"), P("P"), P2("P2");
	
	private String code;
	
	private VoucherCode(String code){
		
		this.code = code;
	}
	
	public String getCode(){
		
		return this.code;
	}
	
	public String toString(){
		
		return this.code;
	}

}
