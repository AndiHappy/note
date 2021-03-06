package leetcode.L;

import java.util.Arrays;
import java.util.Random;

/**
 * @author guizhai
 Shuffle a set of numbers without duplicates.
 
 Example:
 
 // Init an array with set 1, 2, and 3.
 int[] nums = {1,2,3};
 Solution solution = new Solution(nums);
 
 // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 solution.shuffle();
 
 // Resets the array back to its original configuration [1,2,3].
 solution.reset();
 
 // Returns the random shuffling of array [1,2,3].
 solution.shuffle();
 */
public class L384ShuffleanArray {



 private int[] nums;
 private Random random;

 public L384ShuffleanArray(int[] nums) {
  this.nums = nums;
  random = new Random();
 }

 /** Resets the array to its original configuration and return it. */
 public int[] reset() {
  return nums;
 }

 /** Returns a random shuffling of the array. */
 public int[] shuffle() {
  if (nums == null)
   return null;
  int[] a = nums.clone();
  for (int i = a.length; i >1; i--) {
   swap(a, i-1, random.nextInt(i));
  }
  return a;
 }

 private void swap(int[] a, int i, int j) {
  int t = a[i];
  a[i] = a[j];
  a[j] = t;
 }

 public static void main(String[] args) {
  L384ShuffleanArray test = new L384ShuffleanArray(new int[] {1,2,3,4,5,6});
  System.out.println(Arrays.toString(test.shuffle()));
  System.out.println(Arrays.toString(test.reset()));

 }

}
