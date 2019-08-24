package cn.leetcode.xux.easy;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 */
public class BestTimeToBuyAndSellStockI {

    public static int solution(int[] array) {
        int maximumProfit = 0;
        int min = array[0];
        for(int i : array) {
            if(i<min) {
                min = i;
            }
            if(i>min) {
                maximumProfit = Math.max(i-min, maximumProfit);
            }
        }
        return maximumProfit;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{7,1,5,3,6,4}));
        System.out.println(solution(new int[]{7,6,4,3,1}));
    }

}
