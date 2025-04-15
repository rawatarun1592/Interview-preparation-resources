loop in linked list
Question related to balancing parenthesis
What are the different way of reversing a string.
*Reverse a Linked List
Reverse a Linked List in k- groups of given size
Sort an array of 0s, 1s and 2s
**Remove loop in linked list.
ZigZag Tree Traversal
**Given a preorder traversal of a binary tree,you have to determine whether a BST is possible for that traversal or not
	boolean canRepresentBST(int pre[], int n) { 
        // Create an empty stack 
        Stack<Integer> s = new Stack<Integer>(); 
   
        // Initialize current root as minimum possible 
        // value 
        int root = Integer.MIN_VALUE; 
   
        // Traverse given array 
        for (int i = 0; i < n; i++) { 
            // If we find a node who is on right side 
            // and smaller than root, return false 
            if (pre[i] < root) { 
                return false; 
            } 
   
            // If pre[i] is in right subtree of stack top, 
            // Keep removing items smaller than pre[i] 
            // and make the last removed item as new 
            // root. 
            while (!s.empty() && s.peek() < pre[i]) { 
                root = s.peek(); 
                s.pop(); 
            } 
   
            // At this point either stack is empty or 
            // pre[i] is smaller than root, push pre[i] 
            s.push(pre[i]); 
        } 
        return true; 
    }
Find next greater element.
	public static long[] nextLargerElement(long[] arr, int n)
    { 
        Stack<Long> stack = new Stack<Long>();
        
        long[] ans = new long[n];
        for(int i = n-1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= arr[i]) {
                    stack.pop();
            } 
            if(stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

Add two numbers which are represented by the Linked List
	class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}
	}

	public class AddTwoNumbersLinkedList {
		public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			ListNode dummyHead = new ListNode(0);
			ListNode current = dummyHead;
			int carry = 0;
			
			while (l1 != null || l2 != null) {
				int x = (l1 != null) ? l1.val : 0;
				int y = (l2 != null) ? l2.val : 0;
				int sum = x + y + carry;
				carry = sum / 10;
				current.next = new ListNode(sum % 10);
				current = current.next;
				if (l1 != null) l1 = l1.next;
				if (l2 != null) l2 = l2.next;
			}
			
			if (carry > 0) {
				current.next = new ListNode(carry);
			}
			
			return dummyHead.next;
		}
		
		// Helper method to print the linked list
		public static void printList(ListNode head) {
			ListNode current = head;
			while (current != null) {
				System.out.print(current.val + " ");
				current = current.next;
			}
			System.out.println();
		}

		public static void main(String[] args) {
			// Example usage
			ListNode l1 = new ListNode(2);
			l1.next = new ListNode(4);
			l1.next.next = new ListNode(3);

			ListNode l2 = new ListNode(5);
			l2.next = new ListNode(6);
			l2.next.next = new ListNode(4);

			ListNode result = addTwoNumbers(l1, l2);
			printList(result); // Output: 7 0 8
		}
	}

subtract two nos using linked list.
	public class SubtractTwoNumbersLinkedList {
    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int borrow = 0;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int diff = x - y - borrow;
            if (diff < 0) {
                diff += 10; // Borrow from the next higher digit
                borrow = 1;
            } else {
                borrow = 0;
            }
            current.next = new ListNode(diff);
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

Remove adjacent duplicates
to generate binary numbers 1 to n
	import java.util.*;

	public class GenerateBinaryNumbers {
		public static List<String> generateBinaryNumbers(int n) {
			List<String> result = new ArrayList<>();
			Queue<String> queue = new LinkedList<>();
			queue.offer("1");

			for (int i = 0; i < n; i++) {
				String binary = queue.poll();
				result.add(binary);
				queue.offer(binary + "0");
				queue.offer(binary + "1");
			}

			return result;
		}

		public static void main(String[] args) {
			int n = 5;
			List<String> binaryNumbers = generateBinaryNumbers(n);
			System.out.println("Binary numbers from 1 to " + n + ": " + binaryNumbers);
		}
	}
	
Detect any 1 local minima in the array. You can call a number local minima if it's adjacent numbers are greater than the number itself. The solution is expected is better than O(n) solution
	To find any local minima in the array with better than O(n) complexity, you can use a binary search approach
	public class LocalMinima {
		public static int findLocalMinima(int[] nums) {
			int low = 0;
			int high = nums.length - 1;

			while (low <= high) {
				int mid = low + (high - low) / 2;

				// Check if mid is a local minima
				if ((mid == 0 || nums[mid] < nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] < nums[mid + 1])) {
					return nums[mid];
				}

				// Move the high pointer to the left if the element to the left of mid is smaller
				if (mid > 0 && nums[mid - 1] < nums[mid]) {
					high = mid - 1;
				} 
				// Otherwise, move the low pointer to the right
				else {
					low = mid + 1;
				}
			}

			return -1; // No local minima found
		}

		public static void main(String[] args) {
			int[] nums = {9, 6, 3, 14, 5, 7, 4};
			int localMinima = findLocalMinima(nums);
			if (localMinima != -1) {
				System.out.println("Local minima: " + localMinima);
			} else {
				System.out.println("No local minima found");
			}
		}
	}

