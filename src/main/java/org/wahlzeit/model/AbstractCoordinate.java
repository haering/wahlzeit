package org.wahlzeit.model;

import com.google.appengine.api.memcache.InvalidValueException;

public abstract class AbstractCoordinate implements Coordinate {

	public AbstractCoordinate() {
		super();
	}

	public final double getDistance(Coordinate other) {
		if (other == null) {
			throw new NullPointerException();
		}
		if(!(other instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("Unknown type of Coordinate");
		}
		
		AbstractCoordinate otherAbstract = (AbstractCoordinate) other;
		assertClassInvariant();
		otherAbstract.assertClassInvariant();
		
		double distance =  doGetDistance(otherAbstract);
		
		if(distance < 0 ) {
			throw new InvalidValueException("distance has to be positive"); 
		}
		
		assertClassInvariant();
		otherAbstract.assertClassInvariant();
		
		return distance;
	}

	protected double doGetDistance(AbstractCoordinate other) {
		double xDist = this.getX() - other.getX();
		double yDist = this.getY() - other.getY();
		double zDist = this.getZ() - other.getZ();

		return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
	}
	
	@Override
	public boolean isEqual(Coordinate other) {
		assert other != null : new NullPointerException("other cannot be null");
		return this.getDistance(other) < 0.005;
	}
	
	protected abstract double getX();
	protected abstract double getY();
	protected abstract double getZ();
	protected abstract void assertClassInvariant();
}