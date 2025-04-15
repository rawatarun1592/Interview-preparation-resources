1. Convert a string as given in the format below:
Example:
aabbb into a2b3
aaaa into a4
a into a1

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
			if (count > 1) {
				compressedString.append(count);
			}

			// Clear the count for the current character
			charCountMap.put(currentChar, 0);
		}
	}

	// Return the compressed string
	return compressedString.toString();
}

2. Given a dictionary (list of words) and a substring, you have to return the length of the longest word in the dictinary containing the substring.

Example:
String[] dict = {"CODGE", "ODG", "LODGES", "SODG", "dodge", "mODJ", "LODGESSSS"}
String toSearch = "ODG";
//Returns 9 (LODGESSSS)

public class LongestWordWithSubstring {
    
    /**
     * Function to find the longest word in the dictionary array that contains a given substring.
     * 
     * @param dict The dictionary array.
     * @param toSearch The substring to search for.
     * @return The longest word containing the substring.
     */
    public static String longestWordWithSubstring(String[] dict, String toSearch) {
        String longestWord = ""; // Initialize the longest word to an empty string
        for (String word : dict) { // Iterate through each word in the dictionary
            // Check if the current word contains the given substring and is longer than the current longest word
            if (word.contains(toSearch) && word.length() > longestWord.length()) {
                longestWord = word; // Update the longest word
            }
        }
        return longestWord; // Return the longest word containing the substring
    }

    public static void main(String[] args) {
        // Test the longestWordWithSubstring method
        String[] dict = {"CODGE", "ODG", "LODGES", "SODG", "dodge", "mODJ", "LODGESSSS"};
        String toSearch = "ODG";
        String longestWord = longestWordWithSubstring(dict, toSearch);
        System.out.println("Longest word containing substring: " + longestWord);
    }
}


3. Write a program to print the inorder traversal of a binary tree.

Recursive:
// Given a binary tree, print its nodes in inorder
void printInorder(Node node)
{
	if (node == null)
		return;

	// First recur on left child
	printInorder(node.left);

	// Then print the data of node
	System.out.print(node.key + " ");

	// Now recur on right child
	printInorder(node.right);
}

Iterative:
public static List<Integer> inorderTraversal(TreeNode root) {
	// List to store the inorder traversal result
	List<Integer> result = new ArrayList<>();
	// Stack to simulate the recursive function call stack
	Stack<TreeNode> stack = new Stack<>();
	// Current node initialized with the root
	TreeNode current = root;
	
	// Iterate until current node becomes null and stack becomes empty
	while (current != null || !stack.isEmpty()) {
		// Traverse left subtree and push nodes onto the stack
		while (current != null) {
			stack.push(current);
			current = current.left;
		}
		
		// Pop the node from the stack, visit it, and move to its right subtree
		current = stack.pop();
		result.add(current.val);
		current = current.right;
	}
	
	// Return the inorder traversal result
	return result;
}

4. Given the string (containing directions e.g., U, R, D, L) and initial coordinates (0,0),
return the final coordinates after traversing the string.
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

*** 5. Find the median of two sorted arrays of different sizes.
Example:
arr1[] = {2, 3, 5, 8}
arr2[] = {10, 12, 14, 16, 18, 20}
//Return 11 as the median

*****6. Prefix Search, Given a document and a prefix, return all the words starting with the prefix.
Example:
Say the document is a string of words,
String[] arr = {"apple", "applet", "bread", "aper"};
String prefix = "app";
// Return apple, applet

Brute Force:
import java.util.ArrayList;
import java.util.List;

public class PrefixSearch {
    public static void main(String[] args) {
        String[] arr = {"apple", "applet", "bread", "aper"};
        String prefix = "app";

        List<String> wordsWithPrefix = searchPrefix(arr, prefix);
        System.out.println("Words with prefix '" + prefix + "': " + wordsWithPrefix);
    }

