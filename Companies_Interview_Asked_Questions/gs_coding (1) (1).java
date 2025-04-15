1. Verbally explain the logic to reverse a string in place i.e. without using extra memory.
https://www.geeksforgeeks.org/reverse-string-without-using-any-temporary-variable/

Using XOR Properties:
Commutative property: A ^ B = B ^ A
Associative property: (A ^ B) ^ C = A ^ (B ^ C)
Identity property: A ^ 0 = A
Negation property: A ^ A = 0
Bitwise complement property A ^ 1 = ~A

public class StringReverseXOR {
    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();

        int start = 0;
        int end = charArray.length - 1;

        while (start < end) {
            // XOR for swapping the characters
            charArray[start] ^= charArray[end];
            charArray[end] ^= charArray[start];
            charArray[start] ^= charArray[end];

            start++;
            end--;
        }

        // Convert char array back to string
        return new String(charArray);
    }

    public static void main(String[] args) {
        String original = "Hello, World!";
        System.out.println("Original string: " + original);

        String reversed = reverseString(original);
        System.out.println("Reversed string: " + reversed);
    }
}
-------------------------------------------------------------------------------------------------
2. Find minimum element in sorted rotated array.
● Explain the question.
● Explain and code brute force logic.
● What are the edge cases for this scenario?
● How to find an element in a sorted integer array?
https://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/

public class MinInRotatedSortedArray {
    
    /**
     * Function to find the minimum element in a rotated sorted array.
     * 
     * @param nums The rotated sorted array.
     * @return The minimum element in the array.
     */
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Check if the mid element is greater than the last element
            if (nums[mid] > nums[right]) {
                // Minimum element is in the right half
                left = mid + 1;
            } else {
                // Minimum element is in the left half
                right = mid;
            }
        }

        // Left pointer points to the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 6, 1, 2, 3, 4};
        int[] arr2 = {1, 2, 3, 4};

        // Test cases
        System.out.println("Minimum element in arr1: " + findMin(arr1)); // Expected: 1
        System.out.println("Minimum element in arr2: " + findMin(arr2)); // Expected: 1
    }
}
-------------------------------------------------------------------------------------------------
3. Search Element in a Rotated Sorted Array.
● Explain the question.
● Explain and code brute force logic.
● What are the edge cases for this scenario?
● How to find an element in a sorted integer array?

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. 
It does this by determining a specific condition that ensures that the target is not present in that half.
arr=[7,8,9,1,2,3,4,5,6], target = 8
Key Observation: Though the array is rotated, we can clearly notice that for every index, one of the 2 halves will always be sorted. 
In the above example, the right half of the index mid is sorted.
So, to efficiently search for a target value using this observation, we will follow a simple two-step process. 
    First, we identify the sorted half of the array. 
    Once found, we determine if the target is located within this sorted half. 
        If not, we eliminate that half from further consideration. 
        Conversely, if the target does exist in the sorted half, we eliminate the other half.

import java.util.*;

public class tUf {
    public static int search(ArrayList<Integer> arr, int n, int k) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            // if mid points to the target
            if (arr.get(mid) == k)
                return mid;

            // dentify the sorted half, check where the target is located, and then eliminate one half accordingly:
            // if left part is sorted
            if (arr.get(low) <= arr.get(mid)) {
                if (arr.get(low) <= k && k <= arr.get(mid)) {
                    // it signifies that the target is in this sorted half. So, we will eliminate the right half
                    // element exists
                    high = mid - 1;
                } else {
                    // the target does not exist in the sorted half. So, we will eliminate this left half
                    // element does not exist
                    low = mid + 1;
                }
            } else { // if right part is sorted
                if (arr.get(mid) <= k && k <= arr.get(high)) {
                    // it signifies that the target is in this sorted right half. So, we will eliminate the left half
                    // element exists
                    low = mid + 1;
                } else {
                    // the target does not exist in this sorted half. So, we will eliminate this right half
                    // element does not exist
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(7, 8, 9, 1, 2, 3, 4, 5, 6));
        int n = 9, k = 1;
        int ans = search(arr, n, k);
        if (ans == -1)
            System.out.println("Target is not present.");
        else
            System.out.println("The index is: " + ans);
    }
}
-------------------------------------------------------------------------------------------------
4. Find the BestAverageScore from a list of list containing marks of students and names.
- Discussed the brute force and optimal approach
- Implementation of the same
- Test cases check
- Time complexity
// /* Problem Name is &&& Best Average Grade &&& PLEASE DO NOT REMOVE THIS LINE. */
// /*
// ** Instructions:
// **
// ** Given a list of student test scores, find the best average grade.
// ** Each student may have more than one test score in the list.
// **
// ** Complete the bestAverageGrade function in the editor below.
// ** It has one parameter, scores, which is an array of student test scores.

// ** use a floor function to return the largest integer less than or equal to the average.
// ** Return 0 for an empty input.
// **
// ** Example:
// **
// ** Input:
// ** [ [ "Bobby", "87" ],
// ** [ "Charles", "100" ],
// ** [ "Eric", "64" ],
// ** [ "Charles", "22" ] ].
// **
// ** Expected output: 87
// ** Explanation: The average scores are 87, 61, and 64 for Bobby,
//Charles, and Eric,
// ** respectively. 87 is the highest.
// */

public class Main {
    // Function to find the name with the maximum average score
    public static String findMaxAverage(String[][] scores) {
        // Create two HashMaps to store the sum and count of scores for each name
        Map<String, Integer> sumMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        
        // Calculate sum and count of scores for each name
        for (String[] score : scores) {
            String name = score[0];
            int value = Integer.parseInt(score[1]);
            
            // Update sum and count maps
            sumMap.put(name, sumMap.getOrDefault(name, 0) + value);
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }
        
        double maxAverage = Double.MIN_VALUE;
        String maxName = "";
        
        // Calculate average and find the maximum average
        for (String name : sumMap.keySet()) {
            // Calculate average score for the current name
            double average = (double) sumMap.get(name) / countMap.get(name);
            
            // Update maxAverage and maxName if the current average is greater
            if (average > maxAverage) {
                maxAverage = average;
                maxName = name;
            }
        }
        
        // Return the name with the maximum average score
        return maxName; //or maxAverage
    }

    public static void main(String[] args) {
        // Test scores array
        String[][] scores = {
            {"Bob", "87"}, {"Mike", "35"}, {"Bob", "52"},
            {"Jason", "35"}, {"Mike", "55"}, {"Jessica", "99"}
        };
        
        // Call the function and print the result
        System.out.println(findMaxAverage(scores));  // Output: Jessica
    }
}
--------------------------------------
● find the second highest average:

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    // Function to find the name with the second highest average score
    public static String findSecondMaxAverage(String[][] scores) {
        // Create two HashMaps to store the sum and count of scores for each name
        Map<String, Integer> sumMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        // Calculate sum and count of scores for each name
        for (String[] score : scores) {
            String name = score[0];
            int value = Integer.parseInt(score[1]);

            // Update sum and count maps
            sumMap.put(name, sumMap.getOrDefault(name, 0) + value);
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }

        // Use a PriorityQueue to keep track of the top two averages (sort the list of averages in descending order)
        PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Calculate average and add to the PriorityQueue
        for (String name : sumMap.keySet()) {
            double average = (double) sumMap.get(name) / countMap.get(name);
            pq.offer(Map.entry(name, average));
        }

        // If there are less than two unique names, return an empty string
        if (pq.size() < 2) {
            return "";
        }

        // Remove the highest average to get to the second highest average
        pq.poll();
        return pq.poll().getKey();
    }

    public static void main(String[] args) {
        // Test scores array
        String[][] scores = {
            {"Bob", "87"}, {"Mike", "35"}, {"Bob", "52"},
            {"Jason", "35"}, {"Mike", "55"}, {"Jessica", "99"}
        };

        // Call the function and print the result
        System.out.println(findSecondMaxAverage(scores));  // Output: Bob
    }
}

-------------------------------------------------------------------------------------------------
5. Find the largest subarray sum in the array containing integer numbers(both positive and negative).
● Brute force
● Optimal approach
https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/

class Solution{

    // arr: input array
    // n: size of array
    //Function to find the sum of contiguous subarray with maximum sum.
    long maxSubarraySum(int arr[], int n){
        
        long max_sum = Integer.MIN_VALUE;
        long curr_sum = 0;
        for(int i = 0; i < n; i++){
            curr_sum += arr[i];
            max_sum = Math.max(max_sum, curr_sum);
            if(curr_sum < 0){
                curr_sum = 0;
            }
        }
        return max_sum;
    }
    
}

public class MaximumSubarraySum {
    public static int maxSubarraySum(int[] nums) {
        return maxSubarraySum(nums, 0, nums.length - 1);
    }

    private static int maxSubarraySum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;
        int maxLeftSum = maxSubarraySum(nums, left, mid);
        int maxRightSum = maxSubarraySum(nums, mid + 1, right);
        int maxCrossingSum = maxCrossingSum(nums, left, mid, right);

