package server.test;

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
public class AuctionHandlerTest {
	AuctionHandler test;
	@Before
	public void setUp() throws Exception {
		test=new AuctionHandler();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

}