    public static List<String> searchPrefix(String[] arr, String prefix) {
        List<String> result = new ArrayList<>();
        for (String word : arr) {
            if (word.startsWith(prefix)) {
                result.add(word);
            }
        }
        return result;
    }
}
https://docs.google.com/document/d/1ZhnyO0i24_MG4jrLGj5cIKVFIwwM4Q_7hWgX1T9L8kQ/edit
Trie data structure is defined as a Tree based data structure that is used for storing some collection of strings and performing efficient search operations on them. The word Trie is derived from reTRIEval, which means finding something or obtaining it. 
Trie follows some property that If two strings have a common prefix then they will have the same ancestor in the trie. A trie can be used to sort a collection of strings alphabetically as well as search whether a string with a given prefix is present in the trie or not.

Need for Trie Data Structure?
A Trie data structure is used for storing and retrieval of data and the same operations could be done using another data structure which is Hash Table but Trie can perform these operations more efficiently than a Hash Table. Moreover, Trie has its own advantage over the Hash table. A Trie data structure can be used for prefix-based searching whereas a Hash table can’t be used in the same way. 
import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    List<String> searchPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return result;
            }
            curr = curr.children[index];
        }
        searchWords(curr, prefix, result);
        return result;
    }

    void searchWords(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.children[c - 'a'] != null) {
                searchWords(node.children[c - 'a'], prefix + c, result);
            }
        }
    }
}

public class PrefixSearch {
    public static void main(String[] args) {
        String[] arr = {"apple", "applet", "bread", "aper"};
        String prefix = "app";

        Trie trie = new Trie();
        for (String word : arr) {
            trie.insert(word);
        }

        List<String> wordsWithPrefix = trie.searchPrefix(prefix);
        System.out.println("Words with prefix '" + prefix + "': " + wordsWithPrefix);
    }
}


7. Given a string, return the first non-repeating character.
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

8. Given a sentence/phrase and two words, return the minimum distance between the words.
Example:
String str = "the quick the brown quick brown the frog"
String word1 = "quick"
String word2 = "frog"
//Return 2"

public class MinimumDistanceBetweenWords {
    
    /**
     * Function to find the minimum distance between two words in a string.
     * 
     * @param str The input string.
     * @param word1 The first word to find.
     * @param word2 The second word to find.
     * @return The minimum distance between the two words. Returns -1 if either word is not found.
     */
    public static int minDistance(String str, String word1, String word2) {
        // Split the input string into words
        String[] words = str.split(" ");
        int minDistance = Integer.MAX_VALUE;
        int index1 = -1, index2 = -1;

        // Traverse through the words array to find the indices of word1 and word2
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }

            // If both word1 and word2 are found, calculate the distance and update minDistance
            if (index1 != -1 && index2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(index1 - index2));
            }
        }

        // If either word1 or word2 is not found, return -1
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) {
        String str = "the quick the brown quick brown the frog";
        String word1 = "quick";
        String word2 = "frog";

        // Find and print the minimum distance between word1 and word2 in the string
        System.out.println("Minimum distance between \"" + word1 + "\" and \"" + word2 + "\": " + minDistance(str, word1, word2));
    }
}

***9. Implement your custom atoi function in Java. Given a string, you have to convert into integer. The input string may contain alphabets and special characters alongside numbers (both negative and positive).
Examples:
String str1 = "123";     //Return 123 (in integer format)

String str2 = "-123";    //Return -123

String str3 = "1a23";   //Return -1 (Invalid entry)

10. We are given a chess board and a piece with a fixed position (immoveable). We also have a bishop on the board which, well of course, can only move diagonally.
- Return "YES" if we can reach the immoveable piece using the bishop, else return "NO".
- If "YES", return the number of minimum steps it takes for the bishop to reach the immoveable piece.

public class BishopMove {
    
    /**
     * Function to determine if the bishop can reach the target square.
     * 
     * @param bishopX The x-coordinate of the bishop.
     * @param bishopY The y-coordinate of the bishop.
     * @param targetX The x-coordinate of the target square.
     * @param targetY The y-coordinate of the target square.
     * @return "YES" if the bishop can reach the target, "NO" otherwise.
     */
    public static String canReach(int bishopX, int bishopY, int targetX, int targetY) {
        // If the difference in x-coordinate equals the difference in y-coordinate, the bishop can reach the target
        if (Math.abs(bishopX - targetX) == Math.abs(bishopY - targetY)) {
            return "YES";
        }
        return "NO";
    }

