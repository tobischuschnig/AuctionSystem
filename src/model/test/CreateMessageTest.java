package model.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Client.*;
import connect.*;
import model.*;
import server.*;

/**
 * 
 * @author Kuanlun Huang
 * @version
 */
public class CreateMessageTest {
	CreateMessage test;
	@Before
	public void setUp() throws Exception {
		test=new CreateMessage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetName() {
		test.setName("user");
		assertEquals("user",test.getName());
	}

	@Test
	public void testGetDesc() {
		test.setDesc("bla");
		assertEquals("bla",test.getDesc());
	}

	@Test
	public void testSetDesc() {
		test.setDesc("bla");
	}

	@Test
	public void testGetDuration() {
		test.setDuration(10);
		assertEquals(10,test.getDuration());
	}

	@Test
	public void testSetDuration() {
		test.setDuration(10);
	}

	@Test
	public void testSetName() {
		test.setName("user");
	}

}
