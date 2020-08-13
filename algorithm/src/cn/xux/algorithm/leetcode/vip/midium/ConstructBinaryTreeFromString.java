package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 536. 字符串构造二叉树
 * 你需要根据一个由括号和整数组成的字符串中构造一颗二叉树。
 * 输入的整个字符串表示一个二叉树。它包含一个整数，以及其后跟随的0~2对括号。
 * 该整数表示根的值，而一对括号里的字符串代表一个具有相同结构的子二叉树。
 * 如果一个节点含有子节点，你应该先构造它的左子节点。
 *
 * 样例1:
 * 输入: "-4(2(3)(1))(6(5))"
 * 输出: {-4,2,6,3,1,5}
 * 说明:
 * 输出应该如下所示：
 *       -4
 *      /  \
 *     2    6
 *    / \   /
 *   3   1 5
 *
 * 样例2:
 * 输入: "1(-1)"
 * 输出: {1,-1}
 * 说明:
 * 输出应该如下所示：
 *      1
 *     /
 *   -1
 *
 * 注意事项
 * 在输入字符串中只有'('，')'，'-'和'0' ~ '9'。
 * 空树表示为""而不是"()"。
 */
public class ConstructBinaryTreeFromString {

    public TreeNode str2tree(String s) {
        if("".equals(s)) {
            return null;
        }
        if(!s.contains("(")) {
            return new TreeNode(Integer.parseInt(s));
        }
        int leftStart = s.indexOf("(");
        int val = Integer.parseInt(s.substring(0, leftStart));
        TreeNode ans = new TreeNode(val);
        int cnt = 1;
        int leftEnd = leftStart+1;
        for(;leftEnd<s.length();leftEnd++) {
            if(s.charAt(leftEnd)=='(') {
                cnt++;
            }else if(s.charAt(leftEnd)==')') {
                if(--cnt==0) {
                    break;
                }
            }
        }
        String leftStr = s.substring(leftStart+1, leftEnd);
        ans.left = str2tree(leftStr);
        if(leftEnd!=s.length()-1) {
            int rightStart = leftEnd+2;
            String rightStr = s.substring(rightStart,s.length()-1);
            ans.right = str2tree(rightStr);
        }
        return ans;
    }

}