segregate 0s and 1s in an array
	public class SegregateZerosAndOnes {
		public static void segregate(int[] nums) {
			int left = 0;
			int right = nums.length - 1;

			while (left < right) {
				// Move left pointer to the right until it points to 1
				while (nums[left] == 0 && left < right) {
					left++;
				}
				// Move right pointer to the left until it points to 0
				while (nums[right] == 1 && left < right) {
					right--;
				}
				// Swap 0s and 1s
				if (left < right) {
					nums[left] = 0;
					nums[right] = 1;
					left++;
					right--;
				}
			}
		}

		public static void main(String[] args) {
			int[] nums = {0, 1, 0, 1, 1, 0, 1, 0};
			segregate(nums);
			System.out.println("Segregated array: " + Arrays.toString(nums));
		}
	}

find the repeating number in an array of 0 to n.
	use the pigeonhole principle
	public class FindRepeatingNumber {
		public static int findRepeating(int[] nums) {
			int n = nums.length;
			// Calculate the sum of all elements in the array
			int sum = 0;
			for (int num : nums) {
				sum += num;
			}
			// Calculate the sum of numbers from 0 to n-1
			int expectedSum = (n - 1) * n / 2;
			// The difference between the expected sum and the actual sum is the repeating number
			return sum - expectedSum;
		}

		public static void main(String[] args) {
			int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11};
			int repeatingNumber = findRepeating(nums);
			System.out.println("Repeating number: " + repeatingNumber);
		}
	}
print spiral matrix.

import java.io.*;
import java.util.*;

class Solution
{
    //Function to return a list of integers denoting spiral traversal of matrix.
    static ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c)
    {
    ArrayList<Integer> matList = new ArrayList<Integer>();
    int top = 0;
    int bottom = r-1;
    int left = 0;
    int right = c-1;
    
    while(top <= bottom && left <= right){
        for(int i = left; i <= right; i++){
            matList.add(matrix[top][i]);
        }
        top++;
        for(int i = top; i <= bottom; i++){
            matList.add(matrix[i][right]);
        }
        right--;
        if(top <= bottom){
            for(int i = right; i >= left; i--){
                matList.add(matrix[bottom][i]);
            }
            bottom--;
        }
        if(left <= right){
            for(int i = bottom; i >= top; i--){
                matList.add(matrix[i][left]);
            }
            left++;
        }
    }
    return matList;
    }
}

You have been given a string and signature string. Signature string can contain only characters either a or b. For example string is ballballplayball. Multiple signatures can be possible for this string: aaba a, b, ab.
You have been given a signature. You need to tell whether signature is valid or not.

public class SignatureValidator {
    public static boolean isValidSignature(String input, String signature) {
        int i = 0; // Pointer for the input string
        int j = 0; // Pointer for the signature string
        
        while (i < input.length() && j < signature.length()) {
            char inputChar = input.charAt(i);
            char signatureChar = signature.charAt(j);
            
            // If characters match, move to the next character in both strings
            if (inputChar == signatureChar) {
                i++;
                j++;
            } else {
                // If signature character is 'a' and input character doesn't match, signature is invalid
                if (signatureChar == 'a') {
                    return false;
                }
                // If signature character is 'b' and input character doesn't match, ignore this mismatch and move to the next character in the input string
                i++;
            }
        }
        
        // If we reached the end of the input string, but not the end of the signature string, signature is invalid
        return j == signature.length();
    }
    
