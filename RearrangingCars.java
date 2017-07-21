package CodeU_Assignment6;

import java.util.ArrayList;
import java.util.HashSet;

public class RearrangingCars {
    /** rearrangingSteps(initial, expected) returns the steps required to arrange the cars
     *  according to the "expected" rearrangement, starting from the "initial" rearrangement. 
     *  
     *  Solution:
     *      1. Check if the current and the expected rearrangements are valid, if not, then throw an exception.
     *      2. If the arrays are of length 0 or are exactly equal, there is no steps to be taken, hence return "".
     *      3. Otherwise, call rearrangingStepsHelper() to get the required series of steps. 
     *         Return the required steps as a String.           
     * 
     * @param initial: an array representing initial rearrangement of the cars
     * @param expected: an array representing expected rearrangement of the cars
     * @return String: A string containing a series of steps required to arrange the cars
     *  according to the "expected" rearrangement, starting from the "initial" rearrangement.
     * @throws RearrangementException 
     */
    public static String rearrangingSteps(int [] initial, int [] expected) throws RearrangementExceptions{
        
        StringBuilder str = new StringBuilder();
        
        //If the initial or expected rearrangement array is null, then throw an exception.
        if(initial == null || expected == null){
            throw new NullRearrangementsException("Either the initial rearrangement or the expected rearrangement is null");
        }
        
        //If the initial and expected rearrangement array do not have same length, then throw an exception.
        else if(initial.length != expected.length){
            throw new LengthMismatchException("The length of arrays representing the initial and expected rearrangement are not same");
        }
        
        //If the initial or expected rearrangement array is not a valid permutation of {0,...,array.length-1}, then throw an exception.
        else if(!isValidPermutation(initial) || !isValidPermutation(expected)){
            throw new InvalidRearrangementException("The initial or the expected rearrangement is invalid!");
        }
        
        else{
            //If the initial and expected rearrangement is empty (no slots), then nothing to do, simply return "".
            if(initial.length == 0){
                return str.toString();
            }
            
            //If the initial and expected rearrangement are equal, then no steps required, return "".
            boolean isEqual = true;
            for(int i=0; i<initial.length; i++){
                if(initial[i] != expected[i]){
                    isEqual = false;
                }
            }
            if(isEqual){
                return str.toString();
            }
            else{
                //Obtain the required series of steps and print them
                ArrayList<Step> steps = rearrangingStepsHelper(initial, expected);
                str.append(steps.toString());
                        
                return str.toString();
            }
        }
    }
    
