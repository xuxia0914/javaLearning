package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 655. 输出二叉树
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。
 * 根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。
 * 你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。
 * 即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。
 * 然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 *
 * 示例 1:
 * 输入:
 *      1
 *     /
 *    2
 * 输出:
 * [["", "1", ""],
 *  ["2", "", ""]]
 *
 * 示例 2:
 * 输入:
 *      1
 *     / \
 *    2   3
 *     \
 *      4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 *  ["", "2", "", "", "", "3", ""],
 *  ["", "", "4", "", "", "", ""]]
 *
 * 示例 3:
 * 输入:
 *       1
 *      / \
 *     2   5
 *    /
 *   3
 *  /
 * 4
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 *
 * 注意: 二叉树的高度在范围 [1, 10] 中。
 */
public class PrintBinaryTree {

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(new Integer[]{1,2});
        System.out.println(new PrintBinaryTree().printTree(root));
    }

    public List<List<String>> printTree(BinaryTreeNode root) {
        int m = height(root);
        int n = (int)Math.pow(2, m)-1;
        String[][] resultArr = new String[m][n];
        for(int i=0;i<m;i++) {
            Arrays.fill(resultArr[i], "");
        }
        printTree(resultArr, root, 0, 0, n-1);
        List<List<String>> result = new LinkedList<>();
        for(int i=0;i<m;i++) {
            List<String> list = new LinkedList<>();
            for(int j=0;j<n;j++) {
                list.add(resultArr[i][j]);
            }
            result.add(list);
        }
        return result;
    }

    public void printTree(String[][] resultArr, BinaryTreeNode root, int level, int start, int end) {
        if(root==null) {
            return;
        }
        int mid = (start+end)/2;
        resultArr[level][mid] = String.valueOf(root.val);
        printTree(resultArr, root.left, level+1, start, mid-1);
        printTree(resultArr, root.right, level+1, mid+1, end);
    }

    public int height(BinaryTreeNode root) {
        if(root==null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right))+1;
    }

}