    public static void main(String[] args) {
        String input = "ballballplayball";
        String signature = "aaba";
        System.out.println(isValidSignature(input, signature)); // Output: true
    }
}

A linked list is given.we need to arrange the linked list in such a way that nodes having odd values will come before nodes having even values.
ex: I/P: 1->2->4->5->7->6
    O/P : 1->5->7->2->4->6
// Define a class representing a node in the linked list
class ListNode {
    int val; // Value of the node
    ListNode next; // Reference to the next node
    
    // Constructor to initialize the node with a given value
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListOddEven {
    // Method to rearrange the linked list such that nodes with odd values appear before nodes with even values
    public ListNode rearrangeLinkedList(ListNode head) {
        // Check if the linked list is empty or has only one node
        if (head == null || head.next == null) {
            return head; // Return the head as it is
        }
        
        // Create dummy nodes for odd and even l ists
        ListNode oddDummy = new ListNode(0);
        ListNode evenDummy = new ListNode(0);
        ListNode oddTail = oddDummy; // Pointer to the tail of the odd list
        ListNode evenTail = evenDummy; // Pointer to the tail of the even list
        
        // Traverse the original list
        ListNode current = head;
        while (current != null) {
            // Check if the current node's value is odd
            if (current.val % 2 == 1) {
                // Append the current node to the odd list
                oddTail.next = current;
                oddTail = oddTail.next; // Update the tail of the odd list
            } else {
                // Append the current node to the even list
                evenTail.next = current;
                evenTail = evenTail.next; // Update the tail of the even list
            }
            current = current.next; // Move to the next node in the original list
        }
        
        // Connect the tail of the odd list to the head of the even list
        oddTail.next = evenDummy.next;
        // Set the next of the tail of the even list to null to terminate the list
        evenTail.next = null;
        
        // Return the head of the rearranged list
        return oddDummy.next;
    }
    
    // Method to print the elements of the linked list
    public void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " "); // Print the value of the current node
            current = current.next; // Move to the next node
        }
        System.out.println(); // Move to the next line after printing all elements
    }
    
    // Main method to test the implementation
    public static void main(String[] args) {
        LinkedListOddEven solution = new LinkedListOddEven();
        
        // Create the linked list: 1->2->4->5->7->6
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next = new ListNode(6);
        
        System.out.println("Input:");
        solution.printLinkedList(head);
        
        // Rearrange the linked list
        ListNode rearrangedHead = solution.rearrangeLinkedList(head);
        
        System.out.println("Output:");
        solution.printLinkedList(rearrangedHead);
    }
}

Two Sum
 hasArrayTwoCandidates(int A[], int arr_size, int sum)
    {
        int l, r;
 
        /* Sort the elements */
        Arrays.sort(A);
 
        /* Now look for the two candidates
        in the sorted array*/
        l = 0;
        r = arr_size - 1;
        while (l < r) {
            if (A[l] + A[r] == sum)
                return true;
            else if (A[l] + A[r] < sum)
                l++;
            else // A[i] + A[j] > sum
                r--;
        }
        return false;
    }
	
