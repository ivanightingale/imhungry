package info;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageTest {
	@Test
	public void twoParamInstantiation1() {
		Object i = new Object();
		Message msg = new Message("Hello",i);
		assertEquals("Hello",msg.header);
		assertEquals("2 param instantiation error",i,msg.body);
	}
	@Test
	public void twoParamInstantiation2() {
		Message msg = new Message("Hello",null);
		assertEquals("2 param instantiation error: header","Hello",msg.header);
		assertEquals("2 param instantiation error: body",null,msg.body);
	}
	@Test
	public void twoParamInstantiation3() {
		Message msg = new Message("",null);
		assertEquals("2 param instantiation error: header","",msg.header);
		assertEquals("2 param instantiation error: body",null,msg.body);
	}
	@Test
	public void twoParamInstantiation4() {
		Object someObject = new Object();
		Message msg = new Message("Leo Messi",someObject);
		assertEquals("2 param instantiation error: header","Leo Messi",msg.header);
		assertEquals("2 param instantiation error: body",someObject,msg.body);
	}
	@Test
	public void oneParamInstantiation1() {
		Message msg = new Message("Hello");
		assertEquals("1 param instantiation error: header","Hello",msg.header);
		assertEquals("1 param instantiation error: body",null,msg.body);
	}
	@Test
	public void oneParamInstantiation2() {
		Message msg = new Message("Hello");
		assertEquals("1 param instantiation error: header","Hello",msg.header);
		assertEquals("1 param instantiation error: body",null,msg.body);
	}
}

