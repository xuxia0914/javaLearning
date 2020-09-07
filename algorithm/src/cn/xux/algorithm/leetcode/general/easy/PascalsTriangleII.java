package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * 进阶：
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 */
public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if(rowIndex<0) {
            return res;
        }
        res.add(1);
        for(int i=1;i<rowIndex;i++) {
            long longVal = (long)res.get(i-1)*(rowIndex-i)/i;
            res.add((int)longVal);
        }
        return res;
    }

    public List<Integer> getRow1(int rowIndex) {
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
