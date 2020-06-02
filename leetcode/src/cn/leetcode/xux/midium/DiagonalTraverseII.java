package cn.leetcode.xux.midium;

import java.util.List;
import java.util.Stack;

/**
 * 1424. 对角线遍历 II
 * 给你一个列表 nums ，里面每一个元素都是一个整数列表。
 * 请你依照下面各图的规则，按顺序(左下->右上)返回 nums 中对角线上的整数。
 *
 * 示例 1：
 * 输入：nums = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,4,2,7,5,3,8,6,9]
 *
 * 示例 2：
 * 输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 *
 * 示例 3：
 * 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * 输出：[1,4,2,5,3,8,6,9,7,10,11]
 *
 * 示例 4：
 * 输入：nums = [[1,2,3,4,5,6]]
 * 输出：[1,2,3,4,5,6]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= nums[i][j] <= 10^9
 * nums 中最多有 10^5 个数字。
 */
public class DiagonalTraverseII {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Stack<Integer>[] map = new Stack[100000];
        int len = 0;
        int maxIdx = 0;
        for(int i=0;i<nums.size();i++) {
            List<Integer> list = nums.get(i);
            len += list.size();
            for(int j=0;j<list.size();j++) {
                int key = i+j;
                if(map[key]==null) {
                    map[key] = new Stack<>();
                }
                map[key].push(nums.get(i).get(j));
            }
            maxIdx = Math.max(maxIdx, i+list.size()-1);
        }
        int[] ans = new int[len];
        int idx = 0;
        for(int i=0;i<=maxIdx;i++) {
            Stack<Integer> curr = map[i];
            while(!curr.isEmpty()) {
                ans[idx++] += curr.pop();
            }
        }
        return ans;
    }

}
