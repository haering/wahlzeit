package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class GamePhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051254939534110507L;
	
	private String gameName ="";
	
	private String gameGenre ="";
	
	public enum Platform {
		PC, XBOX_ONE, PS4, WII_U, WII, XBOX360, PS3, N64, NES, UNKNOWN
	}
	
	private Platform gamePlatform = Platform.UNKNOWN;
	
	public GamePhoto() {
		super();
	}

	public GamePhoto(PhotoId myId) throws PhotoComponentException {
		super(myId);
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameGenre() {
		return gameGenre;
	}

	public void setGameGenre(String gameGenre) {
		this.gameGenre = gameGenre;
	}

	public Platform getGamePlatform() {
		return gamePlatform;
	}

	public void setGamePlatform(Platform gamePlatform) {
		this.gamePlatform = gamePlatform;
	}
	
	
}