    /**
     * Function to calculate the minimum steps required for the bishop to reach the target square.
     * 
     * @param bishopX The x-coordinate of the bishop.
     * @param bishopY The y-coordinate of the bishop.
     * @param targetX The x-coordinate of the target square.
     * @param targetY The y-coordinate of the target square.
     * @return The minimum number of steps required.
     */
    public static int minSteps(int bishopX, int bishopY, int targetX, int targetY) {
        // The minimum steps required is the absolute difference in x-coordinates
        return Math.abs(bishopX - targetX);
    }

    public static void main(String[] args) {
        // Test cases
        int bishopX = 3, bishopY = 3;
        int targetX = 6, targetY = 6;
        
        // Check if the bishop can reach the target square
        System.out.println("Can the bishop reach the target? " + canReach(bishopX, bishopY, targetX, targetY)); // Expected: YES
        
        // Calculate and print the minimum steps required
        System.out.println("Minimum steps required: " + minSteps(bishopX, bishopY, targetX, targetY)); // Expected: 3
    }
}

11. Given a sorted rotated array, return the minimum element from it.
Examples:
int[] arr1 = {5, 6, 1, 2, 3, 4};    //Return 1

int[] arr2 = {1, 2, 3, 4};            //Return 1

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

12. Given an array of integers and a sum K, return the length of the shortest sub-array which matches the given sum. If it doesn't exist, return -1.
[Even a single integer can be considered as a sum.]
Examples:
int[] arr = {2, 4, 6, 10, 2, 1}, K = 12        // Return 2

int[] arr = {5, 8, 50, 4}, K = 50                // Return 50 (A single integer may be considered as a sum)

public class ShortestSubarraySum {
    public static int shortestSubarraySum(int[] arr, int K) {
        // Initialize minLength to a large value to store the minimum length of sub-arrays
        int minLength = Integer.MAX_VALUE;
        // Initialize sum to keep track of the sum of elements in the current window
        int sum = 0;
        // Initialize start to track the start index of the current window
        int start = 0;

        // Iterate through the array using the end pointer to represent the end of the current window
        for (int end = 0; end < arr.length; end++) {
            // Add the current element to the sum
            sum += arr[end];

            // Check if the sum exceeds or equals the target sum K
            while (sum >= K) {
                // Update minLength with the minimum length encountered so far
                minLength = Math.min(minLength, end - start + 1);
                // Move the start pointer forward to reduce the window size
                sum -= arr[start];
                start++;
            }
        }

        // Return the minimum length of the sub-array, or -1 if no sub-array with sum K is found
        return (minLength == Integer.MAX_VALUE) ? -1 : minLength;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {2, 4, 6, 10, 2, 1};
        int K1 = 12;
        // Example 2
        int[] arr2 = {5, 8, 50, 4};
        int K2 = 50;

        // Print the result for the first example
        System.out.println("Length of the shortest sub-array for K = " + K1 + ": " + shortestSubarraySum(arr1, K1));
        // Print the result for the second example
        System.out.println("Length of the shortest sub-array for K = " + K2 + ": " + shortestSubarraySum(arr2, K2));
    }
}

13. Check whether a number is a power of 10.

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

14. Given a list of student names and corresponding marks, return the highest average marks (and print the corresponding student name - optional).
The marks can be negative as well and a student may have multiple marks.
Example:
String[][] = {{“Charles”, 84}, {“John”, 100}, {“Andy”, 37}, {“John”, 23}, {“Charles”, 20}};
//Return 61.5 (which is of John's)

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

15. Given an array of strings, return the IP address that occurs maximum number of times. 
[You might have to check for the validity of the IP address before considering it].
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

16. Given a dictionary (list of words) and a word, return an array of strings that can be formed from the given word.
[Every letter in the input word can occur only once in the word to be returned].
Examples:
String[] dict = {""ab"", ""abcd"", ""bcdaf"", ""bcad"", ""acb"", ""acab""};
String input = ""abcd"";
// Return {""abcd"", ""bcad""}

