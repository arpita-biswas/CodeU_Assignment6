# CodeU_Assignment6

## Problem:
There is a parking lot with N spaces and N-1 cars in it. Your task is to write an algorithm to rearrange the cars in a given way. Only one car can be moved at a time to the empty slot. The parking lot is described by an array of numbers. Let's identify cars with numbers from 1
to N-1, and the number 0 means an empty parking space.  
The input to your function is two arrays, each with a permutation of the numbers 0 to N (you don't have to validate it). Your function must generate a series of moves and print them.  

## Solution:  
1. Check if the current and the expected rearrangements are valid, if not, then throw an exception.  
2. If the arrays are of length 0 or are exactly equal, there is no steps to be taken, hence return "".  
3. Otherwise, call rearrangingStepsHelper() to get the required series of steps.   
     A. Create an inverse mapping of the current rearrangement.  
     B. Get the index corresponding to the empty slot, and look for the current location of the car that is expected in that slot (using inverse mapping), (let us call it "move_from").  
        i) If "move_from" is SAME as "empty_slot" (current and expected both have the same empty slot index),   
           then, select a random index (where the car parked differs from expected) and make it "move_from".  
           Then, go to step (2c).  
     C. Make a move from move_from index to empty_slot index, and update the current rearrangement.  
     D. Repeat until the current rearrangement becomes same as that of the expected rearrangement.  
4. Return the required steps as a String.   

## Time Complexity: 
O(n) where n is the number of parking slots available.

