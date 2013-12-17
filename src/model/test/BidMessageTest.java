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
public class BidMessageTest {
	BidMessage test;
	@Before
	public void setUp() throws Exception {
		test=new BidMessage();
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
	public void testGetId() {
		test.setId(1);
		assertEquals(1,test.getId());
	}

	@Test
	public void testSetId() {
		test.setId(1);
	}

	@Test
	public void testGetAmount() {
		test.setAmount(1.1);
		test.getAmount();
	}

	@Test
	public void testSetAmount() {
		test.setAmount(1.1);
	}

	@Test
	public void testSetName() {
		test.setName("user");
	}

}
