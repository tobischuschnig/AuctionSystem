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
public class UserTest {
	User test;
	@Before
	public void setUp() throws Exception {
		test=new User();
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
	public void testSetName() {
		test.setName("user");
	}

	@Test
	public void testGetAdresse() {
		test.setAdresse("adresse");
		assertEquals("adresse",test.getAdresse());
	}

	@Test
	public void testSetAdresse() {
		test.setAdresse("adresse");
	}

	@Test
	public void testGetUdpPort() {
		test.setUdpPort(123);
		assertEquals(123,test.getUdpPort());
	}

	@Test
	public void testSetUdpPort() {
		test.setUdpPort(123);
	}

	@Test
	public void testGetTcpPort() {
		test.setTcpPort(123);
		assertEquals(123,test.getTcpPort());
	}

	@Test
	public void testSetTcpPort() {
		test.setTcpPort(123);
	}

	@Test
	public void testIsActive() {
		assertTrue(test.isActive());
	}

	@Test
	public void testSetActive() {
		test.setActive(true);
	}

	@Test
	public void testGetMessages() {
		
	}

	@Test
	public void testSetMessages() {
		
	}

}