        return Math.max(Math.max(maxLeftSum, maxRightSum), maxCrossingSum);
    }

    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int maxSum = maxSubarraySum(nums);
        System.out.println("Maximum subarray sum: " + maxSum);
    }
}
-------------------------------------------------------------------------------------------------
6. Given an array of non-negative integers representing the elevations from the vertical cross section of a range of hills, determine how many units of snow could be captured between the hills.

Brute force
Approach: For each index, we have to find the amount of water that can be stored and we have to sum it up. 
If we observe carefully the amount the water stored at a particular index is the minimum of maximum elevation to the left and right of the index minus the elevation at that index.
Time Complexity: O(N*N) as for each index we are calculating leftMax and rightMax so it is a nested loop.
Space Complexity: O(1).

import java.util.*;
class TUF {
    static int trap(int[] arr) {
        int n = arr.length;
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int leftMax = 0, rightMax = 0;
            while (j >= 0) {
                leftMax = Math.max(leftMax, arr[j]);
                j--;
            }
            j = i;
            while (j < n) {
                rightMax = Math.max(rightMax, arr[j]);
                j++;
            }
            waterTrapped += Math.min(leftMax, rightMax) - arr[i];
        }
        return waterTrapped;
    }
    public static void main(String args[]) {
        int arr[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("The duplicate element is " + trap(arr));
    }
}

Approach: Take 2 array prefix and suffix array and precompute the leftMax and rightMax for each index. Then use the formula min(prefix[I], suffix[i])-arr[i] to compute water trapped at each index.
Time Complexity: O(3*N) as we are traversing through the array only once. And O(2*N) for computing prefix and suffix array.
Space Complexity: O(N)+O(N) for prefix and suffix arrays.

public class TrappingWater {
    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n]; // Maximum height of walls on the left side
        int[] rightMax = new int[n]; // Maximum height of walls on the right side

        // Pre-compute maximum height of walls on the left side for each index
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Pre-compute maximum height of walls on the right side for each index
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int water = 0;

        // Calculate the amount of water trapped at each index
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return water;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Amount of water trapped: " + trap(height)); // Output: 6
    }
}

Approach: Take 2 pointers l(left pointer) and r(right pointer) pointing to 0th and (n-1)th index respectively. Take two variables leftMax and rightMax and initialize them to 0. 
If height[l] is less than or equal to height[r] then if leftMax is less than height[l] update leftMax to height[l] else add leftMax-height[l] to your final answer and move the l pointer to the right i.e l++. 
If height[r] is less than height[l], then now we are dealing with the right block. If height[r] is greater than rightMax, then update rightMax to height[r] else add rightMax-height[r] to the final answer. 
Now move r to the left. Repeat these steps till l and r crosses each other.

Intuition: We need a minimum of leftMax and rightMax.So if we take the case when height[l]<=height[r] we increase l++, so we can surely say that there is a block with a height more than height[l] to the right of l. 
And for the same reason when height[r]<=height[l] we can surely say that there is a block to the left of r which is at least of height[r]. 
So by traversing these cases and using two pointers approach the time complexity can be decreased without using extra space.

Time Complexity: O(N) because we are using 2 pointer approach.
Space Complexity: O(1) because we are not using anything extra.

public class TrappingWater {
    public static int trap(int[] height) {
        // Check if the height array is null or empty
        if (height == null || height.length == 0) {
            return 0; // If so, no water can be trapped, return 0
        }

        int n = height.length; // Length of the height array
        int left = 0, right = n - 1; // Pointers for the left and right boundaries
        int leftMax = 0, rightMax = 0; // Variables to store the maximum height on the left and right
        int water = 0; // Variable to store the total amount of trapped water

        // Loop until the left and right pointers meet
        while (left < right) {
            // If the height of the left boundary is less than or equal to the height of the right boundary
            if (height[left] <= height[right]) {
                // If the height of the left boundary is greater than or equal to the leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // Update the leftMax
                } else {
                    water += leftMax - height[left]; // Calculate the amount of trapped water between the left boundary and leftMax
                }
                left++; // Move the left boundary to the right
            } else { // If the height of the right boundary is greater than the height of the left boundary
                // If the height of the right boundary is greater than or equal to the rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // Update the rightMax
                } else {
                    water += rightMax - height[right]; // Calculate the amount of trapped water between the right boundary and rightMax
                }
                right--; // Move the right boundary to the left
            }
        }

        return water; // Return the total amount of trapped water
    }

    public static void main(String[] args) {
        // Sample input array representing the heights of bars
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        
        // Call the trap method to calculate the amount of trapped water
        System.out.println("Amount of water trapped: " + trap(height)); // Output: 6
    }
}


-------------------------------------------------------------------------------------------------
7. Given an array and a value, find if there is a triplet in array whose sum is equal to the given value. If there is such a triplet present in array, then return the triplet. Else return [-1,-1,-1].
Input: array = {12, 3, 4, 1, 6, 9}, sum = 24;
Output: 12, 3, 9
Explanation: There is a triplet (12, 3 and 9) present
in the array whose sum is 24.

Input: array = {1, 2, 3, 4, 5}, sum = 9
Output: 5, 3, 1
Explanation: There is a triplet (5, 3 and 1) present
in the array whose sum is 9.
Time complexity: O(nlogn)+O(n⋅n)=O(n^2)
Space complexity: O(n) The sorting algorithm itself typically uses O(n) space for auxiliary storage. 
If using a built-in sort like Timsort (used by Java’s Arrays.sort), it has O(n) space complexity in the worst case.

class Solution {
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort array
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n - 2; i++) {
            // Skip duplicates for the first element of the triplet
            if(i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1; // Initialize the second pointer
            int k = n - 1; // Initialize the third pointer
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum < target) { // If the sum is less than the target, move the second pointer to the right
                    j++;
                } else if(sum > target) { // If the sum is greater than the target, move the third pointer to the left
                    k--;
                } else { // If the sum equals the target, add the triplet to the result list
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);
                    result.add(triplet);
                    j++;
                    k--;
                    // Skip duplicates for the second and third elements of the triplet
                    while(j < k && nums[j] == nums[j - 1]) j++;
                    while(j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return result;
    }
}

import java.util.Arrays;

public class Solution {

    private static int[] findTriplet(int[] nums, int targetSum) {
        int[] triplet = new int[3];
        triplet[0] = -1;
        triplet[1] = -1;
        triplet[2] = -1;
        
        // Sort the array to simplify the search
        Arrays.sort(nums);
        
        // Iterate through the array to find the triplet
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum == (targetSum - nums[i])) {
                    triplet[0] = nums[i];
                    triplet[1] = nums[j];
                    triplet[2] = nums[k];
                    return triplet;
                } else if (sum < targetSum) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return triplet;
    }

    /**
     * Main method to test the findTriplet method with an example input.
     * @param args The command-line arguments (unused).
     */
    public static void main(String[] args) {
        // Example input
        int[] nums = {1, 4, 45, 6, 10, 8};
        int targetSum = 22;

        // Find the triplet
        int[] triplet = findTriplet(nums, targetSum);

        // Print the result
        System.out.println("Triplet: " + Arrays.toString(triplet));
    }
}
-------------------------------------------------------------------------------------------------
8. Given an array of strings. Each string has an IP address and the user mentioned. Find out the
maximum accessed IP address
"10.1.1.1 - james"
"10.1.1.1 - frank"
"10.1.1.2 - sam"
output = 10.1.1.1
Example:
String [] str = {
				  "10.0.0.1 bytes=32 time=50ms TTL=63",
				  "10.0.0.2 bytes=32 time=50ms TTL=73",
				  "10.0.0.4 bytes=32 time=50ms TTL=83",
				  "10.0.0.2 bytes=32 time=50ms TTL=93",
				}
// Return 10.0.0.2

import java.util.HashMap;

public class MaxOccurrencesIPAddress {
    // Function to find the IP address with maximum occurrences
    public static String findMaxOccurrencesIPAddress(String[] arr) {
        // HashMap to store the count of each IP address
        HashMap<String, Integer> countMap = new HashMap<>();

        // Iterate through the array of strings
        for (String str : arr) {
            // Extract the IP address from the string
            String ipAddress = extractIPAddress(str);

            // Check the validity of the IP address
            if (isValidIPAddress(ipAddress)) {
                // Update the count of the IP address in the HashMap
                countMap.put(ipAddress, countMap.getOrDefault(ipAddress, 0) + 1);
            }
        }

        // Find the IP address with maximum occurrences
        String maxOccurrencesIPAddress = null;
        int maxOccurrences = 0;
        for (String ipAddress : countMap.keySet()) {
            int occurrences = countMap.get(ipAddress);
            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                maxOccurrencesIPAddress = ipAddress;
            }
        }