    /** rearrangingStepsHelper(current, expected) generates a series of steps required to arrange the cars
     *  according to the "expected" rearrangement, starting from the "current" rearrangement. 
     *  At each step, a car is moved from its location to the empty slot.
     * 
     * Solution:
     *  1. Create an inverse mapping of the current rearrangement.
     *  2. Get the index corresponding to the empty slot, and look for the current location
     *     of the car that is expected in that slot (using inverse mapping), (let us call it "move_from").
     *     i) If "move_from" is SAME as "empty_slot" (current and expected both have the same empty slot index),
     *        then, select a random index (where the car parked differs from expected) and make it "move_from".
     *        Then, go to step (2c).
     *  3. Make a move from move_from index to empty_slot index, and update the current rearrangement.
     *  4. Repeat until the current rearrangement becomes same as that of the expected rearrangement.
     * 
     * @param current: an array representing current rearrangement of the cars
     * @param expected: an array representing expected rearrangement of the cars
     * @return: ArrayList<Steps>: representing the required series of steps
     */
    private static  ArrayList<Step> rearrangingStepsHelper(int [] current, int [] expected){
        
        ArrayList<Step> steps = new ArrayList<Step>();
        
        //Create an inverse-mapping of the "current" rearrangement.
        int [] inverseMapping_current = getInverseMapping(current);            
        
        //After obtaining the inverse-mapping, find the index corresponding to 0 (no car).
        int empty_slot = findEmptySlot(inverseMapping_current);
        
        //Obtain the set of spaces (indices) which do not have correctly placed cars (and are yet to be rearranged)
        HashSet<Integer> yetToRearrange = indices_needToRearrange(current, expected);
        
        //Until no space (index) has incorrectly placed car (no index needs further rearrangement)
        while(!yetToRearrange.isEmpty()){
            //Let "carToBeRearranged" be the car to be correctly placed in the empty slot
            int carToBeRearranged = expected[empty_slot];                

            //Obtain the index where "carToBeRearranged" is currently placed, and call it "move_from"
            int move_from = inverseMapping_current[carToBeRearranged];
            
            //If "move_from" is same as the "empty_slot", then, the "expected-rearrangement" has empty slot at the same position
            if(move_from == empty_slot){
                //Since the empty_slot position matches with the expected rearrangement, remove it from the set of "yetToRearrange" indices
                yetToRearrange.remove(empty_slot);
                
                //If there is no more rearrangement required, then break
                if(yetToRearrange.isEmpty()){
                    break;
                }
                
                //Now, prepare to move a car from any location that has an incorrectly placed car.
                move_from = yetToRearrange.iterator().next();     
                carToBeRearranged = current[move_from];
            }                

            //Move an incorrectly placed car to its correct index  
            steps.add(new Step(move_from, empty_slot));
            
            //Update the current rearrangement                
            current[empty_slot] = current[move_from];
            current[move_from] = 0;  
            
            //Update the inverse mapping accordingly
            inverseMapping_current[carToBeRearranged] = empty_slot;
            inverseMapping_current[0] = move_from;                               

            //Remove the empty_slot from the set of "yetToRearrange" indices, since it is occupied now
            yetToRearrange.remove(empty_slot);
            
            //The index from which the car has moved, now contains 0 (no car) and becomes the new empty_slot 
            empty_slot = move_from;                
        }
        return steps;
    }
    
    /** isValidPermutation(array) returns whether or not the "array" contains
     *  a valid permutation of [0,1,...,n] where n+1 is the length of the array.
     * 
     * @param array: integer array denoting a rearrangement of cars in spaces
     * @return Boolean: true if the permutation is valid
     */
    private static boolean isValidPermutation(int [] array){
        boolean found [] = new boolean[array.length];
        for(int i=0; i<array.length; i++){
            if(array[i]<0 || array[i]>=array.length){
                return false;
            }
            if(found[array[i]]){
                return false;
            }
            found[array[i]] = true;
        }
        return true;
    }
    
    /** getInverseMapping(array) takes as input an array and returns the inverse mapping
     * that is, mapping each car_number to the space index where it is parked.
     * 
     * Expects the "array" to represent a valid rearrangement.
     * 
     * @param array: integer array denoting a rearrangement of cars in spaces
     * @return int[]: integer array denoting the inverse mapping
     */
    private static int [] getInverseMapping(int [] array){
        int [] inverseMapping_array = new int[array.length];
        for(int i=0; i<array.length; i++){
            inverseMapping_array[array[i]] = i;
        }
        return inverseMapping_array;
    }
    
    /** findEmptySlot(inverseMapping_array) returns the space_index which has no car
     * that is, the index of the empty slot.
     * 
     * Expects the "inverseMapping_array" to be of length >= 1.
     * 
     * @param inverseMapping_array: integer array denoting the inverse mapping
     * @return Integer: integer denoting index of empty slot
     */
    private static int findEmptySlot(int [] inverseMapping_array){
        return inverseMapping_array[0];
    }
    
    /** indices_needToRearrange(current, expected) returns a HashSet of indices which 
     * differs in "current" and "expected" rearrangement.
     * 
     * Expects the "array" to represent a valid rearrangement.
     * 
     * @param current: an array representing current rearrangement of the cars
     * @param expected: an array representing expected rearrangement of the cars
     * @return HashSet<Integer>: Set of all indices which differs "current" and "expected" rearrangement.
     */
    private static HashSet<Integer> indices_needToRearrange(int [] current, int [] expected){
        HashSet<Integer> yetToRearrange = new  HashSet<Integer>();
        for(int i=0; i<current.length; i++){
            if(current[i] != expected[i]){
                yetToRearrange.add(i);
            }
        }
        return yetToRearrange;
    }
}
