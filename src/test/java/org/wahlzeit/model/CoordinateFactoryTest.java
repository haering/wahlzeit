package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.wahlzeit.model.AbstractCoordinate.CoordinateFactory;

import com.sun.org.apache.bcel.internal.generic.ClassGenException;

public class CoordinateFactoryTest {

	private static final double DELTA = 0.001;

	@Test
	public void generateSphericCoordinate() {
		SphericCoordinate coord = CoordinateFactory.createSphericCoordinate(10, 10, 10);
		assertEquals(10, coord.getLatitude(), DELTA);
		assertEquals(10, coord.getLongitude(), DELTA);
		assertEquals(10, coord.getRadius(), DELTA);
	}

	@Test
	public void testSame() {
		SphericCoordinate coord1 = CoordinateFactory.createSphericCoordinate(12, 12, 12);
		SphericCoordinate coord2 = CoordinateFactory.createSphericCoordinate(12, 12, 12);
		Assert.assertSame(coord1, coord2);
	}

	@Test
	public void testNotEquals() {
		SphericCoordinate coord1 = CoordinateFactory.createSphericCoordinate(12, 12, 12);
		CartesianCoordinate coord2 = CoordinateFactory.createCartesianCoordinate(12, 12, 12);
		Assert.assertNotEquals(coord1, coord2);
		Assert.assertNotSame(coord1, coord2);
	}

	@Test(expected = ClassGenException.class)
	public void testWrongParamCount() {
		CoordinateFactory.createCoordinate(CartesianCoordinate.class, 0);
	}

}
