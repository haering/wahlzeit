package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.AbstractCoordinate.CoordinateFactory;

public class CartesianCoordinateTest {

	private static final double DELTA = 0.001;
	private CartesianCoordinate zero;
	private CartesianCoordinate otherPoint;


	@Before
	public void setup() {

		zero = CoordinateFactory.createCartesianCoordinate(0, 0, 0);
		otherPoint = CoordinateFactory.createCartesianCoordinate(9, 9 , 9);
	}

	@Test
	public void testOverlapDistance() {
		Assert.assertEquals(15.588, zero.getDistance(otherPoint), DELTA);
	}


	@Test
	public void testSamePointDistance() {

		Assert.assertEquals(0, zero.getDistance(zero), DELTA);
	}

	@Test()
	public void testConstructor() {
		CartesianCoordinate cartesianCoordinate = CoordinateFactory.createCartesianCoordinate(123, 456, 789);
		assertEquals(123, cartesianCoordinate.getX(),DELTA);
		assertEquals(456, cartesianCoordinate.getY(),DELTA);
		assertEquals(789, cartesianCoordinate.getZ(),DELTA);
	}
	

}
