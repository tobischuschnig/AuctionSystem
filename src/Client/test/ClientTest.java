package Client.test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Client.*;
import connect.*;
import model.*;
import server.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
	Client test;

	@Before
	public void setUp() throws Exception {
		test = new Client();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {
		test.testeInput("!login");
		
		test.testeInput("!login user");
	}

	@Test
	public void testList() {
		test.testeInput("!list");
	}

	@Test
	public void testBid() {
		test.testeInput("!bid");

		test.testeInput("!bid 1 10");

		test.testeInput("!bid 1 1a0");
	}

	@Test
	public void testCreate() {
		test.testeInput("!create");
		
		test.testeInput("!create 1a hallo");
		
		test.testeInput("!create 1000000000000 hallo");
	}

	@Test
	public void testLogout() {
		test.testeInput("!logout");
	}

	@Test
	public void testRun() {

		test.testeInput(" !list");
		test.testeInput("!end");
		test.testeInput("!blabla");

	}

}
