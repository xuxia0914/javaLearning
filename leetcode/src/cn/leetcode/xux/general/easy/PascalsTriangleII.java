package cn.leetcode.xux.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角II
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 * Input: 3
 * Output: [1,3,3,1]
 *
 * Follow up:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if(rowIndex<0) {
            return res;
        }
        res.add(1);
        for(int i=1;i<=rowIndex;i++) {
            res.add(res.get(res.size()-1));
            for(int j=i-1;j>=1;j--) {
                res.set(j, res.get(j)+res.get(j-1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangleII().getRow(6));
    }

}