        return maxOccurrencesIPAddress;
    }

    // Extracts the IP address from the given string
    private static String extractIPAddress(String str) {
        return str.split(" ")[0];
    }

    // Checks the validity of an IP address
    private static boolean isValidIPAddress(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test array of strings
        String[] str = {
                "10.0.0.1 bytes=32 time=50ms TTL=63",
                "10.0.0.2 bytes=32 time=50ms TTL=73",
                "10.0.0.4 bytes=32 time=50ms TTL=83",
                "10.0.0.2 bytes=32 time=50ms TTL=93",
        };
        // Find the IP address with maximum occurrences and print it
        System.out.println("IP address with maximum occurrences: " + findMaxOccurrencesIPAddress(str));
    }
}
--------------------------------------
->To handle the case where multiple IP addresses have the same maximum number of occurrences.
// Find the maximum occurrences count
        int maxOccurrences = 0;
        for (int occurrences : countMap.values()) {
            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
            }
        }

        // Collect all IP addresses with the maximum occurrences count
        List<String> maxOccurrencesIPAddresses = new ArrayList<>();
        for (String ipAddress : countMap.keySet()) {
            if (countMap.get(ipAddress) == maxOccurrences) {
                maxOccurrencesIPAddresses.add(ipAddress);
            }
        }

        return maxOccurrencesIPAddresses;
--------------------------------------
-> we can reduce the loops by finding the maximum occurrences and collecting IP addresses with the maximum occurrences in a single pass. 
We can update the maximum occurrences dynamically as we populate the HashMap and maintain a list of IP addresses that match the current maximum occurrences.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxOccurrencesIPAddress {
    // Function to find the IP addresses with maximum occurrences
    public static List<String> findMaxOccurrencesIPAddresses(String[] arr) {
        // HashMap to store the count of each IP address
        HashMap<String, Integer> countMap = new HashMap<>();
        // List to store IP addresses with maximum occurrences
        List<String> maxOccurrencesIPAddresses = new ArrayList<>();
        // Variable to track the maximum occurrences
        int maxOccurrences = 0;

        // Iterate through the array of strings
        for (String str : arr) {
            // Extract the IP address from the string
            String ipAddress = extractIPAddress(str);

            // Check the validity of the IP address
            if (isValidIPAddress(ipAddress)) {
                // Update the count of the IP address in the HashMap
                int count = countMap.getOrDefault(ipAddress, 0) + 1;
                countMap.put(ipAddress, count);

                // Update the list of IP addresses with maximum occurrences
                if (count > maxOccurrences) {
                    maxOccurrences = count;
                    maxOccurrencesIPAddresses.clear();
                    maxOccurrencesIPAddresses.add(ipAddress);
                } else if (count == maxOccurrences) {
                    maxOccurrencesIPAddresses.add(ipAddress);
                }
            }
        }

        return maxOccurrencesIPAddresses;
    }

    // Extracts the IP address from the given string
    private static String extractIPAddress(String str) {
        return str.split(" ")[0];
    }

    // Checks the validity of an IP address
    private static boolean isValidIPAddress(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test array of strings
        String[] str = {
                "10.0.0.1 bytes=32 time=50ms TTL=63",
                "10.0.0.2 bytes=32 time=50ms TTL=73",
                "10.0.0.4 bytes=32 time=50ms TTL=83",
                "10.0.0.2 bytes=32 time=50ms TTL=93",
        };
        // Find the IP addresses with maximum occurrences and print them
        List<String> maxIPs = findMaxOccurrencesIPAddresses(str);
        System.out.println("IP addresses with maximum occurrences: " + maxIPs);
    }
}
--------------------------------------
-> Concatenate the IP addresses with the maximum count
// Function to find the IP addresses with maximum occurrences and return them as a concatenated string
    public static String findMaxOccurrencesIPAddresses(String[] arr) {
        // HashMap to store the count of each IP address
        HashMap<String, Integer> countMap = new HashMap<>();
        // Variable to track the maximum occurrences
        int maxOccurrences = 0;

        // Iterate through the array of strings
        for (String str : arr) {
            // Extract the IP address from the string
            String ipAddress = extractIPAddress(str);

            // Check the validity of the IP address
            if (isValidIPAddress(ipAddress)) {
                // Update the count of the IP address in the HashMap
                int count = countMap.getOrDefault(ipAddress, 0) + 1;
                countMap.put(ipAddress, count);

                // Update the maximum occurrences if necessary
                if (count > maxOccurrences) {
                    maxOccurrences = count;
                }
            }
        }

        // StringBuilder to concatenate the IP addresses with maximum occurrences
        StringBuilder maxOccurrencesIPAddresses = new StringBuilder();

        // Iterate through the HashMap to collect IP addresses with maximum occurrences
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxOccurrences) {
                if (maxOccurrencesIPAddresses.length() > 0) {
                    maxOccurrencesIPAddresses.append(", ");
                }
                maxOccurrencesIPAddresses.append(entry.getKey());
            }
        }

        return maxOccurrencesIPAddresses.toString();
    }
-------------------------------------------------------------------------------------------------
9. Find the first uniques character in a string
reference link:
https://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
Examples:
String str1 = "12345"
//Return 1
String str2 = "abbacd"
//Return c"

public class Main {
    public static char firstNonRepeatingCharacter(String str) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        
        // Count occurrences of each character
        for (char c : str.toCharArray()) {
			//if(charCount.containsKey(c)){
			//	return c;
			//}
			//charCount.put(c, 1);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Find the first non-repeating character
        for (char c : str.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }
        
        // If no non-repeating character found, return a default character
        return '\0';
    }

    public static void main(String[] args) {
        String str = "hello";
        char firstNonRepeating = firstNonRepeatingCharacter(str);
        if (firstNonRepeating != '\0') {
            System.out.println("First non-repeating character: " + firstNonRepeating); // Output: First non-repeating character: h
        } else {
            System.out.println("No non-repeating character found.");
        }
    }
}
-------------------------------------------------------------------------------------------------
10. Group angram in the list
Input: [ "cat", "dog", "god"]
Output: [ {"cat"}, {"dog", "god"} ]
https://favtutor.com/blogs/group-anagrams

public class AnagramGrouping {

    public static List<List<String>> groupAnagrams(String[] strs) {
        // Create a HashMap to store groups of anagrams
        Map<String, List<String>> map = new HashMap<>();

        // Iterate through the input array of words
        for (String word : strs) {
            // Sort the characters of the word
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            // Check if the sorted word exists as a key in the map
            // If not, create a new list and put it in the map
            if (!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());
            }
            // Add the original word to the corresponding list in the map
            map.get(sortedWord).add(word);
        }

        // Convert map values to a list of lists
        List<List<String>> result = new ArrayList<>(map.values());
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "dog", "god"};
        List<List<String>> result = groupAnagrams(words);
        System.out.println("Output: " + result);
    }
}
-------------------------------------------------------------------------------------------------
11. Given a string, we need to find the starting index of the longest consecutive character in the  string and return a tuple containing the starting index and the length of the consecutive substring. 
Eg: Given string: "100010", we need to return (1,3) as 0 is repeated thrice and starts at index 1
https://www.geeksforgeeks.org/maximum-consecutive-repeating-character-string/
https://www.geeksforgeeks.org/length-longest-consecutive-1s-binary-representation/
Given a String, find the starting index and length of longest uniform substring.
Example : Input abbaaabbbaaaab Output: Start Index=9 and Length=4
a. Started discussing various approaches, Interview asked me to start with any approach
I'm comfortable with.
b. Started coding with the brute force method with O(N2), and eventually solved it in O(N).
c. All test cases have to be passed.
d. Time and Space complexity was asked.
e. Edge cases were discussed.
f. How further complexity can be reduced.

public class LongestSameCharacterSubstring {
    public static void main(String[] args) {
        String input = "abbaaabbbaaaab";
        int[] result = findLongestSameCharacterSubstring(input);
        System.out.println("Start Index = " + result[0] + " and Length = " + result[1]);
    }

    public static int[] findLongestSameCharacterSubstring(String str) {

        if (str == null || str.isEmpty())
            return new int[]{-1, 0}; // If str is empty or null, return -1, 0

        int start = 0;          // Starting index of the current substring
        int maxLength = 0;      // Length of the longest substring
        int currentLength = 1;  // Current length of the substring

        // Iterate through the string starting from the second character
        for (int i = 1; i < str.length(); i++) {
            // If the current character is the same as the previous character
            if (str.charAt(i) == str.charAt(i - 1)) {
                currentLength++;  // Increment the length of the current substring
            } else {
                // If the current substring length is longer than the previous maximum length
                if (currentLength > maxLength) {
                    maxLength = currentLength;  // Update the maximum length
                    start = i - maxLength;      // Update the starting index
                }
                currentLength = 1;  // Reset the current substring length
            }
        }

        // Check if the last substring is the longest
        if (currentLength > maxLength) {
            maxLength = currentLength;  // Update the maximum length
            start = str.length() - maxLength;  // Update the starting index
        }

        return new int[]{start, maxLength};
    }
-------------------------------------------------------------------------------------------------
12. Given the path of a Robot which can move only in up, down, left and right (denoted by U,L,D,R) and the starting position of the robot as (0,0), 
we need to print the final position with respect to the starting position after the robot travels the path. For example,
Path = "UUDD", final position = (0,0)
Path = "UULL", final position = (-2,2)
Examples:
Given (0,0) and String - "UUU"
    Answer : (0,3)
Given (0,0) and String - "URRDDL"
    Answer : (1,-1)
Additional testcase : String - "DOWN UP 2xRIGHT DOWN 3xLEFT"
    Answer : (-1,-1)
	
/**
 * Function to find the final position of the robot after executing a sequence of movements.
 * 
 * @param move The sequence of movements as a string (U for up, D for down, L for left, R for right).
 * @return The final position of the robot as a formatted string (x, y).
 */
static String finalPosition(String move)
{
	int l = move.length(); // Get the length of the movement string
	int countUp = 0, countDown = 0; // Initialize counters for up and down movements
	int countLeft = 0, countRight = 0; // Initialize counters for left and right movements

	// Traverse the instruction string 'move'
	for (int i = 0; i < l; i++)
	{
		// For each movement, increment its respective counter
		if (move.charAt(i) == 'U')
			countUp++;
		else if (move.charAt(i) == 'D')
			countDown++;
		else if (move.charAt(i) == 'L')
			countLeft++;
		else if (move.charAt(i) == 'R')
			countRight++;
	}

	// Calculate the final position of the robot
	int finalX = countRight - countLeft;
	int finalY = countUp - countDown;

	// Return the final position as a formatted string
	return "(" + finalX + ", " + finalY + ")";
}
-------------------------------------------------------------------------------------------------
13. An English pangram is a sentence that contains all 26 letters of the English alphabet. Given a sentence, find the missing alphabets while ignoring the case. 
Print the missing characters in alphabetical order, which are required to make it a pangram.
For example:
- "The Quick Brown Fox Jumped Over The Lazy Dog": //Empty string as it is a Pangram
- "Quick Brown Fox Jumped Over The Lazy Dog": "eht"
https://www.geeksforgeeks.org/pangram-checking/

// A Java Program to check if the given
// string is a pangram or not
import java.util.*;

public class Gfg {

