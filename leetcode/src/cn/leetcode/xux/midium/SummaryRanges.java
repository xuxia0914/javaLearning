package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * Example 1:
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * Example 2:
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
public class SummaryRanges {

    public static List<String> solution(int[] array) {
        List<String> result = new ArrayList<String>();
        if(array==null||array.length==0) {
            return result;
        }
        int tmp = array[0];
        for(int i=1;i<array.length;i++) {
            if(array[i]==array[i-1]+1) {
                continue;
            }else {
                if(array[i-1]==tmp) {
                    result.add(tmp+"");
                }else {
                    result.add(tmp+"->"+array[i-1]);
                }
                tmp = array[i];
            }
        }
        if(array[array.length-1]==tmp) {
            result.add(tmp+"");
        }else {
            result.add(tmp+"->"+array[array.length-1]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,1,2,4,5,7}));
        System.out.println(solution(new int[]{0,2,3,4,6,8,9}));
    }

}
