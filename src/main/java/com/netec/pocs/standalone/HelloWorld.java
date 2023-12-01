package com.netec.pocs.standalone;

public class HelloWorld {

	private Business business = new Business();

	public String sayHelloTo(final String... name) {
		String res = "WORLD";
		if (name != null && name.length > 0) {
			res = business.toUpperCaseString(name[0]);
		}
		return "Hello " + res;
	}

}