	// Returns true if the string is pangram else false
	static boolean checkPangram(String str) {
		// Initialize a set of characters
		Set<Character> set = new HashSet<>();

		for (char ch : str.toCharArray()) {
			// If the character is already
			// a lowercase character
			if (ch >= 'a' && ch <= 'z')
				set.add(ch);

			// In case of a uppercase character
			if (ch >= 'A' && ch <= 'Z') {
				// convert to lowercase
				ch = Character.toLowerCase(ch);
				set.add(ch);
			}
		}

		// check if the size is 26 or not
		return set.size() == 26;
	}

	// Driver Program to test above functions
	public static void main(String[] args) {
		String str = "The quick brown fox jumps over the lazy dog";
		if (checkPangram(str))
			System.out.println("It is a Pangram");
		else
			System.out.println("It is Not a Pangram");
	}
}
--------------------------------------
import java.util.*;

public class PangramMissingAlphabets {
    public static void main(String[] args) {
        String sentence = "The quick brown fox jumps over the lazy dog";
        List<Character> missingChars = findMissingAlphabets(sentence);
        System.out.println("Missing alphabets: " + missingChars);
    }

    public static List<Character> findMissingAlphabets(String sentence) {
        // Convert the sentence to lowercase to ignore case
        sentence = sentence.toLowerCase();

        // Set to store the present alphabets
        Set<Character> presentChars = new HashSet<>();
        for (char ch : sentence.toCharArray()) {
            // Filter out only alphabets
            if (Character.isLetter(ch)) {
                presentChars.add(ch);
            }
        }

        // List to store the missing alphabets
        List<Character> missingChars = new ArrayList<>();
        // Iterate through all the alphabets (a-z)
        for (char ch = 'a'; ch <= 'z'; ch++) {
            // If the current alphabet is not present, add it to the missingChars list
            if (!presentChars.contains(ch)) {
                missingChars.add(ch);
            }
        }

        return missingChars;
    }
}

-------------------------------------------------------------------------------------------------
14.Input: aabbbccb
Output: a2b3c2b1
https://www.geeksforgeeks.org/run-length-encoding/
https://www.tutorialspoint.com/program-to-perform-string-compression-in-python

public static String compress(String input) {
	// If input is null or empty, return an empty string
	if (input == null || input.length() == 0) {
		return "";
	}

	// StringBuilder to store the compressed string
	StringBuilder compressedString = new StringBuilder();
	// Map to store the count of each character
	Map<Character, Integer> charCountMap = new HashMap<>();

	// Iterate through each character in the input string
	for (int i = 0; i < input.length(); i++) {
		char currentChar = input.charAt(i);

		// Update character count in the map
		charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

		// Check if the next character is different or it's the last character
		if (i + 1 == input.length() || input.charAt(i + 1) != currentChar) {
			// Append the current character to the compressed string
			compressedString.append(currentChar);

			// Append the count of repetitions if greater than 1
			int count = charCountMap.get(currentChar);
			if (count >= 1) {
				compressedString.append(count);
			}

			// Clear the count for the current character
			charCountMap.put(currentChar, 0);
		}
	}

	// Return the compressed string
	return compressedString.toString();
}
-------------------------------------------------------------------------------------------------
15.Check if the input is a power of 10. We also need to check for negative powers.
Solution
For positive powers divide by 10 till we reach 1
For negative powers multiply by 10 till we reach 1
Java solution : https://www.javatpoint.com/power-of-a-number-in-java

public class PowerOfTenChecker {
    
    /**
     * Function to check if a number is a power of 10.
     * 
     * @param num The number to check.
     * @return True if the number is a power of 10, false otherwise.
     */
    public static boolean isPowerOfTen(int num) {
        // Check if the number is non-positive
        if (num <= 0) {
            return false;
        }

        // Keep dividing the number by 10 until it becomes 1 or not divisible by 10
        while (num % 10 == 0) {
            num /= 10;
        }

        // If the number becomes 1 after division, it is a power of 10
        return num == 1;
    }

    public static void main(String[] args) {
        int num1 = 100;
        int num2 = 500;
        int num3 = 1000;
        int num4 = 12345;

        // Test cases
        System.out.println(num1 + " is power of 10? " + isPowerOfTen(num1)); // Expected: true
        System.out.println(num2 + " is power of 10? " + isPowerOfTen(num2)); // Expected: false
        System.out.println(num3 + " is power of 10? " + isPowerOfTen(num3)); // Expected: true
        System.out.println(num4 + " is power of 10? " + isPowerOfTen(num4)); // Expected: false
    }
}
--------------------------------------
public class PowerOfTenChecker {
    public static void main(String[] args) {
        // Example input
        double num = 0.001;
        
        // Call the checkPowerOfTen method and print the result
        boolean isPowerOfTen = checkPowerOfTen(num);
        System.out.println(num + " is a power of 10: " + isPowerOfTen);
    }

    // Method to check if the input is a power of 10 or a negative power of 10
    public static boolean checkPowerOfTen(double num) {
        // Handle edge case where num is 0
        if (num == 0) {
            return false;
        }

        // Check for positive powers of 10
        while (num > 1) {
            // If num is not divisible by 10, it's not a power of 10
            if (num % 10 != 0) {
                return false;
            }
            // Divide num by 10 to continue checking
            num /= 10;
        }

        // Check for negative powers of 10
        while (num < 1) {
            // If num is 0.1, it's a negative power of 10
            if (num == 0.1) {
                return true;
            }
            // Multiply num by 10 to continue checking
            num *= 10;
        }

        // If neither positive nor negative powers of 10 are found, return false
        return false;
    }
}

-------------------------------------------------------------------------------------------------
16.Input: [1,2,3,4]
Output: [24,12,8,6]
Given an input array, return an array where ith element is a product of all the numbers apart from the number at i without using division
https://www.geeksforgeeks.org/a-product-array-puzzle/

public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n]; // Initialize the result array to store products

        // Calculate the product of all elements to the left of each element
        int leftProduct = 1; // Initialize leftProduct to 1
        for (int i = 0; i < n; i++) {
            result[i] = leftProduct; // Store leftProduct in result array
            leftProduct *= nums[i]; // Update leftProduct by multiplying with current element
        }

        // Calculate the product of all elements to the right of each element and multiply it with the corresponding left product
        int rightProduct = 1; // Initialize rightProduct to 1
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct; // Multiply current result with rightProduct
            rightProduct *= nums[i]; // Update rightProduct by multiplying with current element
        }

        return result; // Return the result array containing products except self for each element
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        System.out.println("Output: " + Arrays.toString(result)); // Output: [24, 12, 8, 6]
    }
}
-------------------------------------------------------------------------------------------------
17.Given an array Array[8,15,4,11,9,13,3]
Print the time to buy the stock and sell the stock so that we can have maximum profit
output:
buy = 4
sell = 13
profit = 9
https://www.geeksforgeeks.org/best-time-to-buy-and-sell-stock/
// Java code for the above approach
class GFG {
	static int maxProfit(int prices[], int n)
	{
		int buy = prices[0], max_profit = 0;
		for (int i = 1; i < n; i++) {

			// Checking for lower buy value
			if (buy > prices[i])
				buy = prices[i];

			// Checking for higher profit
			else if (prices[i] - buy > max_profit)
				max_profit = prices[i] - buy;
		}
		return max_profit;
	}

