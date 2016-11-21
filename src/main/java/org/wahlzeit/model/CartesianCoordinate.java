package org.wahlzeit.model;


public class CartesianCoordinate implements Coordinate {
	
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


	@Override
	public double getDistanceTo(Coordinate other) {
		if (other == null) {
			throw new NullPointerException();
		}
		if (!(other instanceof CartesianCoordinate)) {
			throw new IllegalArgumentException("Can only get distance of Coordinates of the same type.");
		}
		
		CartesianCoordinate ortherCart = (CartesianCoordinate) other;
		double xDist = this.getX() - ortherCart.getX();
		double yDist = this.getY() - ortherCart.getY();
		double zDist = this.getZ() - ortherCart.getZ();

		return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);

	}

}
