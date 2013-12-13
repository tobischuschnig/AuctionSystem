package Client.test;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
	Client test;
	@Before
	public void setUp() throws Exception {
		test=new Client();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		test.login();
	}

	@Test
	public void testList() {
		test.list();
	}

	@Test
	public void testBid() {
		test.bid(1, 1.1);
	}

	@Test
	public void testCreate() {
		test.create((long)1, "a");
	}

	@Test
	public void testLogout() {
		test.logout();
	}

	@Test
	public void testRun() {
		//test.run();
	}

}
