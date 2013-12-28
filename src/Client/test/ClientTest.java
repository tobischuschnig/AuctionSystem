package Client.test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
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
	ArrayList<Auction> auctionen;

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
		// im falle das die verbindung zum Server funktioniert
		// soll nachgeschaut werden ob der letzer bieter auch der jenige ist

		// input ohne parameter
		test.testeInput("!bid");

		// falscher biet wert
		test.testeInput("!bid 1 1a0");

		// nicht exitierende id
		test.testeInput("!bid 10000 10");

		// korrektes bieten - login und erstellen der auktion nicht vergessen!
		test.testeInput("!bid 1 10");
	}

	@Test
	public void testCreate() {
		// im falle das die verbindung zum Server funktioniert
		// dann sollte die auctionliste geholt werden und
		// überprüft an der länge ob sich um 1 erhöht haben

		// input ohne parameter
		test.testeInput("!create");

		// falsche parameter
		System.out.println("sollte keinen auction erstellen:");
		test.testeInput("!create hallo 1");

		// korrektes erstellen der auktion
		test.testeInput("!create 1000000000000 hallo");
	}

	@Test
	public void testLogout() {
		// im falle das die verbindung zum Server funktioniert
		// überprüfen ob der client bei isActive auf false gesetzt ist
		test.testeInput("!logout");
	}

	@Test
	public void testRun() {
		// testet einen nicht existieren befehl
		test.testeInput("!blabla");
		// zeigen der liste der auctionen
		test.testeInput(" !list");
		// testet das beenden des client
		test.testeInput("!end");
	}

}
