package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class GameManagerTest {

	private static final String TEST_STRING = "test";
	private static final String TEST_STRING2 = "BAR";
	private static final Date TEST_DATE = new Date(1000);
	private GameManager gameManager;

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
		gameManager = GameManager.getInstance();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testSingleton() {
		assertNotNull(gameManager);
		assertEquals(gameManager, GameManager.getInstance());
	}

	@Test
	public void testCreateGameType() {
		GameType gameType = gameManager.getGameType(TEST_STRING, TEST_STRING, TEST_DATE);
		assertNotNull(gameType);
		GameType gameType2 = gameManager.getGameType(TEST_STRING, TEST_STRING, TEST_DATE);
		assertEquals(gameType, gameType2);
	}

	@Test
	public void testSubType() {
		GameType gameType = gameManager.getGameType(TEST_STRING, TEST_STRING, TEST_DATE);
		GameType subType = gameManager.getGameType(TEST_STRING2, TEST_STRING2, TEST_DATE);
		gameType.addSubType(subType);
		assertEquals(gameType, subType.getSupertype());
		assertTrue(gameType.isSubType(subType));
	}

	@Test
	public void testCreateGame() {
		GameType gameType = gameManager.getGameType(TEST_STRING, TEST_STRING, TEST_DATE);
		Game game = gameType.createInstanceOf();
		assertEquals(game.getName(), TEST_STRING);
		assertEquals(game.getGenre(), TEST_STRING);
		assertEquals(game.getReleaseDate(), TEST_DATE);
	}
	
	@Test
	public void testGetAllGames() {
		GameType gameType = gameManager.getGameType(TEST_STRING, TEST_STRING, TEST_DATE);
		List<Game> games = gameManager.getAllGamesByType(gameType);
		assertEquals(1, games.size());
	}
	

}
