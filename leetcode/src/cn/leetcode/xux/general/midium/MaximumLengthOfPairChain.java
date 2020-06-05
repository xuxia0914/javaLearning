package cn.leetcode.xux.general.midium;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */
public class MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        if(pairs==null||pairs.length==0) {
            return 0;
        }
        quickSort(pairs, 0, pairs.length-1);
        int res = 1;
        int curr = pairs[0][1];
        for(int i=1;i<pairs.length;i++) {
            if(pairs[i][0]>curr) {
                res++;
                curr = pairs[i][1];
            }
            if(pairs[i][1]<curr) {
                curr = pairs[i][1];
            }
        }
        return res;
    }

    public void quickSort(int[][] pairs, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int[] key = pairs[left];
        while(left<right) {
            while(left<right&&pairs[right][0]>=key[0]) {
                right--;
            }
            while(left<right&&pairs[left][0]<=key[0]) {
                left++;
            }
            if(left<right) {
                int[] tmp = pairs[left];
                pairs[left] = pairs[right];
                pairs[right] = tmp;
            }
        }
        pairs[start] = pairs[right];
        pairs[right] = key;
        quickSort(pairs, start, right-1);
        quickSort(pairs, right+1, end);
    }

}
