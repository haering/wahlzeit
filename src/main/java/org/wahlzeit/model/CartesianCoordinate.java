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
		z = 0;
	}

	public CartesianCoordinate(double x, double y, double z)  throws CoordinateException {
		if( Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
			throw  new CoordinateException(new IllegalArgumentException("Component is NaN"));
		}

		this.x = x;
		this.y = y;
		this.z = z;

		
		assertClassInvariant();
	}

	/**
	 * @methodtype get
	 */
	public double getX() {
		assertClassInvariant();
		return x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		assertClassInvariant();
		return y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		assertClassInvariant();
		return z;
	}
	
	protected void assertClassInvariant() {
		if( Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
			throw new IllegalStateException("Component is NaN");
		}
	}

}