	// Driver Code
	public static void main(String args[])
	{
		int prices[] = { 7, 1, 5, 6, 4 };
		int n = prices.length;
		int max_profit = maxProfit(prices, n);
		System.out.println(max_profit);
	}
}
-------------------------------------------------------------------------------------------------
18. Find the minimum number of chocolates we need to distribute in a class.
● Topper of the class will always have highest number of chocolates.
● Adjacent rank will have more chocolates if the rank is higher in a queue.
● Every student will have minimum 1 chocolate
For example: students = [4,1,2,3] should have chocolates like [1,3,2,1] respectively. Return the integer value with the minimum number of chocolates we need.
https://www.geeksforgeeks.org/minimum-number-of-candies-required-to-distribute-among-children-based-on-given-conditions/

public class MinimumChocolates {

    public static int minChocolates(int[] students) {
        int n = students.length;
        int[] chocolates = new int[n];
        
        // Initialize chocolates with minimum 1 for each student
        for (int i = 0; i < n; i++) {
            chocolates[i] = 1;
        }

        // Assign additional chocolates to students with higher ranks
        for (int i = 1; i < n; i++) {
            if (students[i] > students[i - 1]) {
                chocolates[i] = chocolates[i - 1] + 1;
            }
        }

        // Reverse traversal to handle adjacent ranks with higher chocolates
        for (int i = n - 2; i >= 0; i--) {
            if (students[i] > students[i + 1]) {
                chocolates[i] = Math.max(chocolates[i], chocolates[i + 1] + 1);
            }
        }

        // Calculate total chocolates needed
        int totalChocolates = 0;
        for (int i = 0; i < n; i++) {
            totalChocolates += chocolates[i];
        }

        return totalChocolates;
    }

    public static void main(String[] args) {
        int[] students = {4, 1, 2, 3};
        System.out.println("Minimum number of chocolates needed: " + minChocolates(students)); // Output: 7
    }
}
-------------------------------------------------------------------------------------------------
19. RemoveTriplets 
An array of integers and need to remove triplets (three consecutive same integers).
Input: [1,1,1,2,2,3,3,3,4], output: [2,2,4]

package array;

import java.util.Stack;

public class RemoveTriplets {
    public static void main(String[] args) {
        int[] input = {0,1, 1, 1, 2, 2, 3, 3, 3, 2, 0, 0, 4, 3};
        int[] result = removeTriplets(input);
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Expected output: 2 2 4
    }

    public static int[] removeTriplets(int[] nums) {
        // Stack to keep track of numbers and their counts
        Stack<Pair> stack = new Stack<>();

        // Traverse each number in the input array
        for (int num : nums) {
            if (!stack.isEmpty() && stack.peek().number == num) {
                // If the stack is not empty and the top of the stack has the same number,
                // increment the count of that number
                stack.peek().count++;
            } else {
                // Otherwise, push a new Pair with the number and a count of 1 onto the stack
                stack.push(new Pair(num, 1));
            }

            // If the count of the number at the top of the stack reaches 3,
            // pop it from the stack (remove the triplet)
            if (!stack.isEmpty() && stack.peek().count == 3) {
                stack.pop();
            }
        }

        // Calculate the size of the result array by summing up the counts of all remaining pairs in the stack
        int size = 0;
        for (Pair pair : stack) {
            size += pair.count;
        }

        // Create the result array
        int[] result = new int[size];
        int index = 0;

        // Fill the result array with numbers from the stack,
        // repeating each number according to its count
        for (Pair pair : stack) {
            for (int i = 0; i < pair.count; i++) {
                result[index++] = pair.number;
            }
        }

        // Return the final array with all triplets removed
        return result;
    }

    // Helper class to store a number and its count
    private static class Pair {
        int number;
        int count;

        Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}
-------------------------------------------------------------------------------------------------
20. You have been given a non-empty grid consisting of only 0s and 1s. You have to find the number of islands in the given grid.

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;
        int numIslands = 0;

        // Perform DFS traversal on each cell of the grid
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    // Depth-First Search (DFS) traversal
    private void dfs(char[][] grid, int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Base cases for out of bounds or cell not part of island
        if (row < 0 || col < 0 || row >= numRows || col >= numCols || grid[row][col] == '0') {
            return;
        }

        // Mark current cell as visited
        grid[row][col] = '0';

        // Perform DFS on adjacent cells
        dfs(grid, row + 1, col); // Down
        dfs(grid, row - 1, col); // Up
        dfs(grid, row, col + 1); // Right
        dfs(grid, row, col - 1); // Left
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        NumberOfIslands solution = new NumberOfIslands();
        int numIslands = solution.numIslands(grid);
        System.out.println("Number of islands: " + numIslands);
    }
}
-------------------------------------------------------------------------------------------------
***21. Given a binary array of electric poles size n x m. Where 1 represents position of electric pole that needs to be painted.
You can start on any cell and can travel in any adjacent cell(L,R,U,D). There is not cost in travelling across rows, 
however a 'UNIT' cost is added when travelling from 1 coloumn to another. Find out the minimum cost required to paint all required poles.

Certainly! The problem statement describes a scenario where we have a grid representing electric poles. Each cell of the grid can either be empty or contain an electric pole 
that needs to be painted. We're tasked with finding the minimum cost required to paint all the poles.

Here are the specifics of the problem:

- We're given a binary array representing the grid of electric poles. In this array, 1 represents a position where an electric pole needs to be painted, 
and 0 represents an empty position.
- We can start at any cell of the grid and move to any adjacent cell (left, right, up, down).
- There is no cost associated with moving across rows (moving up or down within the same column).
- However, there is a "UNIT" cost associated with moving from one column to another.

Our goal is to find the minimum cost required to paint all the required poles. To achieve this, we need to find the shortest path from the starting position to each of the electric poles 
that need to be painted, considering the associated costs of moving between columns.

In summary, the problem involves finding the minimum cost path through the grid while taking into account the specific costs associated with moving between adjacent columns.

import java.util.ArrayDeque;
import java.util.Queue;

public class MinimumPaintingCost {
    
    // Define a class to represent coordinates in the grid along with their cost
    static class Point {
        int x, y, cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    // Function to find the minimum cost required to paint all required poles
    public static int minimumCost(int[][] poles) {
        int n = poles.length;
        int m = poles[0].length;
        boolean[][] visited = new boolean[n][m]; // To keep track of visited cells
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible directions: up, down, left, right
        
        Queue<Point> queue = new ArrayDeque<>(); // Queue for BFS
        
        boolean foundStart = false; // Flag to track if starting position is found

        // Find the starting position
        for (int i = 0; i < n; i++) {
            if (foundStart) break; // If starting position already found, break out of loop
            for (int j = 0; j < m; j++) {
                if (poles[i][j] == 1) {
                    queue.offer(new Point(i, j, 0)); // Start BFS from this position
                    visited[i][j] = true;
                    foundStart = true; // Set flag to true since starting position found
                    break; // Break out of inner loop
                }
            }
        }

        int minCost = Integer.MAX_VALUE; // Initialize minimum cost

        // Perform BFS
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // Check if this position is a pole
            if (poles[current.x][current.y] == 1) {
                minCost = Math.min(minCost, current.cost); // Update minimum cost
            }

            // Explore adjacent cells
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                
                // Check if the new position is within the grid and not visited yet
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && !visited[newX][newY]) {
                    visited[newX][newY] = true; // Mark the new position as visited
                    int newCost = current.cost + (newY != current.y ? 1 : 0); // Calculate the new cost
                    
                    // Add the new position to the queue
                    queue.offer(new Point(newX, newY, newCost));
                }
            }
        }

        return minCost; // Return the minimum cost required to paint all required poles
    }

    public static void main(String[] args) {
        // Example input array representing the grid of electric poles
        int[][] poles = {
            {0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 0, 1, 0}
        };

        // Call the function to find the minimum cost and print the result
        System.out.println("Minimum cost required to paint all required poles: " + minimumCost(poles));
    }
}

Using dfs:
public class MinimumPaintingCost {
    
    // Define a class to represent coordinates in the grid along with their cost
    static class Point {
        int x, y, cost;

