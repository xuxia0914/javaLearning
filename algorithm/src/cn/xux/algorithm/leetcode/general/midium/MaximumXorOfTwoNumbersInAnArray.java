package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 *
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 *
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 *
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 0 <= nums[i] <= 231 - 1
 */
public class MaximumXorOfTwoNumbersInAnArray {

    //二叉树
    public int findMaximumXOR(int[] nums) {
        if(nums==null||nums.length<2) {
            return 0;
        }
        TreeNode root = new TreeNode();
        TreeNode curr;
        for(int num : nums) {
            curr = root;
            for(int i=30;i>=0;i--) {
                if((num>>i)%2==0) {
                    if(curr.left==null) {
                        curr.left = new TreeNode();
                    }
                    curr = curr.left;
                }else {
                    if(curr.right==null) {
                        curr.right = new TreeNode();
                    }
                    curr = curr.right;
                }
            }
        }
        return dfs(root, root, 30);
    }

    public int dfs(TreeNode node1, TreeNode node2, int n) {
        if(n<0) {
            return 0;
        }
        if(node1==null||node2==null) {
            return 0;
        }
        int res = 0;
        if((node1.left!=null&&node2.right!=null)
                ||(node1.right!=null&&node2.left!=null)) {
            res += 1<<n;
            return res+Math.max(dfs(node1.left, node2.right, n-1), dfs(node1.right, node2.left, n-1));
        }
        return Math.max(dfs(node1.left, node2.left, n-1), dfs(node1.right, node2.right, n-1));
    }

    class TreeNode {
        TreeNode left; //左子树不为空表示下一位有0
        TreeNode right; //右子树不为空表示下一位有1
    }

    // a^b = c ->a^c=b,b^c=a
    public int findMaximumXOR1(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            //mask从0x01 ->0x03等一直变化，取后i位
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            //tmp表示当前的最大
            int tmp = max | (1 << i);
            for(int prefix : set){
                //证明存在一个数使得tmp^x = prefix
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumXorOfTwoNumbersInAnArray mx = new MaximumXorOfTwoNumbersInAnArray();
        System.out.println(mx.findMaximumXOR1(new int[]{3, 10, 5, 25, 2, 8}));
    }

}