package com.netec.pocs.standalone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

	private HelloWorld hm = new HelloWorld();

	@Test
	public void sayHelloTo() {
		final String[] INPUT = {"World"};
		final String OUTPUT = "Hello " + INPUT[0].toUpperCase();
		//
		String res = hm.sayHelloTo(INPUT);
		//
		assertEquals(OUTPUT, res);

	}

	@Test
	public void sayHelloTo_NULL() {
		final String[] INPUT = {};
		final String OUTPUT = "Hello WORLD";
		//
		String res = hm.sayHelloTo(INPUT);
		//
		assertEquals(OUTPUT, res);
	}
}
