package org.wahlzeit.model;

/**
 * Interface of a Coordinate
 *
 */
public interface Coordinate {

	/**
	 * Calculates the distance between this and the other Coordinate given
	 * @return distance between this and other Coordinate in km
	 */
	double getDistanceTo(Coordinate other);

}