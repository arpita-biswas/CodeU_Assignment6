package CodeU_Assignment6;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Tests {
    
    //Check if the "initial" rearrangement provided as input is null
    @Test(expected = NullRearrangementsException.class)
    public void test_initialRearrangementIsNull() throws RearrangementExceptions {
        int [] initial = null;
        int [] expected = {0};
        RearrangingCars.rearrangingSteps(initial, expected);
    }
    
    //Check if the "expected" rearrangement provided as input is null
    @Test(expected = NullRearrangementsException.class)
    public void test_expectedRearrangementIsNull() throws RearrangementExceptions {
        int [] initial = {0};
        int [] expected = null;
        RearrangingCars.rearrangingSteps(initial, expected);
    }
    
    //Check if the length of the two arrays, "initial" and "expected" are different 
    @Test(expected = LengthMismatchException.class)
    public void test_lengthMismatch() throws RearrangementExceptions {
        int [] initial = {0,1};
        int [] expected = {0};
        RearrangingCars.rearrangingSteps(initial, expected);
    }
    
    //Check if the "initial" array represent a valid permutation of [0,...,n-1] where n is the length of the array
    @Test(expected = InvalidRearrangementException.class)
    public void test_validInitialRearrangement() throws RearrangementExceptions {
        int [] initial = {1,2};
        int [] expected = {0,1};
        RearrangingCars.rearrangingSteps(initial, expected);
    }   
    
    //Check if the "expected" array represent a valid permutation of [0,...,n-1] where n is the length of the array
    @Test(expected = InvalidRearrangementException.class)
    public void test_validExpectedRearrangement() throws RearrangementExceptions {
        int [] initial = {0,1};
        int [] expected = {-1,0};
        RearrangingCars.rearrangingSteps(initial, expected);
    } 
    
    //Test 0 length arrays
    @Test
    public void test_zeroLengthRearrangements() throws RearrangementExceptions {
        int [] initial = new int[0];
        int [] expected = new int[0];
        assertEquals("", RearrangingCars.rearrangingSteps(initial, expected));
    } 
    
    //Test same initial and expected rearrangement
    @Test
    public void test_equalRearrangements() throws RearrangementExceptions {
        int [] initial = {0,1,2};
        int [] expected = initial;
        assertEquals("", RearrangingCars.rearrangingSteps(initial, expected));
    } 
    
    //Test when only one step is required
    @Test
    public void test_oneStepRearrangements() throws RearrangementExceptions {
        int [] initial = {0,1,2};
        int [] expected = {1,0,2};
        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(new Step(1,0));
        assertEquals(steps.toString(), RearrangingCars.rearrangingSteps(initial, expected));
    } 
    
    //Test when each car has to be shifted one position to its left: n-1 steps required
    @Test
    public void test_shiftLeftRearrangement() throws RearrangementExceptions {
        int [] initial = {0,1,2,3};
        int [] expected = {1,2,3,0};
        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(new Step(1,0));
        steps.add(new Step(2,1));
        steps.add(new Step(3,2));
                
        String resultSteps = RearrangingCars.rearrangingSteps(initial, expected);   
        
        assertEquals(steps.toString(), resultSteps.toString());
    } 
    
    //Test when each car has to be shifted one position to its right (and rotated) : n-1 steps required
    @Test
    public void test_rotateShiftRightRearrangement() throws RearrangementExceptions {
        int [] initial = {0,1,2,3};
        int [] expected = {3,0,1,2};
        ArrayList<Step> steps = new ArrayList<Step>();
        steps.add(new Step(3,0));
        steps.add(new Step(2,3));
        steps.add(new Step(1,2));
                
        String resultSteps = RearrangingCars.rearrangingSteps(initial, expected);   
        
        assertEquals(steps.toString(), resultSteps.toString());
    } 
    
    //Test when the initial and expected rearrangements have the empty slot at the same index
    @Test
    public void test_sameEmptySlotIndex() throws RearrangementExceptions {
        int [] initial = {0,1,2};
        int [] expected = {0,2,1};
        ArrayList<Step> steps1 = new ArrayList<Step>();
        steps1.add(new Step(1,0));
        steps1.add(new Step(2,1));
        steps1.add(new Step(0,2));
        
        ArrayList<Step> steps2 = new ArrayList<Step>();
        steps2.add(new Step(2,0));
        steps2.add(new Step(1,2));
        steps2.add(new Step(0,1));
        
        HashSet<String> expectedStepsString = new HashSet<String>();
        expectedStepsString.add(steps1.toString());
        expectedStepsString.add(steps2.toString());
        
        String resultSteps = RearrangingCars.rearrangingSteps(initial, expected);
        
        assertTrue(expectedStepsString.toString().contains(resultSteps));
    } 
    
    //Test when two pairs of slots need to be swapped: 6 steps required
    @Test
    public void test_twoswaps() throws RearrangementExceptions {
        int [] initial = {0,1,2,3,4};
        int [] expected = {0,2,1,4,3};
        ArrayList<Step> steps1 = new ArrayList<Step>();
        steps1.add(new Step(1,0));
        steps1.add(new Step(2,1));
        steps1.add(new Step(0,2));
        steps1.add(new Step(3,0));
        steps1.add(new Step(4,3));
        steps1.add(new Step(0,4));
        
        ArrayList<Step> steps2 = new ArrayList<Step>();
        steps2.add(new Step(2,0));
        steps2.add(new Step(1,2));
        steps2.add(new Step(0,1));
        steps2.add(new Step(3,0));
        steps2.add(new Step(4,3));
        steps2.add(new Step(0,4));
                
        ArrayList<Step> steps3 = new ArrayList<Step>();
        steps3.add(new Step(2,0));
        steps3.add(new Step(1,2));
        steps3.add(new Step(0,1));
        steps3.add(new Step(3,0));
        steps3.add(new Step(4,3));
        steps3.add(new Step(0,4));
        
        ArrayList<Step> steps4 = new ArrayList<Step>();
        steps4.add(new Step(1,0));
        steps4.add(new Step(2,1));
        steps4.add(new Step(0,2));
        steps4.add(new Step(3,0));
        steps4.add(new Step(4,3));
        steps4.add(new Step(0,4));
        
        HashSet<String> expectedStepsString = new HashSet<String>();
        expectedStepsString.add(steps1.toString());
        expectedStepsString.add(steps2.toString());
        expectedStepsString.add(steps3.toString());
        expectedStepsString.add(steps4.toString());
        
        String resultSteps = RearrangingCars.rearrangingSteps(initial, expected);
        
        assertTrue(expectedStepsString.toString().contains(resultSteps.toString()));
    } 
    
    //Test when two cycles of shifts are required
    @Test
    public void test_twoCycles() throws RearrangementExceptions {
        int [] initial = {0,1,2,3,4,5};
        int [] expected = {2,0,1,5,3,4};
        ArrayList<Step> steps1 = new ArrayList<Step>();
        steps1.add(new Step(2,0));
        steps1.add(new Step(1,2));
        steps1.add(new Step(3,1));
        steps1.add(new Step(5,3));
        steps1.add(new Step(4,5));
        steps1.add(new Step(1,4));
        
        ArrayList<Step> steps2 = new ArrayList<Step>();
        steps2.add(new Step(2,0));
        steps2.add(new Step(1,2));
        steps2.add(new Step(4,1));
        steps2.add(new Step(3,4));
        steps2.add(new Step(5,3));
        steps2.add(new Step(1,5));
        
        ArrayList<Step> steps3 = new ArrayList<Step>();
        steps3.add(new Step(2,0));
        steps3.add(new Step(1,2));
        steps3.add(new Step(5,1));
        steps3.add(new Step(4,5));
        steps3.add(new Step(3,4));
        steps3.add(new Step(1,3));
        
        HashSet<String> expectedStepsString = new HashSet<String>();
        expectedStepsString.add(steps1.toString());
        expectedStepsString.add(steps2.toString());
        expectedStepsString.add(steps3.toString());
        
        String resultSteps = RearrangingCars.rearrangingSteps(initial, expected);
        
        assertTrue(expectedStepsString.toString().contains(resultSteps));
    } 
    
    

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Tests.class);
          
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
        }
          
        System.out.println("All tests successful: "+ result.wasSuccessful());
     }
}
