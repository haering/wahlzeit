package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private static final double DELTA = 0.001;
	private static final double EARTH_PERIMETER = Coordinate.EARTH_RADIUS * 2 * Math.PI;
	private Coordinate north;
	private Coordinate south;
	private Coordinate zero;
	private Coordinate mostWest;
	private Coordinate mostEast;
	private Coordinate middleWest;

	@Before
	public void setup() {
		north = new Coordinate(90, 90);
		south = new Coordinate(-90, -90);
		zero = new Coordinate(0, 0);
		mostWest = new Coordinate(0, -180);
		mostEast = new Coordinate(0, 180);
		middleWest = new Coordinate(0,90);
	}

	@Test
	public void testPoleToPoleDistance() {
		Assert.assertEquals(EARTH_PERIMETER / 2, north.getDistanceTo(south), DELTA);
	}

	@Test
	public void testOverlapDistance() {
		Assert.assertEquals(0, mostWest.getDistanceTo(mostEast), DELTA);
	}

	@Test
	public void testMostWestToZeroDistance() {
		Assert.assertEquals(EARTH_PERIMETER / 2, zero.getDistanceTo(mostEast), DELTA);
	}
	
	@Test
	public void testDistanceFrom() {
		Assert.assertEquals(EARTH_PERIMETER/4, middleWest.getDistanceTo(mostWest),DELTA);
	}

	@Test
	public void testSamePointDistance() {

		Assert.assertEquals(0, zero.getDistanceTo(zero), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments1() {
		new Coordinate(-91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments2() {
		new Coordinate(91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments3() {
		new Coordinate(0, 181);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidArguments4() {
		new Coordinate(0, -181);
	}

}
