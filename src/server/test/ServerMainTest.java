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
public class ServerMainTest {
	ServerMain test;
	@Before
	public void setUp() throws Exception {
		String[] args=new String[1];
		args[0]="";
		ServerMain.main(args);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() {
		
	}

}
