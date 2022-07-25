package testing;


import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;




class FactorsTester {

	@Test
	void testPerfect1()
	{	
		// TEST 1: should throw the exception because the parameter value is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.perfect(0));
	}
	
	@Test
	void testPerfect2()
	{	
		// TEST 2: should succeed because 1 is a valid parameter value, but is not a perfect number
		assertFalse(FactorsUtility.perfect(1));
	}
	
	@Test
	void testPerfect3()
	{	
		// TEST 3: should succeed because 6 is a valid parameter value, and is a perfect number
		assertTrue(FactorsUtility.perfect(6));
	}
	
	@Test
	void testPerfect4()
	{	
		// TEST 4: should succeed because 7 is a valid parameter value, but is not a perfect number
		// I've coded this using assertEquals to show that there's often more than one way to write a test 
		boolean expected = false;
		assertEquals(expected, FactorsUtility.perfect(7));
	}
	@Test
	void testgetFactors1()
	{	
		// TEST 1: should throw the exception because the parameter value is less than 0
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.getFactors(-1));
	}
	@Test
	void testgetFactors2()
	{	
		// TEST 2: should succeed because 0 is a valid parameter value, but has no factors
		assertTrue(FactorsUtility.getFactors(0).isEmpty());
	}
	@Test
	void testgetFactors3()
	{	
		// TEST 3: should succeed because 1 is a valid parameter value, but has no factors
		assertTrue(FactorsUtility.getFactors(1).isEmpty());
	}
	@Test
	void testgetFactors4()
	{	
		// TEST 4: should succeed because 2 is a valid parameter value, but has only 1 as factor
		 ArrayList<Integer> list2 = new ArrayList<Integer>();
		 list2.add(1);
		 assertEquals(FactorsUtility.getFactors(2), list2);
	}
	@Test
	void testgetFactors5()
	{	
		// TEST 5: should succeed because 2 is a valid parameter value, but has 1,2,3,4,6 as factor
		 ArrayList<Integer> list2 = new ArrayList<Integer>();
		 list2.add(1);
		 list2.add(2);
		 list2.add(3);
		 list2.add(4);
		 list2.add(6);
		 assertEquals(FactorsUtility.getFactors(12), list2);
	}
	@Test
	void testfactor1()
	{	
		// TEST 1: should throw the exception because the one parameter value is unacceptable i.e, b<1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(0,0));
	}
	@Test
	void testfactor2()
	{	
		// TEST 2: should throw the exception because the one parameter value is unacceptablei.e a<0
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-1,1));
	}
	@Test
	void testfactor3()
	{	
		// TEST 3: should throw the exception because the both parameter value is unacceptablei.e a<0,b<1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-1,0));
	}
	@Test
	void testfactor4()
	{	
		// TEST 4: should succeed because a=0,b=1 is a valid parameter value, and 1 is a factor of 0
		assertTrue(FactorsUtility.factor(0,1));
	}
	@Test
	void testfactor5()
	{	
		// TEST 5: should succeed because a=4,b=2 is a valid parameter value, and 2 is a factor of 4
		assertTrue(FactorsUtility.factor(4,2));
	}

}