String[] dict =  {""ab"", ""abcd"", ""bcdaf"", ""bcad"", ""acaab"", ""acab""};
String input = ""caab"";
// Return {""acab""}

Given a dictionary of the words(strings) which contains different words & you are given an input string e.g. “abd”. You need to find the largest word available in the supplied dictionary which can be made using the letters of input string. The returned word can contain only the same no of occurrences of the letters as given in the input string i.e. if a letter is given once then in the output it should be existed only once. 
Examples:
Dictionary {“to”, “banana”, “toe”, “dogs”, “ababcd”, “elephant”} and input string is “eot”. Output should be “toe”
Dictionary is same as specified in example a but the input string is “ogtdes” and the output is “dogs” and “toes”

public class Main {
    // Function to find the largest word from the dictionary that can be formed using the characters of inputString
    public static String findLargestWord(String[] dictionary, String inputString) {
        String largestWord = ""; // Initialize the largest word found so far
        int maxLength = 0; // Initialize the maximum length of the largest word

        // Iterate through each word in the dictionary
        for (String word : dictionary) {
            // Check if the length of the word is less than or equal to the length of the inputString
            // and if the word can be formed using the characters of the inputString
            if (word.length() <= inputString.length() && containsLetters(inputString, word)) {
                // If the length of the current word is greater than the maximum length found so far,
                // update the largestWord and maxLength
                if (word.length() > maxLength) {
                    largestWord = word;
                    maxLength = word.length();
                }
            }
        }

        return largestWord; // Return the largest word found
    }

    // Function to check if a word can be formed using the characters of the inputString
    public static boolean containsLetters(String inputString, String word) {
        Map<Character, Integer> inputMap = new HashMap<>(); // Create a map to store character counts of inputString

        // Populate the inputMap with character counts of inputString
        for (char c : inputString.toCharArray()) {
            inputMap.put(c, inputMap.getOrDefault(c, 0) + 1);
        }

        // Iterate through each character of the word
        for (char c : word.toCharArray()) {
            // If the character is not present in inputMap or its count is zero,
            // return false indicating the word cannot be formed
            if (!inputMap.containsKey(c) || inputMap.get(c) == 0) {
                return false;
            }
            // Decrement the count of the character in inputMap as it is used in forming the word
            inputMap.put(c, inputMap.get(c) - 1);
        }

        return true; // Return true indicating the word can be formed
    }

    public static void main(String[] args) {
        // Test data
        String[] dictionary = {"to", "banana", "toe", "dogs", "ababcd", "elephant"};
        String inputString = "eot";
        // Find and print the largest word that can be formed using the characters of inputString
        System.out.println(findLargestWord(dictionary, inputString));  // Output: "toe"
    }
}

17. Given a sorted array of non-negative integers of size n, return the smallest missing integer.
Examples:
int[] arr1 = {1, 2, 3, 4};    // Return 0

int[] arr2 = {0, 1, 3, 4};    // Return 2

public class SmallestMissingInteger {
    public static int findSmallestMissingInteger(int[] arr) {
        // If the first element is not 0, return 0 as the smallest missing integer
        if (arr[0] != 0) {
            return 0;
        }

        // Iterate through the array starting from the second element
        for (int i = 1; i < arr.length; i++) {
            // Check for a gap between consecutive elements
            if (arr[i] != arr[i - 1] + 1) {
                // Return the previous element plus 1 as the smallest missing integer
                return arr[i - 1] + 1;
            }
        }

        // If no gaps are found, return the last element plus 1 as the smallest missing integer
        return arr[arr.length - 1] + 1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {0, 1, 3, 4};

        System.out.println("Smallest missing integer in arr1: " + findSmallestMissingInteger(arr1));
        System.out.println("Smallest missing integer in arr2: " + findSmallestMissingInteger(arr2));
    }
}

***18. Given two integers representing the numerator and denominator of a fraction, return the fraction in string format. 
[If the fractional part is repeating, enclose the repeating part in parentheses].
Examples:
Numerator = 1, Denominator = 2
// Return ""0.5""

