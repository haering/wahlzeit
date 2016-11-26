package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

	public AbstractCoordinate() {
		super();
	}

	public final double getDistanceTo(Coordinate other) {
		if (other == null) {
			throw new NullPointerException();
		}
		if(!(other instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("Unknown type of Coordinate");
		}
		AbstractCoordinate otherAbstract = (AbstractCoordinate) other;
		return doGetDistanceTo(otherAbstract);
	}

	protected double doGetDistanceTo(AbstractCoordinate other) {
		double xDist = this.getX() - other.getX();
		double yDist = this.getY() - other.getY();
		double zDist = this.getZ() - other.getZ();

		return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
	}
	
	@Override
	public boolean isEqual(Coordinate other) {
		return this.getDistanceTo(other) < 0.005;
	}
	
	protected abstract double getX();
	protected abstract double getY();
	protected abstract double getZ();
}