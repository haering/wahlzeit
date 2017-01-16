/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.wahlzeit.services.ObjectManager;

public class GameManager extends ObjectManager {

	
	private static GameManager instance;
	
	private Map<GameType, GameType> gameTypeMap;
	
	private List<Game> games;
	
	public static GameManager getInstance() {
		if(instance == null) {
			synchronized (GameManager.class) { // Only synchronize if a new Instance has to be initiated
				if(instance == null) { 
					instance = new GameManager();
				}
			}
		}
		return instance;
	}
	
	private GameManager() {
		gameTypeMap = new HashMap<>();
		games = new ArrayList<>();
	}

	public GameType getGameType(String name, String genre, Date releaseDate) {
		GameType newGame = new GameType(name, genre, releaseDate);
		GameType type = gameTypeMap.get(newGame);
		if(type == null) {
			type = newGame;
			synchronized (this) { // Only synchronize if a new Instance has to be initiated
				if(!gameTypeMap.containsKey(newGame))
					gameTypeMap.put(newGame, newGame);
			}
		}
		return type;
	}
	
	public Game createGame(String name, String genre, Date releaseDate) {
		return createGame(getGameType(name, genre, releaseDate));
	}

	public Game createGame(GameType gameType) {
		Game game = gameType.createInstanceOf();
		games.add(game);
		return game;
	}
	
	public List<Game> getAllGamesByType(GameType gameType) {
		return games.stream().filter(game -> game.equals(gameType)).collect(Collectors.toList());
	}
}