Numerator = 1, Denominator = -2
// Return ""-0.5""

Numerator = 50, Denominator = 22
// Return ""2.(27)""  [Fractional part (27) is repeating, hence enclosed in paranthesis]

19. Given a string, return the starting index & length of the longest substring containing the same character.
Example:
String[] str = "aabbbbCCddd";    // Return 2 (index) and substring "bbbb"

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

    public static void main(String[] args) {
        String str = "aabbbbCCddd";
        int[] result = findLongestSameCharacterSubstring(str);
        System.out.println("Starting index: " + result[0] + ", Length: " + result[1]);
    }
}

***20. Give two fractions, the numerator and denominator represented as a pair of ints, return them in their simplest form after adding them.
Examples:
{Numerator_frac1 = 1, Denominator_frac1 = 3}                // 1/3
{Numerator_frac2 = 3, Denominator_frac2 = 9}                // 3/9
// Return Numerator_ans = 2, Denominator_ans = 3       // 2/3

{Numerator_frac1 = 1, Denominator_frac1 = 2}               // 1/2
{Numerator_frac2 = 3, Denominator_frac2 = 2}               // 3/2
// Return Numerator_ans = 2, Denominator_ans = 1      // 2/1

21. A staircase with n steps is given. Starting from the base, return the number of ways of reaching the n’th stair.
[The person can climb either 1 or 2 stairs in one move].

class stairs {
    // A simple recursive program to find
    // n'th fibonacci number
    static int fib(int n)
    {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }
 
    // Returns number of ways to reach s'th stair
    static int countWays(int s) { return fib(s + 1); }
 
    /* Driver program to test above function */
    public static void main(String args[])
    {
        int s = 4;
        System.out.println("Number of ways = "
                           + countWays(s));
    }
}
Time Complexity: O(2n) , because at each stair there are two choices and there are total of n stairs.
Auxiliary Space: O(n), because of recursive stack space.

// Java program to count number of
// ways to reach Nth stair
class GFG {
 
    // A simple recursive function to find number of ways to
    // reach the nth stair
    static int countWays(int n, int dp[])
    {
        if (n <= 1)
            return dp[n] = 1;
 
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = countWays(n - 1, dp) + countWays(n - 2, dp);
        return dp[n];
    }
 
    // Driver code
    public static void main(String[] args)
    {
        int n = 4;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }
        System.out.println(countWays(n, dp));
    }
}
Time Complexity: O(n)
Auxiliary Space: O(n)

Tabulation:
class GFG {
    // A function to find number of ways to reach the nth
    // stair
    static int countWays(int n)
    {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
 
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }
 
    // Driver method
    public static void main(String[] args)
    {
        int n = 4;
        System.out.println("Number of ways = "
                           + countWays(n));
    }
}
Time Complexity: O(n)
Auxiliary Space: O(n)

22. Pascal Triangle
generate the Pascal Triangle, OR
generate a specified row of the Pascal Triangle, OR
calculate the element at a specified row and column.
 1
 1 1
 1 2 1
 1 3 3 1
 1 4 6 4 1
 1 5 10 10 5 1
 1 6 15 20 15 6 1
 
 import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static void main(String[] args) {
        int numRows = 5;

        // Generate Pascal's Triangle
        List<List<Integer>> pascalTriangle = generatePascalTriangle(numRows);
        System.out.println("Pascal Triangle:");
        displayPascalTriangle(pascalTriangle);

        int rowNum = 3;
        // Generate a specified row of Pascal's Triangle
        List<Integer> specifiedRow = getRowOfPascalTriangle(pascalTriangle, rowNum);
        System.out.println("\nRow " + rowNum + " of Pascal Triangle: " + specifiedRow);

        int row = 4;
        int col = 2;
        // Calculate the element at a specified row and column
        int element = getElementOfPascalTriangle(pascalTriangle, row, col);
        System.out.println("\nElement at row " + row + " and column " + col + ": " + element);
    }

    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows <= 0) return triangle;

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int num = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(num);
                }
            }
            triangle.add(row);
        }
        return triangle;
    }

    public static void displayPascalTriangle(List<List<Integer>> triangle) {
        for (List<Integer> row : triangle) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static List<Integer> getRowOfPascalTriangle(List<List<Integer>> triangle, int rowIndex) {
        if (rowIndex < 0 || rowIndex >= triangle.size()) return new ArrayList<>();

        return triangle.get(rowIndex);
    }

    public static int getElementOfPascalTriangle(List<List<Integer>> triangle, int row, int col) {
        if (row < 0 || row >= triangle.size() || col < 0 || col >= triangle.get(row).size()) return 0;

        return triangle.get(row).get(col);
    }
}

