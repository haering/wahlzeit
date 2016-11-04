package org.wahlzeit.model;

/**
 * Location on the earth
 *
 */
public class Location {
	
	public static final Location NO_LOCATION = new Location(null);

	private Coordinate coordinate;
	
	/**
	 * Default Constructor needed for Google App Engine
	 */
	public Location() {
		coordinate = null;
	}
	
	public Location(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}
