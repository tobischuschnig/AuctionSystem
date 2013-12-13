package connect.test;

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
public class ReceiveConnectionTest {
	ReceiveConnection test;
	@Before
	public void setUp() throws Exception {
		//port herrausfinden und den server
		test=new ReceiveConnection(1234,new Server());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReceiveConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

}
