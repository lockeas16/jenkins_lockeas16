package com.netec.pocs.standalone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BusinessTest {

	private Business business = new Business();

	@Test
	public void toUpperCaseString_OK() {
		final String INPUT = "Hello Test";
		final String OUTPUT = INPUT.toUpperCase();

		//
		String res = business.toUpperCaseString(INPUT);
		//
		assertEquals(OUTPUT, res);

	}

	@Test
	public void toUpperCaseString_NULL() {
		final String INPUT = null;
		//
		assertThrows(RuntimeException.class, () -> {
			business.toUpperCaseString(INPUT);
		});
	}

	@Test
	public void toUpperCaseString_ERROR() {
		final String INPUT = "ERROR";
		//
		assertThrows(RuntimeException.class, () -> {
			business.toUpperCaseString(INPUT);
		});
	}

}
