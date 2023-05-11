package com.company;

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


}
