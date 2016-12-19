package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.AbstractCoordinate.CoordinateFactory;

public class SphericCoordinateTest {

	private static final double DELTA = 0.001;
	private static final double EARTH_PERIMETER = SphericCoordinate.EARTH_RADIUS * 2 * Math.PI;
	private Coordinate north;
	private AbstractCoordinate south;
	private SphericCoordinate zero;
	private AbstractCoordinate mostWest;
	private SphericCoordinate mostEast;

	@Before
	public void setup()  {
		
		north = CoordinateFactory.createSphericCoordinate(90, 90);
		south = CoordinateFactory.createSphericCoordinate(-90, -90);
		zero = CoordinateFactory.createSphericCoordinate(0, 0);
		mostWest = CoordinateFactory.createSphericCoordinate(0, -180);
		mostEast = CoordinateFactory.createSphericCoordinate(0, 180);
	}


	@Test
	public void testOverlapDistance() {
		Assert.assertEquals(0, mostWest.getDistance(mostEast), DELTA);
	}
	

	@Test
	public void testSamePointDistance() {

		Assert.assertEquals(0, zero.getDistance(zero), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments1()  {
		CoordinateFactory.createSphericCoordinate(-91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments2() {
		CoordinateFactory.createSphericCoordinate(91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments3()  {
		CoordinateFactory.createSphericCoordinate(0, 181);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments4()  {
		CoordinateFactory.createSphericCoordinate(0, -181);
	}
	
	@Test
	public void testDiffrentRadiusLength() {
		Assert.assertEquals(6671.0,north.getDistance(CoordinateFactory.createSphericCoordinate(-90, 0,300)), DELTA);
	}
	
	@Test
	public void testIsEqual() {
		Assert.assertTrue(north.isEqual(north));
		Assert.assertFalse(north.isEqual(south));
		Assert.assertTrue(north.isEqual(CoordinateFactory.createCartesianCoordinate(0, 0, SphericCoordinate.EARTH_RADIUS)));
	}


}
