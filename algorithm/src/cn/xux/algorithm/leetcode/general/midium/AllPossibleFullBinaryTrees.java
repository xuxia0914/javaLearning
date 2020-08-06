package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 894. 所有可能的满二叉树
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 * 答案中每个树的每个结点都必须有 node.val=0。
 * 你可以按任何顺序返回树的最终列表。
 *
 * 示例：
 * 输入：7
 * 输出：[[0,0,0,null,null,0,0,null,null,0,0],
 *      [0,0,0,null,null,0,0,0,0],
 *      [0,0,0,0,0,0,0],
 *      [0,0,0,0,0,null,null,null,null,0,0],
 *      [0,0,0,0,0,null,null,0,0]]
 *
 * 提示：
 * 1 <= N <= 20
 */
public class AllPossibleFullBinaryTrees {

    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> result = new ArrayList<>();
        if(N%2==0) {
            return result;
        }
        if(N==1) {
            result.add(new TreeNode(0));
            return result;
        }
        for(int i=1;i<N;i+=2) {
            List<TreeNode> lefts = allPossibleFBT(i);
            List<TreeNode> rights = allPossibleFBT(N-1-i);
            for(int j=0;j<lefts.size();j++) {
                for(int k=0;k<rights.size();k++) {
                    TreeNode curr = new TreeNode(0);
                    curr.left = lefts.get(j);
                    curr.right = rights.get(k);
                    result.add(curr);
                }
            }
        }
        return result;
    }

}
