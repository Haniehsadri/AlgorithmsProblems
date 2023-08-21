package com.company;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        // write your code here
    }


    public int maxProfit(int[] prices) {
        int n = prices.length;

        int buy = 0;
        int sell = 1;
        int profit = 0;
        while (sell < n) {
            if (prices[sell] > prices[buy]) {
                profit += prices[sell] - prices[buy];

            }

            sell++;
            buy++;

        }

        return profit;
    }

    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * @param nums the integer array
     * @return a list of triplets that satisfy the given conditions
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicate values for the first element
            }

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++; // Skip duplicate values for the second element
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--; // Skip duplicate values for the third element
                    }

                    left++;
                    right--;
                }
            }
        }

        return result;
    }


    /**
     * Calculates the maximum area formed by vertical lines and an array of heights.
     * The area is determined by the width between two vertical lines (indices) and the minimum height among them.
     * This method implements the two-pointer approach to optimize the computation.
     *
     * @param height An array of integers representing the heights of the vertical lines.
     * @return The maximum area formed by the vertical lines.
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;
            maxArea = Math.max(maxArea, currentArea);

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }


    /**
     * Calculates the maximum profit that can be obtained from a given array of prices.
     * The maximum profit is the difference between the highest selling price and the lowest buying price.
     * If no profit can be obtained, returns 0.
     *
     * @param prices an array of prices
     * @return the maximum profit that can be obtained
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0; // Edge case: no profit can be obtained
        }

        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (currentPrice < minPrice) {
                minPrice = currentPrice; // Update the minimum buying price
            } else {
                int profit = currentPrice - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit; // Update the maximum profit
                }
            }
        }

        return maxProfit;
    }


    /**
     * Returns the length of the longest substring without repeating characters in the given string.
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int[] lastOccurrence = new int[256];
        Arrays.fill(lastOccurrence, -1);
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            int lastSeenIndex = lastOccurrence[currentChar];

            if (lastSeenIndex >= left) {
                left = lastSeenIndex + 1;
            }

            lastOccurrence[currentChar] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }


    /**
     * Checks if a string containing parentheses, brackets, and braces is valid.
     *
     * @param s the string to be checked
     * @return true if the string is valid, false otherwise
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false; // Early termination if the string length is odd
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    /**
     * Finds the minimum value in an array of integers using a binary search-like approach.
     *
     * @param nums  the array of integers
     * @param left  the left index of the subarray to search
     * @param right the right index of the subarray to search
     * @return the minimum value in the array
     */
    public int findMin(int[] nums, int left, int right) {
        if (left >= right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] > nums[right]) {
            return findMin(nums, mid + 1, right);
        } else {
            return findMin(nums, left, mid);
        }
    }


    /**
     * Calculates the number of unique paths from the top-left corner to the bottom-right corner
     * in a grid of size m x n.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths from the top-left corner to the bottom-right corner.
     */
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        return uniquePaths(0, 0, m, n, memo);
    }

    /**
     * Helper method to calculate the number of unique paths from a given position (i, j)
     * to the bottom-right corner in a grid of size m x n.
     *
     * @param i    The current row index.
     * @param j    The current column index.
     * @param m    The number of rows in the grid.
     * @param n    The number of columns in the grid.
     * @param memo The memoization array to store previously calculated results.
     * @return The number of unique paths from the current position to the bottom-right corner.
     */
    private int uniquePaths(int i, int j, int m, int n, int[][] memo) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (i >= m || j >= n) {
            return 0;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int rightPaths = uniquePaths(i, j + 1, m, n, memo);
        int downPaths = uniquePaths(i + 1, j, m, n, memo);
        memo[i][j] = rightPaths + downPaths;
        return memo[i][j];
    }


    /**
     * Returns the diameter of a binary tree.
     *
     * @param root The root node of the binary tree.
     * @return The diameter of the binary tree.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        calculateMaxDepth(root);
        return max;
    }

    /**
     * Calculates and updates the maximum diameter of a given node in a binary tree.
     * Returns the maximum depth of the given node.
     *
     * @param node The node for which to calculate the maximum depth.
     * @return The maximum depth of the given node.
     */
    private int calculateMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = calculateMaxDepth(node.left);
        int rightDepth = calculateMaxDepth(node.right);

        max = Math.max(max, leftDepth + rightDepth);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * Determines whether it is possible to reach the last index in the given array by making jumps from the starting index.
     *
     * @param nums An array of non-negative integers representing the maximum jump length from each position.
     * @return {@code true} if it is possible to reach the last index, {@code false} otherwise.
     * @throws IllegalArgumentException if the input array is null or empty.
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }

        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0;
    }


    /**
     * Returns a list of integers representing the elements of a matrix traversed in spiral order.
     *
     * @param matrix the input matrix
     * @return a list of integers in spiral order
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int topRow = 0;
        int bottomRow = matrix.length - 1;
        int leftColumn = 0;
        int rightColumn = matrix[0].length - 1;

        while (topRow <= bottomRow && leftColumn <= rightColumn) {
            // Traverse top row
            for (int i = leftColumn; i <= rightColumn; i++) {
                result.add(matrix[topRow][i]);
            }
            topRow++;

            // Traverse right column
            for (int j = topRow; j <= bottomRow; j++) {
                result.add(matrix[j][rightColumn]);
            }
            rightColumn--;

            // Traverse bottom row if applicable
            if (topRow <= bottomRow) {
                for (int j = rightColumn; j >= leftColumn; j--) {
                    result.add(matrix[bottomRow][j]);
                }
                bottomRow--;
            }

            // Traverse left column if applicable
            if (leftColumn <= rightColumn) {
                for (int j = bottomRow; j >= topRow; j--) {
                    result.add(matrix[j][leftColumn]);
                }
                leftColumn++;
            }
        }

        return result;
    }


    /**
     * Rotates the given matrix 90 degrees clockwise.
     *
     * @param matrix The matrix to be rotated.
     */
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reverseRows(matrix);
    }

    /**
     * Transposes the given matrix.
     *
     * @param matrix The matrix to be transposed.
     */
    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * Reverses the order of elements in each row of the given matrix.
     *
     * @param matrix The matrix to be processed.
     */
    public void reverseRows(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }

    }


    /**
     * Calculates the number of days to wait for a warmer temperature for each given day.
     * <p>
     * For each day's temperature in the input array, this method determines how many days
     * one would have to wait for a temperature that's warmer. If no warmer temperature is
     * found in the subsequent days, the result for that day is set to 0.
     *
     * @param dailyTemps An array of daily temperatures.
     * @return An array representing the number of days to wait for a warmer temperature for each day.
     */
    public int[] daysToWaitForWarmerTemp(int[] dailyTemps) {
        int[] daysToWait = new int[dailyTemps.length];

        for (int currentDay = 0; currentDay < dailyTemps.length; currentDay++) {
            for (int nextDay = currentDay + 1; nextDay < dailyTemps.length; nextDay++) {
                if (dailyTemps[nextDay] > dailyTemps[currentDay]) {
                    daysToWait[currentDay] = nextDay - currentDay;
                    break;
                }
            }
        }

        return daysToWait;
    }


    /**
     * Adds two numbers represented by two linked lists. Each node contains a single digit.
     * The digits are stored in reverse order, meaning that the 1's digit is at the head of the list.
     *
     * @param l1 The first linked list representing the first number.
     * @param l2 The second linked list representing the second number.
     * @return A linked list representing the sum of the two numbers.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead, current1 = l1, current2 = l2;
        int carry = 0;

        while (current1 != null || current2 != null) {
            int x = (current1 != null) ? current1.val : 0;
            int y = (current2 != null) ? current2.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

            if (current1 != null) current1 = current1.next;
            if (current2 != null) current2 = current2.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
    }


    /**
     * Checks if the input string is a palindrome by considering only
     * alphanumeric characters and ignoring cases.
     *
     * @param s The string to be checked.
     * @return {@code true} if the pruned string is a palindrome; {@code false} otherwise.
     */
    public boolean isPalindrome2(String s) {
        StringBuilder pruned = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (isAlphanumeric(currentChar)) {
                pruned.append(currentChar);
            }
        }

        String prunedString = pruned.toString().toLowerCase();
        String reversed = reverse(prunedString);

        return reversed.equals(prunedString);
    }

    /**
     * Determines if the given character is alphanumeric.
     *
     * @param c The character to be checked.
     * @return {@code true} if the character is alphanumeric; {@code false} otherwise.
     */
    private boolean isAlphanumeric(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }

    /**
     * Returns the reversed version of the input string.
     *
     * @param s The string to be reversed.
     * @return The reversed string.
     */
    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }


    /**
     * Determines if the target array can be formed by merging the given triplets.
     *
     * <p>This method checks if a combination of valid triplets can achieve the specified target.
     * A triplet is deemed valid if each of its elements is less than or equal to the corresponding
     * element in the target.</p>
     *
     * @param triplets An array of 3-element int arrays.
     * @param target   A 3-element int array to be matched using triplets.
     * @return True if the target can be achieved by merging valid triplets, otherwise False.
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] result = new int[3];

        for (int[] triplet : triplets) {
            if (isValidTriplet(triplet, target)) {
                for (int i = 0; i < 3; i++) {
                    result[i] = Math.max(triplet[i], result[i]);
                }
            }
        }

        return Arrays.equals(result, target);
    }

    /**
     * Checks if a given triplet is valid against the target.
     *
     * @param triplet The triplet to be checked.
     * @param target  The target array.
     * @return True if the triplet is valid, otherwise False.
     */
    private boolean isValidTriplet(int[] triplet, int[] target) {
        for (int i = 0; i < 3; i++) {
            if (triplet[i] > target[i]) {
                return false;
            }
        }
        return true;
    }


}


    /**
     * Determines partitions in the input string such that:
     * - Each letter appears only in one partition.
     * - Concatenating the partitions results in the original string.
     *
     * @param s Input string containing lowercase English letters
     * @return List of integers, each denoting the length of a partition
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();

        // get max index
        int[] indexes = new int[26];
        Arrays.fill(indexes, -300);
        for (int i = 0; i < s.length(); i++) {

            if (i > indexes[s.charAt(i) - 'a']) {
                indexes[s.charAt(i) - 'a'] = i;
            }
        }

        for (int i = 0; i < 26; i++) {
            System.out.println(indexes[i]);
        }


        int i = 0;

        int pre = 0;

        while (i < s.length()) {


            int j = indexes[s.charAt(i) - 'a'];
            while (i <= j) {

                if (indexes[s.charAt(i) - 'a'] > j) {
                    j = indexes[s.charAt(i) - 'a'];

                }

                i++;

            }


            result.add((i - pre));
            pre = i;
            i = j + 1;

        }

        return result;


    }


    /**
     * Calculates the minimum time required for all fresh oranges to rot.
     *
     * @param grid A 2D grid representing the state of oranges.
     * @return The minimum time in minutes or -1 if not all oranges can rot.
     */
    public int orangesRotting(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int minute = 0;
        Queue<int[]> q = new LinkedList<>();

        // Count fresh oranges and enqueue rotten oranges
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 2) {
                    int[] a = new int[2];
                    a[0] = i;
                    a[1] = j;
                    q.add(a);
                } else if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        while (!q.isEmpty()) {
            int count1 = q.size();
            for (int z = 0; z < count1; z++) {
                int[] re = q.poll();
                int i = re[0];
                int j = re[1];
                if (valid_index(i + 1, j, r, c, grid)) {
                    grid[i + 1][j] = 2;
                    int[] a = new int[2];
                    a[0] = i + 1;
                    a[1] = j;
                    q.add(a);
                }
                // Similar logic for other directions
            }
            minute++;
        }

        if (!hasFresh(grid)) {
            return -1;
        }
        return Math.max(minute - 1, 0);
    }

    /**
     * Checks if there are any fresh oranges left.
     *
     * @param grid A 2D grid representing the state of oranges.
     * @return True if there are no fresh oranges, false otherwise.
     */
    public boolean hasFresh(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the given indices are valid and point to a fresh orange.
     *
     * @param i    Row index.
     * @param j    Column index.
     * @param r    Number of rows in the grid.
     * @param c    Number of columns in the grid.
     * @param grid A 2D grid representing the state of oranges.
     * @return True if the indices are valid and point to a fresh orange, false otherwise.
     */
    public boolean valid_index(int i, int j, int r, int c, int[][] grid) {
        if ((i < 0 || j < 0 || i >= r || j >= c) || grid[i][j] != 1) {
            return false;
        }
        return true;
    }

    /**
     * Given a list of stones, each time we take the two heaviest stones and smash them together.
     * - If the stones are of the same weight, both stones are destroyed.
     * - If the stones are of different weights, the smaller stone is destroyed and the remaining
     * weight of the larger stone is put back into the list.
     * This method returns the weight of the last remaining stone, if there is one, or 0 if all stones are destroyed.
     *
     * @param stones An array of integers representing the weights of the stones.
     * @return The weight of the last stone or 0 if all stones are destroyed.
     */
    public int lastStoneWeight(int[] stones) {

        List<Integer> result = new ArrayList<>();
        for (int j : stones) {
            result.add(j);
        }

        while (result.size() > 1) {
            Collections.sort(result);
            int index1 = result.size() - 1;
            int index2 = index1 - 1;
            int y = result.get(index1);
            int x = result.get(index2);

            if (x == y) {
                result.remove(index1);
                result.remove(index2);
            }

            if (x != y) {
                int n = y - x;
                result.remove(index1);
                result.remove(index2);
                result.add(n);
            }
        }

        if (result.size() == 0) {
            return 0;
        } else {
            return result.get(0);
        }
    }


    /**
     * Finds all unique triplets in the array which gives the sum of zero.
     *
     * <p>The function sorts the array first, then uses two pointers to scan through the sorted array.
     * If a triplet is found, it's added to a set to ensure uniqueness. The result is then converted
     * to a list of lists for the output.</p>
     *
     * <p>This function assumes that the input list may have duplicates and the output should not contain
     * any duplicate triplets.</p>
     *
     * @param nums The input array of integers.
     * @return A list of lists containing all unique triplets that sum up to zero.
     */

    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);

            int i=0;
            //int j=i+1;
            //int k=numslength-1;
            Set<List<Integer>> result = new HashSet<>();
            while(i<nums.length-2){
                int j=i+1;
                int k=nums.length-1;

                while(j<k){

                    if(nums[i]+nums[j]+nums[k]==0){
                        List<Integer> subre=new ArrayList<>();
                        subre.add(nums[i]);
                        subre.add(nums[j]);
                        subre.add(nums[k]);
                        result.add(subre);
                        j++;
                        k--;
                    }

                    if(nums[i]+nums[j]+nums[k]<0){
                        j++;

                    }

                    if(nums[i]+nums[j]+nums[k]> 0){
                        k--;

                    }

                }
                i++;

            }


            List<List<Integer>>  finalResult=new ArrayList<>();

            for(List<Integer> a: result){

                finalResult.add(a);
            }

            return finalResult;

        }

    }


}


