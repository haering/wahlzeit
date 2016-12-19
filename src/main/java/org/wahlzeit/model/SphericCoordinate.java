package org.wahlzeit.model;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * Coordinate on the earth surface, which is assumed to be a sphere.
 *
 */
public class SphericCoordinate extends AbstractCoordinate {

	/**
	 * Earth radius of 6371 km.
	 */
	public static final double EARTH_RADIUS = 6371;

	/**
	 * Latitude given in a range between -90 and 90 degrees.
	 */
	private final double latitude;

	/**
	 * Longitude given in a range between -180 and 180 degrees.
	 */
	private final double longitude;

	private final double radius;

	/**
	 * Default Constructor needed for Google App Engine
	 */
	private SphericCoordinate() {
		latitude = 0;
		longitude = 0;
		radius = 0;
	}

	/**
	 * Create a SphericCoordinate the Radius is implicitly assumed to be Earth
	 * Radius.
	 * 
	 * @param latitude
	 * @param longitude
	 */
	private SphericCoordinate(Double latitude, Double longitude) {
		this(latitude, longitude, EARTH_RADIUS);
	}

	private SphericCoordinate(Double latitude, Double longitude, Double radius) {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalArgumentException("Latitude has to be between -90° and 90°.");
		}
		if (longitude > 180 || longitude < -180) {
			throw new IllegalArgumentException("Longitude has to be between -180° and 180°.");
		}
		if (radius < 0) {
			throw new IllegalArgumentException("Radius has to be > 0.");
		}

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;

		assertClassInvariant();
	}

	/**
	 * @methodtype get
	 */
	public double getLatitude() {
		assertClassInvariant();
		return latitude;
	}

	/**
	 * @methodtype get
	 */
	public double getLongitude() {
		assertClassInvariant();
		return longitude;
	}

	/**
	 * @methodtype get
	 */
	public double getRadius() {
		assertClassInvariant();
		return radius;
	}

	@Override
	protected double getX() {
		assertClassInvariant();
		return radius * cos(toRadians(latitude)) * cos(toRadians(longitude));
	}

	@Override
	protected double getY() {
		assertClassInvariant();
		return radius * cos(toRadians(latitude)) * sin(toRadians(longitude));
	}

	@Override
	protected double getZ() {
		assertClassInvariant();
		return radius * sin(toRadians(latitude));
	}

	protected void assertClassInvariant() {
		if (latitude > 90 || latitude < -90) {
			throw new IllegalStateException("Latitude has to be between -90Â° and 90Â°.");
		}
		if (longitude > 180 || longitude < -180) {
			throw new IllegalStateException("Longitude has to be between -180Â° and 180Â°.");
		}
		if (radius < 0) {
			throw new IllegalStateException("Radius has to be > 0.");
		}
	}

	@Override
	public String toString() {
		return buildString(latitude, longitude, radius);
	}

	private String buildString(double latitude, double longitude, double radius) {
		return latitude + "," + longitude + "," + radius;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

}
