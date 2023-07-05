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
     * @param i The current row index.
     * @param j The current column index.
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
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





}
