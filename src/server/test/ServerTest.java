package server.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
public class ServerTest {
	Server test;
	ArrayList<User> user;

	@Before
	public void setUp() throws Exception {
		test = new Server();
		user = new ArrayList<User>();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBid() {
		BidMessage bmessage=new BidMessage();
		bmessage.setName("user");
		bmessage.setAmount(10);
		bmessage.setId(1);
		//nicht exsitierenden user(ausgeloggt)
		assertEquals("This User doesn't exists!",test.bid(bmessage));
		
		//exsitierenden user(eingeloggt)
		CreateMessage message=new CreateMessage();
		message.setName("user");
		message.setDesc("Blabla");
		message.setDuration(10);
		test.login("user");
		assertEquals("You have created a new auction!",test.create(message));
		
		test.login("user");
		test.bid(bmessage);
		
		 
	}

	@Test
	public void testCreate() {
		//nachricht erstellen für die auktion
		CreateMessage message=new CreateMessage();
		message.setName("user");
		message.setDesc("Blabla");
		message.setDuration(10);
		
		//nicht exstierender User(ausgeloggt)
		assertEquals("This User doesn't exists!",test.create(message));
		
		//exstierender User(eingeloggt)
		test.login("user");
		assertEquals("You have created a new auction!",test.create(message));
		
	}

	@Test
	public void testLogin() {
		//erstellen des user
		assertEquals("Succesfully loged in as: user",test.login("user"));
		
		
		//als eingeloggten user sich nochmal anzumelden
		assertEquals("You are allready loged in please log out first!",test.login("user"));
		
		//user login 
		ArrayList<User>usertest=test.getUser();
		usertest.get(0).setActive(false);
		test.setUser(usertest);
		assertEquals("Succesfully loged in as: user",test.login("user"));
	}

	@Test
	public void testLogout() {
		//als nicht exstieren oder abgemelden nutzer ausloggen
		assertEquals("Error you must log in first!",test.logout("hallo"));
		
		//normal ausloggen
		test.login("user");
		assertEquals("Succesfully loged out as: user",test.logout("user"));
		
	}

	@Test
	public void testList() {
		//keinen bieter
		CreateMessage message=new CreateMessage();
		message.setName("user");
		message.setDesc("Blabla");
		message.setDuration(10);
		test.login("user");
		assertEquals("You have created a new auction!",test.create(message));
		test.list();
		
		//letzer bieter
		User tuser=new User();
		tuser.setName("user");
		ArrayList<Auction>tauction=test.getAuction();
		tauction.get(0).setLastUser(tuser);
		test.setAuction(tauction);
		
		test.list();
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


	//noch nicht fertig
	@Test
	public void testSetUser() {
		// //////////////////////////
		// test user angelegt
		User utest = new User();
		utest.setName("User");
		utest.setAdresse("127.0.0.1");
		utest.setTcpPort(123);
		utest.setUdpPort(123);
		utest.setActive(false);
		utest.setMessages(new ArrayList<String>());
		user.add(utest);
		test.setUser(user);
	}
	@Test
	public void testGetUser() {
		User utest = new User();
		utest.setName("User");
		utest.setAdresse("127.0.0.1");
		utest.setTcpPort(123);
		utest.setUdpPort(123);
		utest.setActive(false);
		utest.setMessages(new ArrayList<String>());
		user.add(utest);
		test.setUser(user);
		assertEquals(user,test.getUser());
		
	}


	@Test
	public void testSetTodo() {
		test.setTodo("a");
	}

	@Test
	public void testGetTodo() {
		test.setTodo("a");
		assertEquals( "a",test.getTodo());
	}

}
