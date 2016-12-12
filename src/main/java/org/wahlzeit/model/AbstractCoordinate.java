package org.wahlzeit.model;

import com.google.appengine.api.memcache.InvalidValueException;

public abstract class AbstractCoordinate implements Coordinate {

	public AbstractCoordinate() {
		super();
	}

	public final double getDistance(Coordinate other) throws CoordinateException {
		try {
			return doGetDistance(other);
		} catch (Throwable e) {
			throw new CoordinateException("getDistance Exception caught",e);
		}
		
	}

	protected double doGetDistance(Coordinate other) {
		
		if (other == null) {
			throw new NullPointerException();
		}
		if(!(other instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("Unknown type of Coordinate");
		}
		
		AbstractCoordinate otherAbstract = (AbstractCoordinate) other;
		assertClassInvariant();
		otherAbstract.assertClassInvariant();
		
		
		double xDist = this.getX() - otherAbstract.getX();
		double yDist = this.getY() - otherAbstract.getY();
		double zDist = this.getZ() - otherAbstract.getZ();

		double distance =  Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
		
		if(distance < 0 ) {
			throw new InvalidValueException("distance has to be positive"); 
		}
		
		
		
		assertClassInvariant();
		otherAbstract.assertClassInvariant();
		
		return distance;
	}
	
	@Override
	public boolean isEqual(Coordinate other) throws CoordinateException {
		if(other == null){
			throw new CoordinateException(new NullPointerException("other cannot be null"));
		}
		return this.getDistance(other) < 0.005;
	}
	
	protected abstract double getX();
	protected abstract double getY();
	protected abstract double getZ();
	protected abstract void assertClassInvariant();
}