        public Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    // Function to find the minimum cost required to paint all required poles
    public static int minimumCost(int[][] poles) {
        int n = poles.length;
        int m = poles[0].length;
        boolean[][] visited = new boolean[n][m]; // To keep track of visited cells
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Possible directions: up, down, left, right
        
        int minCost = Integer.MAX_VALUE; // Initialize minimum cost

        // Perform DFS from all cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (poles[i][j] == 1) {
                    // Start DFS from this cell and update the minimum cost
                    minCost = Math.min(minCost, dfs(poles, visited, i, j, directions, 0));
                }
            }
        }

        return minCost; // Return the minimum cost required to paint all required poles
    }

    // Depth-First Search (DFS) function
    public static int dfs(int[][] poles, boolean[][] visited, int x, int y, int[][] directions, int cost) {
        visited[x][y] = true; // Mark the current cell as visited
        
        int minCost = Integer.MAX_VALUE; // Initialize minimum cost

        if (poles[x][y] == 1) {
            // If the current cell contains a pole, update the minimum cost
            minCost = Math.min(minCost, cost);
        }

        // Explore all possible directions (up, down, left, right)
        for (int[] dir : directions) {
            int newX = x + dir[0]; // Calculate new row index
            int newY = y + dir[1]; // Calculate new column index
            
            // Check if the new position is within the grid and not visited yet
            if (newX >= 0 && newX < poles.length && newY >= 0 && newY < poles[0].length && !visited[newX][newY]) {
                int newCost = cost + (newY != y ? 1 : 0); // Calculate the new cost (add 1 if moving to a new column)
                
                // Recursively call DFS for the new position and update the minimum cost
                minCost = Math.min(minCost, dfs(poles, visited, newX, newY, directions, newCost));
            }
        }

        visited[x][y] = false; // Backtrack: Mark the current cell as not visited
        return minCost; // Return the minimum cost found so far
    }

    public static void main(String[] args) {
        // Example input array representing the grid of electric poles
        int[][] poles = {
            {0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 0, 1, 0}
        };

        // Call the function to find the minimum cost and print the result
        System.out.println("Minimum cost required to paint all required poles: " + minimumCost(poles));
    }
}
-------------------------------------------------------------------------------------------------
***22. You are an avid rock collector who lives in southern California. Some rare and desirable rocks just became available in New York, 
so you are planning a cross-country road trip. There are several other rare rocks that you could pick up along the way. 
You have been given a grid filled with numbers, representing the number of rare rocks available in various cities across the country. 
Your objective is to find the optimal path from So_Cal to New_York that would allow you to accumulate the most rocks along the way. 
Note: You can only travel either north (up) or east (right).
2) Consider adding some additional tests in doTestsPass().
3) Implement optimalPath() correctly.
4) Here is an example:
3) ** ^
4) ** {{0,0,0,0,5}, New_York (finish) N
5) ** {0,1,1,1,0}, < W E >
6) ** So_Cal (start) {2,0,0,0,0}} S
7) **

The total for this example would be 10 (2+0+1+1+1+0+5).

/**
 * This class provides a solution to find the optimal path in a 2D grid
 * from the bottom-left corner to the top-right corner, while maximizing
 * the sum of numbers along the path.
 */
public class Solution {
    
    /**
     * Finds the optimal path in the grid by calling the helper method.
     * @param grid The 2D grid representing the numbers.
     * @return The maximum sum of numbers along the optimal path.
     */
    public static int optimalPath(int[][] grid) {
        return optimalPathHelper(grid, grid.length - 1, 0, 0);
    }

    /**
     * Recursive helper method to find the maximum sum of numbers along
     * the optimal path in the grid.
     * @param grid The 2D grid representing the numbers.
     * @param row The current row index.
     * @param col The current column index.
     * @param result The sum of numbers along the current path.
     * @return The maximum sum of numbers along the optimal path.
     */
    public static int optimalPathHelper(int[][] grid, int row, int col, Integer result) {
        // Base case: out of bounds
        if (row < 0 || col == grid[0].length) return 0;
        
        // Base case: reached the top-right corner
        if (row == 0 && col == grid[0].length - 1) return result + grid[row][col];
        
        // Recursively explore two possible moves: up or right
        return Math.max(
            optimalPathHelper(grid, row - 1, col, result + grid[row][col]), // Move up
            optimalPathHelper(grid, row, col + 1, result + grid[row][col])  // Move right
        );
    }
    
    /**
     * Tests the solution with various input grids.
     * @return true if all tests pass, false otherwise.
     */
   // public static boolean doTestsPass() {
        //return optimalPath(new int[][]{{0, 0, 0, 0, 5}, {0, 1, 1, 1, 0}, {2, 0, 0, 0, 0}}) == 10
            //&& optimalPath(new int[][]{{1, 3, 2, 0, 2, 1, 8}, {3, 4, 1, 2, 0, 1, 1},
                //{1, 1, 1, 2, 3, 2, 1}, {1, 0, 1, 1, 4, 2, 1}}) == 25
            //&& optimalPath(new int[][]{}) == 0;
   // /}
    
    public static void main(String[] args) {
    // Test the solution with the provided input grids
    System.out.println(optimalPath(new int[][]{{0, 0, 0, 0, 5}, {0, 1, 1, 1, 0}, {2, 0, 0, 0, 0}})); // Output: 10
    System.out.println(optimalPath(new int[][]{{1, 3, 2, 0, 2, 1, 8}, {3, 4, 1, 2, 0, 1, 1},
        {1, 1, 1, 2, 3, 2, 1}, {1, 0, 1, 1, 4, 2, 1}})); // Output: 25
    System.out.println(optimalPath(new int[][]{})); // Output: 0
}
    
    /**
     * Main method to run the tests and print the result.
     * @param args The command-line arguments (unused).
     */
    //public static void main(String[] args) {
      //  System.out.println(doTestsPass() ? "All tests pass" : "Tests fail.");
    //}
//}
-------------------------------------------------------------------------------------------------
***23. Given a 2D matrix of a field having gold coins at multiple (x,y) coordinates and given the starting and ending coordinates,
we need to find the optimal path to collect maximum gold. We can traverse only in the up and right directions, and START and END coordinates are fixed, 
i.e., Start = (num_of_rows,1) and End = (1, num_of_columns) taking 1 based indexing.
For example,
Input =. [0 0 2 1 2] END
[0 1 1 1 1]
START [2 0 0 0 0]
Output: 9

/**
 * This class provides a solution to find the optimal path in a 2D grid
 * from the bottom-left corner to the top-right corner, while maximizing
 * the sum of numbers along the path.
 */
public class Solution {
    
    /**
     * Finds the optimal path in the grid by calling the helper method.
     * @param grid The 2D grid representing the numbers.
     * @return The maximum sum of numbers along the optimal path.
     */
    public static int optimalPath(int[][] grid) {
        return optimalPathHelper(grid, grid.length - 1, 0, 0);
    }

    /**
     * Recursive helper method to find the maximum sum of numbers along
     * the optimal path in the grid.
     * @param grid The 2D grid representing the numbers.
     * @param row The current row index.
     * @param col The current column index.
     * @param result The sum of numbers along the current path.
     * @return The maximum sum of numbers along the optimal path.
     */
    public static int optimalPathHelper(int[][] grid, int row, int col, Integer result) {
        // Base case: out of bounds
        if (row < 0 || col == grid[0].length) return 0;
        
        // Base case: reached the top-right corner
        if (row == 0 && col == grid[0].length - 1) return result + grid[row][col];
        
        // Recursively explore two possible moves: up or right
        return Math.max(
            optimalPathHelper(grid, row - 1, col, result + grid[row][col]), // Move up
            optimalPathHelper(grid, row, col + 1, result + grid[row][col])  // Move right
        );
    }
    
    /**
     * Tests the solution with various input grids.
     * @return true if all tests pass, false otherwise.
     */
   // public static boolean doTestsPass() {
        //return optimalPath(new int[][]{{0, 0, 0, 0, 5}, {0, 1, 1, 1, 0}, {2, 0, 0, 0, 0}}) == 10
            //&& optimalPath(new int[][]{{1, 3, 2, 0, 2, 1, 8}, {3, 4, 1, 2, 0, 1, 1},
                //{1, 1, 1, 2, 3, 2, 1}, {1, 0, 1, 1, 4, 2, 1}}) == 25
            //&& optimalPath(new int[][]{}) == 0;
   // /}
    
    public static void main(String[] args) {
    // Test the solution with the provided input grids
    System.out.println(optimalPath(new int[][]{{0, 0, 0, 0, 5}, {0, 1, 1, 1, 0}, {2, 0, 0, 0, 0}})); // Output: 10
    System.out.println(optimalPath(new int[][]{{1, 3, 2, 0, 2, 1, 8}, {3, 4, 1, 2, 0, 1, 1},
        {1, 1, 1, 2, 3, 2, 1}, {1, 0, 1, 1, 4, 2, 1}})); // Output: 25
    System.out.println(optimalPath(new int[][]{})); // Output: 0
}
    
    /**
     * Main method to run the tests and print the result.
     * @param args The command-line arguments (unused).
     */
    //public static void main(String[] args) {
      //  System.out.println(doTestsPass() ? "All tests pass" : "Tests fail.");
    //}
//}
-------------------------------------------------------------------------------------------------
***24. Given an array of integers like {1,2,3,4} and number n=3 and k=2
You need to find k numbers from the array whose XOR operation with n is largest.
● Brute force
● Optimal approach


import java.util.*;

public class tUf {

    public static int subarraysWithXorK(int []a, int k) {
        int n = a.length; //size of the given array.
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>(); //declaring the map.
        mpp.put(xr, 1); //setting the value of 0.
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            // prefix XOR till index i:
            xr = xr ^ a[i];

            //By formula: x = xr^k:
            int x = xr ^ k;

            // add the occurrence of xr^k
            // to the count:
            if (mpp.containsKey(x)) {
                cnt += mpp.get(x);
            }

            // Insert the prefix xor till index i
            // into the map:
            if (mpp.containsKey(xr)) {
                mpp.put(xr, mpp.get(xr) + 1);
            } else {
                mpp.put(xr, 1);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;
        int ans = subarraysWithXorK(a, k);
        System.out.println("The number of subarrays with XOR k is: " + ans);
    }
}
-------------------------------------------------------------------------------------------------
***25.Find the median of 2 sorted arrays in the most optimal way.
https://www.geeksforgeeks.org/median-of-two-sorted-arrays/
Median of two Sorted Arrays of Different Sizes - GeeksforGeeks
Example:
arr1[] = {2, 3, 5, 8}
arr2[] = {10, 12, 14, 16, 18, 20}
//Return 11 as the median

public class MedianOfSortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {2, 3, 5, 8};
        int[] arr2 = {10, 12, 14, 16, 18, 20};

