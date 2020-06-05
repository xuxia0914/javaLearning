package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 */
public class FourSum {

    public static List<List<Integer>> solution(int[] S, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(S==null||S.length<4) {
            return  result;
        }
        Arrays.sort(S);
        int n = S.length;
        for(int i=0;i<n-3;i++) {
            if(i>0&&S[i]==S[i-1]) {
                continue;
            }
            for(int j=i+1;j<n-2;j++) {
                if(j>i+1&&S[j]==S[j-1]) {
                    continue;
                }
                int left = j+1, right = n-1;
                while(left<right) {
                    int sum = S[i]+S[j]+S[left]+S[right];
                    if(sum==target) {
                        List<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(S[i]);
                        tmp.add(S[j]);
                        tmp.add(S[left]);
                        tmp.add(S[right]);
                        result.add(tmp);
                        left++;
                        while(S[left]==S[left-1]) {
                            left++;
                        }
                        right--;
                        while(S[right]==S[right+1]) {
                            right--;
                        }
                    }else if(sum<target) {
                        left++;
                        while(S[left]==S[left-1]) {
                            left++;
                        }
                    }else {
                        right--;
                        while(S[right]==S[right+1]) {
                            right--;
                        }
                    }
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(solution(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(solution(new int[]{4,-9,-2,-2,-7,9,9,5,10,-10,4,5,2,-4,-2,4,-9,5}, -13));
    }

}
