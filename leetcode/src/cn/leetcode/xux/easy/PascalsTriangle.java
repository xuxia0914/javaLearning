package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * Example:
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows<1) {
            return res;
        }
        for(int i=1;i<=numRows;i++) {
            List<Integer> curr = new ArrayList<>();
            for(int j=0;j<i;j++) {
                if(j==0||j==i-1) {
                    curr.add(1);
                }else {
                    curr.add(res.get(res.size()-1).get(j-1)+res.get(res.size()-1).get(j));
                }
            }
            res.add(curr);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PascalsTriangle().generate(6));
    }

}