Maximum path sum between two leaves of a binary tree
Time Complexity: O(N) where n is the number of nodes
Auxiliary Space: O(N)
Code:
	// An object of Res is passed around so that the same value can be used by multiple recursive calls.
	class Res {
		int val;
	}

	class BinaryTree {
		static Node root;

		// A utility function to find the maximum sum between any
		// two leaves.This function calculates two values:
		// 1) Maximum path sum between two leaves which is stored
		// in res.
		// 2) The maximum root to leaf path sum which is returned.
		int maxPathSumUtil(Node node, Res res) {

			// Base cases
			if (node == null)
				return 0;
			if (node.left == null && node.right == null)
				return node.data;

			// Find maximum sum in left and right subtree. Also
			// find maximum root to leaf sums in left and right
			// subtrees and store them in ls and rs
			int ls = maxPathSumUtil(node.left, res);
			int rs = maxPathSumUtil(node.right, res);

			// If both left and right children exist
			if (node.left != null && node.right != null) {

				// Update result if needed
				res.val = Math.max(res.val, ls + rs + node.data);

				// Return maximum possible value for root being
				// on one side
				return Math.max(ls, rs) + node.data;
			}

			// If any of the two children is empty, return
			// root sum for root being on one side
			return (node.left == null) ? rs + node.data : ls + node.data;
		}

		// The main function which returns sum of the maximum
		// sum path between two leaves. This function mainly
		// uses maxPathSumUtil()
		int maxPathSum() {
			Res res = new Res();
			res.val = Integer.MIN_VALUE;

			int val = maxPathSumUtil(root, res);
		
			if (root.left != null && root.right != null)
				return res.val;
			else {
				//--- for test case ---
				//		 7				 
				//	 / \			 
					// Null -3				 
				// value of res will be INT_MIN but the answer is 4, 
				// which is returned by the function maxPathSumUtil()
				return Math.max(res.val,val);
			}
		}
	}
	
Best Time to Buy and Sell Stock III
In comparison to the easy version where we can do only one transaction here, we can do at most two transactions. which means either one transaction or two transactions in such a way that gives maximum profit.
prices = [3,3,5,0,0,3,1,4]
First transaction: Buy on day 4 (cost price = 0) sell on day 6 (selling price = 3) profit = 3-0 = 3.
Second transaction: buy on day 7(cost price = price-profit=(1-3)=-2) sell on day 8 (price = 4) profit = 4-(-2) = 6(Net profit).

import java.util.Arrays; 
public class Tutorialcup {
    public static int maxProfit(int[] prices) {
  int minPrice1 = Integer.MAX_VALUE;
  int profit1 = 0;
  int minPrice2 = Integer.MAX_VALUE;
  int profit2 = 0;
  for(int i = 0; i < prices.length; i++) {
    int p = prices[i];
    minPrice1 = Math.min(minPrice1, p);
    profit1 = Math.max(profit1, p - minPrice1);
    minPrice2 = Math.min(minPrice2, p - profit1);
    profit2 = Math.max(profit2, p - minPrice2);
  }
  
  return profit2;
    }
  public static void main(String[] args) {
    int [] arr = {1,2,3,4,5}; 
    int ans=  maxProfit(arr);
    System.out.println(ans);
  }
}

