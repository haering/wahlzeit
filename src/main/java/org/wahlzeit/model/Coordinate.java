package org.wahlzeit.model;

import static java.lang.Math.*;


/**
 * Coordinate on the earth surface, which is assumed to be a sphere.
 *
 */
public class Coordinate {
	
	/**
	 * Earth radius of 6371 km.
	 */
	public static final float EARTH_RADIUS = 6371;

	/**
	 * Latitude given in a range between -90 and 90 degrees.
	 */
	private double latitude;
	
	/**
	 * Longitude given in a range between -180 and 180 degrees.
	 */
	private double longitude;
	
	
	/**
	 * Default Constructor needed for Google App Engine
	 */
	public Coordinate() {
		latitude = 0;
		longitude = 0;
	}

	public Coordinate(double latitude, double longitude) {
		if(latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude has to be between -90° and 90°.");
		}
		if(longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Latitude has to be between -180° and 180°.");
		}
		
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}
	
	
	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Calculates the distance between this and the other Coordinate given
	 * @return distance between this and other Coordinate in km
	 */
	public double getDistanceTo(Coordinate other) {
		if(other == null) {
			throw new NullPointerException();
		}
		
		double phi_1 = toRadians(latitude);
		double phi_2 = toRadians(other.latitude);
		double lambda_1 = toRadians(longitude);
		double lambda_2 = toRadians(other.longitude);
		
		double distanceAngle = acos(sin(phi_1) * sin(phi_2) +
				(cos(phi_1) * cos(phi_2) * cos(lambda_2 - lambda_1)));
		return EARTH_RADIUS * distanceAngle;
	}
	
	

}
