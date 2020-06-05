package cn.leetcode.xux.general.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1361. 验证二叉树
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，
 * 其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 *
 * 示例 1：
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * 输出：true
 *
 * 示例 2：
 * 输入：n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * 输出：false
 *
 * 示例 3：
 * 输入：n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * 输出：false
 *
 * 示例 4：
 * 输入：n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * 输出：false
 *
 * 提示：
 * 1 <= n <= 10^4
 * leftChild.length == rightChild.length == n
 * -1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class ValidateBinaryTreeNodes {

    public static void main(String[] args) {
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodes(
                4,
                new int[]{1,2,0,-1},
                new int[]{-1,-1,-1,-1}
        ));
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        //先找到root
        boolean[] hasParent = new boolean[n];
        for(int i=0;i<n;i++) {
            if(leftChild[i]!=-1) {
                if(hasParent[leftChild[i]]) {
                    return false;
                }
                hasParent[leftChild[i]] = true;
            }
            if(rightChild[i]!=-1) {
                if(hasParent[rightChild[i]]) {
                    return false;
                }
                hasParent[rightChild[i]] = true;
            }
        }
        boolean flag = false;
        int root = -1;
        for(int i=0;i<n;i++) {
            boolean b = hasParent[i];
            if(!b) {
                if(flag) {
                    return false;
                }
                flag = true;
                root = i;
            }
        }
        if(root==-1) {
            return false;
        }
        //再bfs遍历树判断是否符合
        boolean[] visited = new boolean[n];
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;
        cnt++;
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(leftChild[curr]!=-1) {
                if(visited[leftChild[curr]]) {
                    return false;
                }
                visited[leftChild[curr]] = true;
                queue.offer(leftChild[curr]);
                cnt++;
            }
            if(rightChild[curr]!=-1) {
                if(visited[rightChild[curr]]) {
                    return false;
                }
                visited[rightChild[curr]] = true;
                queue.offer(rightChild[curr]);
                cnt++;
            }
        }
        return cnt==n;

    }

}