public class LongestSameCharacterSubstring {
    public static int[] findLongestSameCharacterSubstring(String str) {
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


some string manipulation and maximum average calculation problem. In this round, all the given test cases should pass.
Input:
[{“Bob”,”87″}, {“Mike”, “35”},{“Bob”, “52”}, {“Jason”,”35″},
{“Mike”, “55”}, {“Jessica”, “99”}]
Output: 99

Given a 2-D String array of student marks, find the student with the highest average score?

import java.util.*;

public class Main {
    public static String findMaxAverage(String[][] scores) {
        Map<String, Integer> sumMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        
        // Calculate sum and count of scores for each name
        for (String[] score : scores) {
            String name = score[0];
            int value = Integer.parseInt(score[1]);
            
            sumMap.put(name, sumMap.getOrDefault(name, 0) + value);
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }
        
        double maxAverage = Double.MIN_VALUE;
        String maxName = "";
        
        // Calculate average and find the maximum average
        for (String name : sumMap.keySet()) {
            double average = (double) sumMap.get(name) / countMap.get(name);
            if (average > maxAverage) {
                maxAverage = average;
                maxName = name;
            }
        }
        
        return maxName;
    }

    public static void main(String[] args) {
        String[][] scores = {
            {"Bob", "87"}, {"Mike", "35"}, {"Bob", "52"},
            {"Jason", "35"}, {"Mike", "55"}, {"Jessica", "99"}
        };
        
        System.out.println(findMaxAverage(scores));  // Output: Jessica
    }
}

Find the lowest common ancestor in a tree.
Node lca(Node node, int n1, int n2)
    {
        if (node == null)
            return null;
 
        // If both n1 and n2 are smaller than root, then LCA
        // lies in left
        if (node.data > n1 && node.data > n2)
            return lca(node.left, n1, n2);
 
        // If both n1 and n2 are greater than root, then LCA
        // lies in right
        if (node.data < n1 && node.data < n2)
            return lca(node.right, n1, n2);
 
        return node;
    }

Given a tree, check if the tree is a Binary search tree or not

import java.io.*;
 
class GFG {
 
  /* A binary tree node has data, pointer to left child
        and a pointer to right child */
  static class node {
    int data;
    node left, right;
  }
 
  /* Helper function that allocates a new node with the
        given data and NULL left and right pointers. */
  static node newNode(int data)
  {
    node Node = new node();
    Node.data = data;
    Node.left = Node.right = null;
 
    return Node;
  }
 
  static int maxValue(node Node)
  {
    if (Node == null) {
      return Integer.MIN_VALUE;
    }
    int value = Node.data;
    int leftMax = maxValue(Node.left);
    int rightMax = maxValue(Node.right);
 
    return Math.max(value, Math.max(leftMax, rightMax));
  }
 
  static int minValue(node Node)
  {
    if (Node == null) {
      return Integer.MAX_VALUE;
    }
    int value = Node.data;
    int leftMax = minValue(Node.left);
    int rightMax = minValue(Node.right);
 
    return Math.min(value, Math.min(leftMax, rightMax));
  }
 
  /* Returns true if a binary tree is a binary search tree
     */
  static int isBST(node Node)
  {
    if (Node == null) {
      return 1;
    }
     
    /* false if the max of the left is > than us */
    if (Node.left != null
        && maxValue(Node.left) > Node.data) {
      return 0;
    }
     
    /* false if the min of the right is <= than us */
    if (Node.right != null
        && minValue(Node.right) < Node.data) {
      return 0;
    }
     
    /* false if, recursively, the left or right is not a
         * BST*/
    if (isBST(Node.left) != 1
        || isBST(Node.right) != 1) {
      return 0;
    }
     
    /* passing all that, it's a BST */
    return 1;
  }
 
  public static void main(String[] args)
  {
    node root = newNode(4);
    root.left = newNode(2);
    root.right = newNode(5);
     
    // root->right->left = newNode(7);
    root.left.left = newNode(1);
    root.left.right = newNode(3);
 
    // Function call
    if (isBST(root) == 1) {
      System.out.print("Is BST");
    }
    else {
      System.out.print("Not a BST");
    }
  }
}	

	
Merge k sorted arrays
max sum subsequence
there are n cities, numbered from 1 to n, and a path is there connecting them. There is a roads path between any two cities. Need to find out if city is reachable or not.
Theifs and warehouses problem
Count ways to reach the n’th stair
Given an array of numbers, find the maximum product triplet. 
Find the difference between maximum and minimum element such that the maximum element is always at higher index than the smaller element.

About Data encapsulation and Abstraction and their importance
overflow and underflow conditions in deques.
 
Find shortest unique prefix to represent each word in the list.
    Input: [zebra, dog, duck, dove]
    Output: {z, dog, du, dov}
	
For Given Number N find if its COLORFUL number or not
COLORFUL number: A number can be broken into different sub-sequence parts.
Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.
And this number is a COLORFUL number, since product of every digit of a sub-sequence are different and other questions exactly I don’t remember but one was from graphs and other two was from DP

the graph and its different representation Adjacency List, Adjacency matrix, graph representation using edges and Longest path in a directed Acyclic graph.
Then,He asked me that if I was given a undirected graph,then how will I represent it?
I said that i will represent it in two ways either using adjacency Matrix or Adjacency List.
Then ,he asked me to write code to create above graph using adjacency list .
Then,he said that we need to find shortest distance between source and a destination.
I asked whether the graph is unweighted or not?
he said graph is weighted and the weights are distinct and positive.Then i changed the representation of each node in adjacency list and
Then I said we can apply Dijkstra’s Algorithm and then he told me to implement it in any language