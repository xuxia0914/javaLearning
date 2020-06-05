package cn.leetcode.xux.general.midium;

import java.util.Arrays;

/**
 *Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *For example, given array S = {-1 2 1 -4}, and target = 1.
 *The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {

    /**
     * O(n2)
     * @param ia
     * @return
     */
    public static int solution2(int[] ia, int target) {
        if(ia==null||ia.length<3) {
            return -1;
        }
        Arrays.sort(ia);
        int closest = Integer.MAX_VALUE;
        int result = -1;
        for(int i=0;i<ia.length-2;i++) {
            int left=i+1,right=ia.length-1;
            if(i>0&&ia[i]==ia[i-1]) {
                continue;
            }
            while(left<right) {
                int currSum = ia[i] + ia[left] + ia[right];
                int currDis = currSum - target;
                if (currDis > 0) {
                    if (currDis < closest) {
                        closest = currDis;
                        result = currSum;
                    }
                    right--;
                    while (right < ia.length - 1 && ia[right] == ia[right + 1]) {
                        right--;
                    }
                } else if (currDis < 0) {
                    if (-currDis < closest) {
                        closest = -currDis;
                        result = currSum;
                    }
                    left++;
                    while (ia[left] == ia[left - 1]) {
                        left++;
                    }
                } else {
                    return target;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution2(new int[]{-1, 2, 1, 4}, 8));
    }

}