        double median = findMedian(arr1, arr2);
        System.out.println("Median of the two sorted arrays: " + median);
    }

    public static double findMedian(int[] arr1, int[] arr2) {
        int totalLength = arr1.length + arr2.length;
        int[] mergedArray = new int[totalLength];

        int i = 0, j = 0, k = 0;

        // Merge the two sorted arrays
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        // Copy remaining elements from arr1
        while (i < arr1.length) {
            mergedArray[k++] = arr1[i++];
        }

        // Copy remaining elements from arr2
        while (j < arr2.length) {
            mergedArray[k++] = arr2[j++];
        }

        // Determine the median based on the length of the merged array
        if (totalLength % 2 == 0) {
            // If totalLength is even, take the average of the middle two elements
            int mid = totalLength / 2;
            return (mergedArray[mid - 1] + mergedArray[mid]) / 2.0;
        } else {
            // If totalLength is odd, return the middle element
            return mergedArray[totalLength / 2];
        }
    }
}
-------------------------------------------------------------------------------------------------
***26.Given a set of time intervals in any order, the task is to merge all overlapping intervals into
one and output the result which should have only mutually exclusive intervals.
Example:
Input: Intervals = {{1,3},{2,4},{6,8},{9,10}}
Output: {{1, 4}, {6, 8}, {9, 10}}
Explanation: Given intervals: [1,3],[2,4],[6,8],[9,10], we have only two overlapping intervals
here,[1,3] and [2,4]. Therefore we will merge these two and return [1,4],[6,8], [9,10].}
https://www.geeksforgeeks.org/merging-intervals/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        // Sort intervals based on their start times
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] nextInterval = intervals[i];
            
            // If current interval overlaps with the next interval, merge them
            if (currentInterval[1] >= nextInterval[0]) {
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                // If there's no overlap, add the current interval to the result and update currentInterval
                mergedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }
        }

        // Add the last interval to the result
        mergedIntervals.add(currentInterval);

        // Convert list of merged intervals to array and return
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,4},{6,8},{9,10}};
        int[][] mergedIntervals = mergeIntervals(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}

import java.util.*;

public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        // Sort the intervals based on the start time
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        List<int[]> mergedIntervals = new ArrayList<>();
        int[] currentInterval = intervals[0]; // Initialize with the first interval

        // Iterate through the sorted intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            // Check if the current interval overlaps with the next interval
            if (interval[0] <= currentInterval[1]) {
                // Merge the intervals by updating the end time of the current interval
                currentInterval[1] = Math.max(currentInterval[1], interval[1]);
            } else {
                // If there is no overlap, add the current interval to the result and update the current interval
                mergedIntervals.add(currentInterval);
                currentInterval = interval;
            }
        }

        // Add the last interval to the result
        mergedIntervals.add(currentInterval);

        // Convert the list of merged intervals to a 2D array
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 4}, {6, 8}, {9, 10}};
        int[][] mergedIntervals = merge(intervals);
        System.out.println("Output:");
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
-------------------------------------------------------------------------------------------------
***27. Find the largest sum subtree in a binary tree(The subtree can have both neg and
positive numbers)
- Brute FOrce approach
- Best Case
- Modification-> If the tree is a non-binary tree then how we can get the largest
sum.
- Modification-> Write Node implementation for the non-binary tree node

https://www.geeksforgeeks.org/find-largest-subtree-sum-tree/

- Brute FOrce approach
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class LargestSumSubtree {
    // Global variables to store the maximum sum found and its corresponding subtree root
    static int maxSum = Integer.MIN_VALUE;
    static TreeNode result = null;
    
    // Utility function to recursively calculate the sum of a subtree
    static int findSum(TreeNode root) {
        if (root == null) return 0;
        // Calculate the sum of the left subtree
        int leftSum = findSum(root.left);
        // Calculate the sum of the right subtree
        int rightSum = findSum(root.right);
        // Calculate the sum of the current subtree
        int subtreeSum = root.val + leftSum + rightSum;
        // Update the maximum sum and its corresponding subtree root if necessary
        if (subtreeSum > maxSum) {
            maxSum = subtreeSum;
            result = root;
        }
        // Return the sum of the current subtree
        return subtreeSum;
    }
    
    // Main function to find the largest sum subtree
    static TreeNode findLargestSumSubtree(TreeNode root) {
        if (root == null) return null;
        // Start the recursive calculation of subtree sums
        findSum(root);
        // Return the root of the subtree with the largest sum
        return result;
    }
    
    public static void main(String[] args) {
        // Example binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(3);
        // Find the root of the largest sum subtree
        TreeNode largestSubtree = findLargestSumSubtree(root);
        // Print the root value of the largest sum subtree
        System.out.println("The root of the largest sum subtree is: " + largestSubtree.val);
    }
}


- Best Case

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class LargestSumSubtree {
    // Global variables to store the maximum sum found and its corresponding subtree root
    static int maxSum = Integer.MIN_VALUE;
    static TreeNode result = null;
    
    // Utility function to recursively calculate the sum of a subtree
    static int findSum(TreeNode root) {
        if (root == null) return 0;
        // Calculate the sum of the left subtree
        int leftSum = findSum(root.left);
        // Calculate the sum of the right subtree
        int rightSum = findSum(root.right);
        // Calculate the sum of the current subtree
        int subtreeSum = root.val + leftSum + rightSum;
        // Update the maximum sum and its corresponding subtree root if necessary
        if (subtreeSum > maxSum) {
            maxSum = subtreeSum;
            result = root;
        }
        // Return the sum of the current subtree
        return subtreeSum;
    }
    
    // Main function to find the largest sum subtree
    static TreeNode findLargestSumSubtree(TreeNode root) {
        if (root == null) return null;
        // Start the recursive calculation of subtree sums
        findSum(root);
        // Return the root of the subtree with the largest sum
        return result;
    }
    
    public static void main(String[] args) {
        // Example binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(3);
        // Find the root of the largest sum subtree
        TreeNode largestSubtree = findLargestSumSubtree(root);
        // Print the root value of the largest sum subtree
        System.out.println("The root of the largest sum subtree is: " + largestSubtree.val);
    }
}

// Java program to find largest subtree
// sum in a given binary tree.
import java.io.*;

class GFG {

// Structure of a tree node.
static class Node {
    public int key;
    public Node left, right;
}

// Function to create new tree node.
static Node newNode(int key)
{
    Node temp = new Node();
    temp.key = key;
    temp.left = null;
    temp.right = null;
    return temp;
}

static int ans = Integer.MIN_VALUE;
static int dfs(Node root)
{
    if (root == null)
    return 0;
    if (root.left == null && root.right == null)
    return root.key;
    // check for every subtree in left
    int sumleft = dfs(root.left);
    // check for every subtree in right
    int sumright = dfs(root.right);
    // sum of all the nodes in the subtree from root
    // node
    int sumrootnode = sumleft + sumright + root.key;
    // temp max value of left and right subtree
    int tempmax = Math.max(sumleft, sumright);

    tempmax = Math.max(tempmax, sumrootnode);
    // update the answer from temp, ans
    ans = Math.max(ans, tempmax);

    return sumrootnode;
}

static int findLargestSubtreeSum(Node root)
{

    // check for the base conditions
    if (root == null)
    return 0;
    if (root.left == null && root.right == null)
    return root.key;
    // function call of dfs
    int x = dfs(root);
    // return the final answer
    return ans;
}

public static void main(String[] args)
{
    /*
                1
                / \
                / \
            -2 3
            / \ / \
            / \ / \
            4 5 -6 2
        */

    Node root = newNode(1);
    root.left = newNode(-2);
    root.right = newNode(3);
    root.left.left = newNode(4);
    root.left.right = newNode(5);
    root.right.left = newNode(-6);
    root.right.right = newNode(2);

    System.out.println(findLargestSubtreeSum(root));
}
}

// This code is contributed by lokesh.

- Modification-> If the tree is a non-binary tree then how we can get the largest
sum.
import java.util.*;

