package org.wahlzeit.model;

import static java.lang.Math.*;

/**
 * Coordinate on the earth surface, which is assumed to be a sphere.
 *
 */
public class SphericCoordinate implements Coordinate {

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

	private double radius;

	/**
	 * Default Constructor needed for Google App Engine
	 */
	public SphericCoordinate() {
		latitude = 0;
		longitude = 0;
	}

	/**
	 * Create a SphericCoordinate the Radius is implicitly assumed to be Earth
	 * Radius.
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public SphericCoordinate(double latitude, double longitude) {
		this(latitude, longitude, EARTH_RADIUS);
	}

	public SphericCoordinate(double latitude, double longitude, double radius) {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude has to be between -90° and 90°.");
		}
		if (longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Latitude has to be between -180° and 180°.");
		}

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
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
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wahlzeit.model.Coordinate#getDistanceTo(org.wahlzeit.model.
	 * SphericCoordinate)
	 */
	@Override
	public double getDistanceTo(Coordinate other) {
		if (other == null) {
			throw new NullPointerException();
		}
		if (!(other instanceof SphericCoordinate)) {
			throw new IllegalArgumentException("Can only get distance of Coordinates of the same type.");
		}
		SphericCoordinate otherCoordinate = (SphericCoordinate) other;

		if(radius != otherCoordinate.getRadius()) {
			throw new IllegalArgumentException("Radius of the Coordinates has to be the same.");
		}
		
		double phi_1 = toRadians(latitude);
		double phi_2 = toRadians(otherCoordinate.latitude);
		double lambda_1 = toRadians(longitude);
		double lambda_2 = toRadians(otherCoordinate.longitude);

		double distanceAngle = acos(sin(phi_1) * sin(phi_2) + (cos(phi_1) * cos(phi_2) * cos(lambda_2 - lambda_1)));
		return radius * distanceAngle;

	}

}
