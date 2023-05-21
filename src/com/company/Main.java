package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


}
