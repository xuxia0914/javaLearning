package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 *
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        System.out.println(new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{5,2,6,1}));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        if(nums==null||nums.length==0) {
            return ans;
        }
        int n = nums.length;
        int[] ret = new int[n];
        TreeNode root = new TreeNode(nums[n-1], 0);
        ans.add(0);
        for(int i=n-2;i>=0;i--) {
            insert(root, new TreeNode(nums[i], 0), ret, i);
        }
        for(int i=0;i<n;i++) {
            ans.add(ret[i]);
        }
        return ans;
    }

    private void insert(TreeNode root, TreeNode node, int[] ret, int i) {
        if(node.val>root.val) {
            ret[i] += root.count+1;
            if(root.right==null) {
                root.right = node;
            }else {
                insert(root.right, node, ret, i);
            }
        }else {
            root.count++;
            if(root.left==null) {
                root.left = node;
            }else {
                insert(root.left, node, ret, i);
            }
        }
    }

    //TLE
    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        if(nums==null||nums.length==0) {
            return ans;
        }
        int n = nums.length;
        for(int i=n-1;i>=0;i--) {
            int cnt = 0;
            for(int j=i+1;j<n;j++) {
                if(nums[j]<nums[i]) {
                    cnt++;
                }
            }
            ans.add(0, cnt);
        }
        return ans;
    }

}

class TreeNode {

    int val;
    //左子树的节点数
    int count;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, int count) {
        this.val = val;
        this.count = count;
    }

}
