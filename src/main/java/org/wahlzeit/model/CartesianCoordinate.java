package org.wahlzeit.model;


@PatternInstance(
		patternName = "Value Object",
		participants = "CartesianCoordinate")
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

	private CartesianCoordinate() {
		x = 0;
		y = 0;
		z = 0;
	}

	private CartesianCoordinate(Double x, Double y, Double z) {
		if( Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
			throw  new IllegalArgumentException("Component is NaN");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	
	

}
