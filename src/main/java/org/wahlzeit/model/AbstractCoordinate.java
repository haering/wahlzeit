package org.wahlzeit.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

@PatternInstance(
		patternName="Template Method",
		participants = {"AbstractCoordinate", "All Subclasses of AbstractCoordinate"}
		)
public abstract class AbstractCoordinate implements Coordinate {

	public AbstractCoordinate() {
		super();
	}

	public final double getDistance(Coordinate other) {
		if (other == null) {
			throw new NullPointerException();
		}
		if (!(other instanceof AbstractCoordinate)) {
			throw new IllegalArgumentException("Unknown type of Coordinate");
		}

		return doGetDistance(other);
	}

	protected double doGetDistance(Coordinate other) {

		AbstractCoordinate otherAbstract = (AbstractCoordinate) other;
		assertClassInvariant();
		otherAbstract.assertClassInvariant();

		double xDist = this.getX() - otherAbstract.getX();
		double yDist = this.getY() - otherAbstract.getY();
		double zDist = this.getZ() - otherAbstract.getZ();

		double distance = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);

		assertClassInvariant();
		otherAbstract.assertClassInvariant();

		return distance;
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (other == null) {
			throw new NullPointerException("other cannot be null");
		}
		return this.getDistance(other) < 0.005;
	}

	protected abstract double getX();

	protected abstract double getY();

	protected abstract double getZ();

	protected abstract void assertClassInvariant();

	@PatternInstance(
			patternName="Abstract Factory",
			participants = { "CoordinateFactory", "SphericCoordinate", "AbstractCoordinate", "CartessianCoordinate"}
			)
	public static class CoordinateFactory {

		private final static HashMap<String, Coordinate> createdCoordinates = new HashMap<>();

		public static Coordinate createCoordinate(Class<? extends Coordinate> coordinateclass, Object... parameters) {

			StringBuilder sb = new StringBuilder(coordinateclass.getName());
			for (Object object : parameters) {
				sb.append(object.toString());
				sb.append(';');
			}
			String coordString = sb.toString();
			Coordinate coordinate = createdCoordinates.get(coordString);
			if (coordinate == null) {
				synchronized (createdCoordinates) {
					coordinate = createdCoordinates.get(coordString);
					if (coordinate == null) {
						for (Constructor<?> constructor : coordinateclass.getDeclaredConstructors()) {
							if (parameters.length != constructor.getParameterCount()) {
								continue;
							}
							for (int i = 0; i < parameters.length; i++) {
								Class<?> parameterType = constructor.getParameterTypes()[i];
								if (!parameterType.isAssignableFrom(parameters[i].getClass())) {
									continue;
								}
								try {
									boolean changedAccessible = false;
									if (!constructor.isAccessible()) {
										constructor.setAccessible(true);
										changedAccessible = true;
									}
									coordinate = (Coordinate) constructor.newInstance(parameters);
									if (changedAccessible)
										constructor.setAccessible(false);

									createdCoordinates.put(coordString, coordinate);
									break;
								} catch (InstantiationException | IllegalAccessException e) {
									throw new RuntimeException("Could not instantiate Object", e);
								} catch (InvocationTargetException e) {
									if(e.getTargetException() instanceof RuntimeException) {
										throw (RuntimeException) e.getTargetException();
									}
									throw new RuntimeException(e.getTargetException());
									
								}
							}
						}
						if(coordinate == null) {
							throw new RuntimeException("No Constructor matching the given Signature");
						}
					}
				}
			}
			return coordinate;
		}

		public static SphericCoordinate createSphericCoordinate(double latitude, double longitude, double radius) {
			return (SphericCoordinate) createCoordinate(SphericCoordinate.class, latitude, longitude, radius);
		}

		public static CartesianCoordinate createCartesianCoordinate(double x, double y, double z) {
			return (CartesianCoordinate) createCoordinate(CartesianCoordinate.class, x, y, z);
		}

		public static Coordinate createCoordinate(double x, double y, double z) {
			return createCoordinate(CartesianCoordinate.class, x, y, z);
		}

		public static SphericCoordinate createSphericCoordinate(double latitude, double longitude) {
			return (SphericCoordinate) createCoordinate(SphericCoordinate.class, latitude, longitude);
			
		}

	}

}