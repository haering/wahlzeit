package org.wahlzeit.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameType {
	
	private final String name;
	private final String genre;
	private final Date release;
	
	
	private GameType supertype;
	private List<GameType> subtypes;
	
	private GameType() {
		name = "";
		genre = "";
		release = null;
	}
	
	GameType(String name, String genre, Date release) {
		this.name = name;
		this.genre = genre;		
		this.release = release;
		
		subtypes = new LinkedList<>();
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getRelease() {
		return release;
	}
	
	Game createInstanceOf() {
		return new Game(this);
	}
	
	public GameType getSupertype() {
		return supertype;
	}
	
	public void addSubType(GameType subType) {
		if(subType == null) {
			throw new IllegalArgumentException("subtype cannot be null");
		}
		subtypes.add(subType);
		subType.setSupertype(this);
	}
	
	public void removeSubType(GameType subType) {
		if(subType == null) {
			throw new IllegalArgumentException("subtype cannot be null");
		}
		subtypes.remove(subType);
	}
	
	public boolean isSubType(GameType gameType) {
		if(this.equals(gameType)) {
			return true;
		}
		for (GameType subType : subtypes) {
			if(subType.isSubType(gameType)) {
				return true;
			}
		}
		return false;
	}
	
	public void setSupertype(GameType supertype) {
		this.supertype = supertype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameType other = (GameType) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
