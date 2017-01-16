package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class GamePhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3051254939534110507L;
		
	private Game game;
	
	
	public GamePhoto() {
		super();
	}

	public GamePhoto(PhotoId myId) throws PhotoComponentException {
		super(myId);
	}
	
	public String getGameName() {
		return game.getName();
	}
	
	public String getGameGenre() {
		return game.getGenre();
	}
	

	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
}
