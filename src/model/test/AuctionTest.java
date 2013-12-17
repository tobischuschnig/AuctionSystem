package model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

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
public class AuctionTest {
	Auction test;
	User tuser;
	@Before
	public void setUp() throws Exception {
		tuser=new User();
		tuser.setActive(false);
		tuser.setName("user");
		tuser.setMessages(new ArrayList<String>());
		tuser.setTcpPort(123);
		tuser.setUdpPort(123);
		
		test=new Auction(tuser,"bla",(long)100);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testIsActive() {
		assertFalse(test.isActive());
	}

	@Test
	public void testBid() {
		assertFalse(test.bid(tuser,10));
	}

	@Test
	public void testGetId() {
		test.setId(10);
		assertEquals(10,test.getId());
	}

	@Test
	public void testSetId() {
		test.setId(10);
	}

	@Test
	public void testGetHighestBid() {
		test.setHighestBid(10.01);
		test.getHighestBid();
		
	}

	@Test
	public void testSetHighestBid() {
		test.setHighestBid(10);
	}

	@Test
	public void testGetLastUser() {
		test.setLastUser(tuser);
		assertEquals(tuser,test.getLastUser());
		
	}

	@Test
	public void testSetLastUser() {
		test.setLastUser(tuser);
	}

	@Test
	public void testGetOwner() {
		test.setOwner(tuser);
		assertEquals(tuser,test.getOwner());
	}

	@Test
	public void testSetOwner() {
		test.setOwner(tuser);
	}

	@Test
	public void testGetDescription() {
		test.setDescription("bla");
		assertEquals("bla",test.getDescription());
	}

	@Test
	public void testSetDescription() {
		test.setDescription("bla");
	}

	@Test
	public void testGetDeadline() {
		Date a=new Date();
		test.setDeadline(a);
		assertEquals(a,test.getDeadline());
	}

	@Test
	public void testSetDeadline() {
		Date a=new Date();
		test.setDeadline(a);
	}

}