23. Maximum path sum in matrix
Given a matrix of N * M. Find the maximum path sum in matrix. The maximum path is sum of all elements from first row to last row where you are allowed to move only down or diagonally to left or right. You can start from any element in first row.
Input : mat[][] = 10 10  2  0 20  4
                   1  0  0 30  2  5
                   0 10  4  0  2  0
                   1  0  2 20  0  4
Output : 74
The maximum sum path is 20-30-4-20.

Input : mat[][] = 1 2 3
                  9 8 7
                  4 5 6
Output : 17
The maximum sum path is 3-8-6.

import static java.lang.Math.max;

class GFG 
{
	public static int N = 4, M = 6;
	
	// Function to calculate max path in matrix
	static int findMaxPath(int mat[][])
	{
		// To find max val in first row
		int res = -1;
		for (int i = 0; i < M; i++)
			res = max(res, mat[0][i]);

		for (int i = 1; i < N; i++) 
		{
			res = -1;
			for (int j = 0; j < M; j++) 
			{
				// When all paths are possible
				if (j > 0 && j < M - 1)
					mat[i][j] += max(mat[i - 1][j],
								max(mat[i - 1][j - 1], 
									mat[i - 1][j + 1]));

				// When diagonal right is not possible
				else if (j > 0)
					mat[i][j] += max(mat[i - 1][j],
									mat[i - 1][j - 1]);

				// When diagonal left is not possible
				else if (j < M - 1)
					mat[i][j] += max(mat[i - 1][j],
								mat[i - 1][j + 1]);

				// Store max path sum
				res = max(mat[i][j], res);
			}
		}
		return res;
	}
	
	// driver program
	public static void main (String[] args) 
	{
		int mat[][] = { { 10, 10, 2, 0, 20, 4 },
						{ 1, 0, 0, 30, 2, 5 },
						{ 0, 10, 4, 0, 2, 0 },
						{ 1, 0, 2, 20, 0, 4 } 
					};

		System.out.println(findMaxPath(mat));
	}
}

Introduction and technical discussion about my recent project
OOPS questions
HashMap internal working
JVM architecture.
How is Java different than other object-oriented programming languages?
Detailed discussion on Garbage collector
You need to design a relational database; how will you design it? Which data structures will you use?
Find the top 3 horses puzzle.
Round 5 (Hiring Manager):

Quick introduction
If you are to design a garbage collector, how will you design it?
What is wrapper class and why do we need it?
What is type erasure and why do we need it?
Why do you want to leave the current organization?
Why GS?
He explained my role in the team

How does a HashMap work internally? 
Code a simplistic version of the working.
    - Implement Put/Get methods.
    - Scaling the HashMap when it reaches full capacity. 
Method overloading and method overriding in Java.
What are immutable classes
Explain the difference between Hashmap and Concurrent Hashmap.

An Object-Relational Mapping (ORM) framework is a software tool that allows developers to map object-oriented programming language constructs to relational database constructs. It provides a layer of abstraction between the application code and the database, allowing developers to work with objects and classes rather than SQL queries.

Java Persistence API (JPA)  is a specification for managing data persistence in Java applications. JPA helps in mapping Java objects to relational database tables and allows developers to perform CRUD (create, read, update, delete) operations on data.
It is a popular open-source ORM (object-relational mapping) framework. 
It defines a set of interfaces and annotations that allow developers to create persistent entities, query data, and manage relationships between entities.




 




