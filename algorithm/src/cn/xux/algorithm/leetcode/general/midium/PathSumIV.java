package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 二叉树的路径和之四
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 * For each integer in this list:
 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 * The units digit represents the value V of this node, 0 <= V <= 9.
 * Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.
 * Example 1:
 * Input: [113, 215, 221]
 * Output: 12
 * Explanation:
 * The tree that the list represents is:
 *     3
 *    / \
 *   5   1
 * The path sum is (3 + 5) + (3 + 1) = 12.
 * Example 2:
 * Input: [113, 221]
 * Output: 4
 * Explanation:
 * The tree that the list represents is:
 *     3
 *      \
 *       1
 * The path sum is (3 + 1) = 4.
 */
public class PathSumIV {

    int res;

    public int pathSum(int[] nums) {
        res = 0;
        if(nums==null||nums.length==0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num/10, num%10);
        }
        helper(map, nums[0], 0);
        return res;
    }

    public void helper(Map<Integer, Integer> map, int start, int curr) {
        int leftKey = (start/100+1)*10+start%100/10*2-1;
        int rightKey = (start/100+1)*10+start%100/10*2;
        if(!map.containsKey(leftKey)&&!map.containsKey(rightKey)) {
            res += curr + start%10;
            return;
        }
        if(map.containsKey(leftKey)) {
            helper(map, leftKey*10+map.get(leftKey), curr+start%10);
        }
        if(map.containsKey(rightKey)) {
            helper(map, rightKey*10+map.get(rightKey), curr+start%10);
        }
    }

    public static void main(String[] args) {
        PathSumIV ps = new PathSumIV();
        System.out.println(ps.pathSum(new int[]{113, 215, 221}));
        System.out.println(ps.pathSum(new int[]{113, 221}));
    }

}
