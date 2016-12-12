package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartesianCoordinateTest {

	private static final double DELTA = 0.001;
	private CartesianCoordinate zero;
	private CartesianCoordinate otherPoint;


	@Before
	public void setup() throws CoordinateException {

		zero = new CartesianCoordinate(0, 0, 0);
		otherPoint = new CartesianCoordinate(9, 9 , 9);
	}

	@Test
	public void testOverlapDistance() throws CoordinateException {
		Assert.assertEquals(15.588, zero.getDistance(otherPoint), DELTA);
	}


	@Test
	public void testSamePointDistance() throws CoordinateException {

		Assert.assertEquals(0, zero.getDistance(zero), DELTA);
	}

	@Test()
	public void testConstructor() throws CoordinateException {
		CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(123, 456, 789);
		assertEquals(123, cartesianCoordinate.getX(),DELTA);
		assertEquals(456, cartesianCoordinate.getY(),DELTA);
		assertEquals(789, cartesianCoordinate.getZ(),DELTA);
	}
	

}
