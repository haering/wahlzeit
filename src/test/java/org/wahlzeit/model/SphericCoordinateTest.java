package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {

	private static final double DELTA = 0.001;
	private static final double EARTH_PERIMETER = SphericCoordinate.EARTH_RADIUS * 2 * Math.PI;
	private Coordinate north;
	private AbstractCoordinate south;
	private SphericCoordinate zero;
	private AbstractCoordinate mostWest;
	private SphericCoordinate mostEast;

	@Before
	public void setup() throws CoordinateException {
		north = new SphericCoordinate(90, 90);
		south = new SphericCoordinate(-90, -90);
		zero = new SphericCoordinate(0, 0);
		mostWest = new SphericCoordinate(0, -180);
		mostEast = new SphericCoordinate(0, 180);
	}


	@Test
	public void testOverlapDistance() throws CoordinateException {
		Assert.assertEquals(0, mostWest.getDistance(mostEast), DELTA);
	}
	

	@Test
	public void testSamePointDistance() throws CoordinateException {

		Assert.assertEquals(0, zero.getDistance(zero), DELTA);
	}

	@Test(expected = CoordinateException.class)
	public void testInvalidArguments1() throws CoordinateException {
		new SphericCoordinate(-91, 0);
	}

	@Test(expected = CoordinateException.class)
	public void testInvalidArguments2() throws CoordinateException {
		new SphericCoordinate(91, 0);
	}

	@Test(expected = CoordinateException.class)
	public void testInvalidArguments3() throws CoordinateException {
		new SphericCoordinate(0, 181);
	}

	@Test(expected = CoordinateException.class)
	public void testInvalidArguments4() throws CoordinateException {
		new SphericCoordinate(0, -181);
	}
	
	@Test
	public void testDiffrentRadiusLength() throws CoordinateException {
		Assert.assertEquals(6671.0,north.getDistance(new SphericCoordinate(-90, 0,300)), DELTA);
	}
	
	@Test
	public void testIsEqual() throws CoordinateException {
		Assert.assertTrue(north.isEqual(north));
		Assert.assertFalse(north.isEqual(south));
		Assert.assertTrue(north.isEqual(new CartesianCoordinate(0, 0, SphericCoordinate.EARTH_RADIUS)));
	}


}
