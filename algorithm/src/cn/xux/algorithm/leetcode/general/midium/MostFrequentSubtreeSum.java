package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 508. 出现次数最多的子树元素和
 * 给出二叉树的根，找出出现次数最多的子树元素和。一个结点的子树元素和定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。然后求出出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的元素（不限顺序）。
 *
 * 示例 1
 * 输入:
 *   5
 *  /  \
 * 2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 *
 * 示例 2
 * 输入:
 *   5
 *  /  \
 * 2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 *
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 */
public class MostFrequentSubtreeSum {

    Map<Integer, Integer> map = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        helper(root);
        List<Integer> list = new ArrayList<>();
        for(Integer i : map.keySet()) {
            if(list.size()==0) {
                list.add(i);
            }else {
                if(map.get(list.get(0))==map.get(i)) {
                    list.add(i);
                }else if(map.get(list.get(0))<map.get(i)) {
                    list.clear();
                    list.add(i);
                }
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<res.length;i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public int helper(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int res = root.val+helper(root.left)+helper(root.right);
        map.put(res, map.getOrDefault(res, 0)+1);
        return res;
    }

}
