package info;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageTest {
	@Test
	//testing with a random string
	public void twoParamInstantiation1() { 
		Object i = new Object();
		Message msg = new Message("Hello",i);
		assertEquals("Hello",msg.header);
		assertEquals("2 param instantiation error",i,msg.body);
	}
	@Test
	// testing with the second parameter(which is an object param) being null
	public void twoParamInstantiation2() { 
		Message msg = new Message("Hello",null);
		assertEquals("2 param instantiation error: header","Hello",msg.header);
		assertEquals("2 param instantiation error: body",null,msg.body);
	}
	@Test
	//testing with the second parameter(which is an object param) being null and string is empty
	public void twoParamInstantiation3() { 
		Message msg = new Message("",null);
		assertEquals("2 param instantiation error: header","",msg.header);
		assertEquals("2 param instantiation error: body",null,msg.body);
	}
	@Test
	//testing with another random string
	public void twoParamInstantiation4() { 
		Object someObject = new Object();
		Message msg = new Message("Leo Messi",someObject);
		assertEquals("2 param instantiation error: header","Leo Messi",msg.header);
		assertEquals("2 param instantiation error: body",someObject,msg.body);
	}
	@Test
	// one parameter instantiation
	public void oneParamInstantiation1() { 
		Message msg = new Message("Hello");
		assertEquals("1 param instantiation error: header","Hello",msg.header);
		assertEquals("1 param instantiation error: body",null,msg.body);
	}
	@Test
	//one parameter instantiation with string empty
	public void oneParamInstantiation2() { 
		Message msg = new Message("");
		assertEquals("1 param instantiation error: header","",msg.header);
		assertEquals("1 param instantiation error: body",null,msg.body);
	}
}