class TreeNode {
    int val;
    List<TreeNode> children;
    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class LargestSumSubtree {
    // Global variables to store the maximum sum found and its corresponding subtree root
    static int maxSum = Integer.MIN_VALUE;
    static TreeNode result = null;
    
    // Utility function to recursively calculate the sum of a subtree
    static int findSum(TreeNode root) {
        if (root == null) return 0;
        int subtreeSum = root.val;
        for (TreeNode child : root.children) {
            subtreeSum += findSum(child);
        }
        // Update the maximum sum and its corresponding subtree root if necessary
        if (subtreeSum > maxSum) {
            maxSum = subtreeSum;
            result = root;
        }
        // Return the sum of the current subtree
        return subtreeSum;
    }
    
    // Main function to find the largest sum subtree
    static TreeNode findLargestSumSubtree(TreeNode root) {
        if (root == null) return null;
        // Start the recursive calculation of subtree sums
        findSum(root);
        // Return the root of the subtree with the largest sum
        return result;
    }
    
    public static void main(String[] args) {
        // Example non-binary tree
        TreeNode root = new TreeNode(1);
        TreeNode child1 = new TreeNode(2);
        TreeNode child2 = new TreeNode(3);
        TreeNode child3 = new TreeNode(4);
        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);
        // Find the root of the largest sum subtree
        TreeNode largestSubtree = findLargestSumSubtree(root);
        // Print the root value of the largest sum subtree
        System.out.println("The root of the largest sum subtree is: " + largestSubtree.val);
    }
}

- Modification-> Write Node implementation for the non-binary tree node
class TreeNode {
    int val;
    List<TreeNode> children;
    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
-------------------------------------------------------------------------------------------------
***28. Problem Name is &&& Dist. Between Strings &&& PLEASE DO NOT REMOVE THIS LINE.

def shortestDistance(document, word1, word2):
""" Given two words returns the shortest distance between their two
midpoints in number of characters.

Words can appear multiple times in any order and should be case insensitive.

E.g. for the document="This is a sample document we just made up"

shortestDistance( document, "we", "just" ) == 4
shortestDistance( document, "is", "a" ) == 2.5
shortestDistance( document, "is", "not" ) == -1
"""

public class ShortestDistance {

    public static double shortestDistance(String document, String word1, String word2) {
        String[] words = document.toLowerCase().split("\\s+");
        int idx1 = -1, idx2 = -1;
        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1.toLowerCase())) {
                idx1 = i;
            } else if (words[i].equals(word2.toLowerCase())) {
                idx2 = i;
            }

            if (idx1 != -1 && idx2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(idx1 - idx2) + 1);
            }
        }

        if (minDistance == Double.POSITIVE_INFINITY) {
            return -1;
        }

        return minDistance % 2 == 0 ? minDistance / 2 : minDistance / 2 + 0.5;
    }

    public static void main(String[] args) {
        String document = "This is a sample document we just made up";
        System.out.println(shortestDistance(document, "we", "just"));  // Output: 4
        System.out.println(shortestDistance(document, "is", "a"));     // Output: 2.5
        System.out.println(shortestDistance(document, "is", "not"));   // Output: -1
    }
}
-------------------------------------------------------------------------------------------------
***29.What data structure can be used to store the ip addresses so that we can use wild card search
Ip address: [192.35.10.1, 192.35.40.7, 10.1.1.1, 192.75.1.6, 84....]
Search = 192.*
output all IP starting with 192. if search was 1* then all ip starting with 1
-------------------------------------------------------------------------------------------------
***30.check if 2 strings are rotation of each other
https://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other/
-------------------------------------------------------------------------------------------------
31.Reverse a list in the most optimal way.
Python solution: https://www.geeksforgeeks.org/python-reversing-list/

Java solution
https://www.tutorialspoint.com/java-program-to-reverse-a-list#:~:text=Reverse%20Array%20Prin
ting%20By%20Using%20Collections.&text=reverse()%20Method-,Collections.,)%2C%20to%20
perform%20the%20code.
-------------------------------------------------------------------------------------------------
32. print all substrings for a given String of given substring size ? Follow up
question, handle all edge cases using Try-Catch and Exceptional handling ?
-------------------------------------------------------------------------------------------------
***33.given HashMap contains edges of Forest ( disconnected Tree ), find the Tree having
maximum number of nodes, if two Trees have same number of nodes return Tree having root
node value least.

-------------------------------------------------------------------------------------------------
***34.https://www.geeksforgeeks.org/design-a-data-structure-that-supports-insert-delete-search-and-getrandom-in-constant-time/

class TreeNode {
    int val;
    TreeNode left, right;
    
    // Constructor to initialize the node with a value
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
-------------------------------------------------------------------------------------------------
public class BinaryTree {
    TreeNode root;

    // Constructor to initialize an empty binary tree
    public BinaryTree() {
        this.root = null;
    }

    // Function to insert a value into the binary tree
    public void insert(int val) {
        this.root = insertRec(this.root, val);
    }

    // Recursive function to insert a value into the binary tree
    private TreeNode insertRec(TreeNode root, int val) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        // Otherwise, recur down the tree
        if (val < root.val) {
            root.left = insertRec(root.left, val);
        } else if (val > root.val) {
            root.right = insertRec(root.right, val);
        }

        // Return the unchanged node pointer
        return root;
    }

    // Function to print prime numbers in the binary tree
    public void printPrimeNumbers() {
        System.out.println("Prime numbers in the binary tree:");
        printPrimeRec(root);
    }

    // Recursive function to traverse the tree and print prime numbers
    private void printPrimeRec(TreeNode root) {
        if (root != null) {
            // Check if the current value is prime
            if (isPrime(root.val)) {
                System.out.print(root.val + " ");
            }
            // Recur for left and right subtrees
            printPrimeRec(root.left);
            printPrimeRec(root.right);
        }
    }

    // Function to check if a number is prime
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Create a binary tree
        BinaryTree tree = new BinaryTree();

        // Insert values into the binary tree
        tree.insert(10);
        tree.insert(3);
        tree.insert(17);
        tree.insert(4);
        tree.insert(13);
        tree.insert(19);

        // Print prime numbers in the binary tree
        tree.printPrimeNumbers();
    }
}
---------------------
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    public static void main(String[] args) {
        // Create the root node
        TreeNode root = new TreeNode(1);

        // Insert nodes level by level
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Access nodes using expressions like node.left.right
        System.out.println("Node at root.left.right: " + root.left.right.val);
        System.out.println("Node at root.right.left: " + root.right.left.val);
    }
}
-------------------------------------------------------------------------------------------------
public class SecondSmallestNumber {
    public static int findSecondSmallest(int[] arr) {
        if (arr == null || arr.length < 2) {
            // Handle edge cases where array is null or contains less than 2 elements
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        // Initialize variables to store smallest and second smallest numbers
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        // Traverse the array
        for (int num : arr) {
            if (num < smallest) {
                // If current number is smaller than smallest, update both smallest and second smallest
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                // If current number is smaller than second smallest but not equal to smallest, update second smallest
                secondSmallest = num;
            }
        }

        return secondSmallest;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 7, 2, 5};
        int secondSmallest = findSecondSmallest(arr);
        System.out.println("Second smallest number: " + secondSmallest);
    }
}
-------------------------------------------------------------------------------------
[13:52, 6/6/2024] Sunny IIIT: import java.util.*;

public class OnCallSchedule {
    public static void main(String[] args) {
        List<Rotation> rotations = Arrays.asList(
                new Rotation("Abby", 1, 10),
                new Rotation("Ben", 5, 7),
                new Rotation("Carla", 6, 12),
                new Rotation("David", 15, 17),
                new Rotation("Abby", 8, 13)
        );

        List<TimeInterval> result = getOnCallSchedule(rotations);
        for (TimeInterval interval : result) {
            System.out.println(interval.start + " " + interval.end + " " + interval.names);
        }
    }

    public static List<TimeInterval> getOnCallSchedule(List<Rotation> rotations) {
        // Collect all unique times
        Set<Integer> times = new TreeSet<>();
        for (Rotation rotation : rotations) {
            times.add(rotation.start);
            times.add(rotation.end);
        }

        // Convert set to sorted list
        List<Integer> sortedTimes = new ArrayList<>(times);
        List<TimeInterval> result = new ArrayList<>();

        // Iterate over time intervals and determine on-call people
        for (int i = 0; i < sortedTimes.size() - 1; i++) {
            int start = sortedTimes.get(i);
            int end = sortedTimes.get(i + 1);
            Set<String> onCallPeople = new HashSet<>();

            for (Rotation rotation : rotations) {
                if (rotation.start < end && rotation.end > start) {
                    onCallPeople.add(rotation.name);
                }
            }

            if (!onCallPeople.isEmpty()) {
                result.add(new TimeInterval(start, end, new ArrayList<>(onCallPeople)));
            }
        }

        return result;
    }
}

class Rotation {
    String name;
    int start;
    int end;

    Rotation(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class TimeInterval {
    int start;
    int end;
    List<String> names;

    TimeInterval(int start, int end, List<String> names) {
        this.start = start;
        this.end = end;
        this.names = names;
    }
}
Explanation:
Collect Unique Times:

We use a TreeSet to collect and sort all unique start and end times from the rotations.
Sort and Convert Times:

Convert the TreeSet to a sorted list to easily iterate through each time interval.
Determine On-Call People:

For each interval (defined by consecutive times in the sorted list), check which rotations are active.
A rotation is considered active during an interval if its start time is before the interval end and its end time is after the interval start.
Collect names of active rotations for each interval.
Store and Print Results:

Store the results in a List<TimeInterval>.
Print the intervals along with the names of the people on call.
This method ensures that all overlapping periods are handled correctly, and it outputs the desired non-overlapping intervals with the corresponding on-call people.