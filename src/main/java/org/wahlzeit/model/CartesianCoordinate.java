package org.wahlzeit.model;


public class CartesianCoordinate extends AbstractCoordinate {
	
	/**
	 * X coordinate
	 */
	private final double x;
	/**
	 * Y coordinate
	 */
	private final double y;
	/**
	 * Z coordinate
	 */
	private final double z;
	
	public CartesianCoordinate() {
		x = 0;
		y = 0;
		z =0;
	}

	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

}
