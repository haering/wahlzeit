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
	private Coordinate middleWest;

	@Before
	public void setup() {
		north = new SphericCoordinate(90, 90);
		south = new SphericCoordinate(-90, -90);
		zero = new SphericCoordinate(0, 0);
		mostWest = new SphericCoordinate(0, -180);
		mostEast = new SphericCoordinate(0, 180);
		middleWest = new SphericCoordinate(0,90);
	}

	@Test
	public void testPoleToPoleDistance() {
		Assert.assertEquals(EARTH_PERIMETER / 2, north.getDistance(south), DELTA);
	}

	@Test
	public void testOverlapDistance() {
		Assert.assertEquals(0, mostWest.getDistance(mostEast), DELTA);
	}

	@Test
	public void testMostWestToZeroDistance() {
		Assert.assertEquals(EARTH_PERIMETER / 2, zero.getDistance(mostEast), DELTA);
	}
	
	@Test
	public void testDistanceFrom() {
		Assert.assertEquals(EARTH_PERIMETER/4, middleWest.getDistance(mostWest),DELTA);
	}

	@Test
	public void testSamePointDistance() {

		Assert.assertEquals(0, zero.getDistance(zero), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments1() {
		new SphericCoordinate(-91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments2() {
		new SphericCoordinate(91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments3() {
		new SphericCoordinate(0, 181);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments4() {
		new SphericCoordinate(0, -181);
	}
	
	@Test
	public void testDiffrentRadiusLength() {
		Assert.assertEquals(6671.0,north.getDistance(new SphericCoordinate(-90, 0,300)), DELTA);
	}
	
	@Test
	public void testIsEqual() {
		Assert.assertTrue(north.isEqual(north));
		Assert.assertFalse(north.isEqual(south));
		Assert.assertTrue(north.isEqual(new CartesianCoordinate(0, 0, SphericCoordinate.EARTH_RADIUS)));
	}


}
