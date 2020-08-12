package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 510 二叉搜索树中的中序后继II（二叉树）
 * 给定一棵二叉搜索树和其中的一个节点，找到该节点在树中的中序后继。
 * 一个结点 p 的中序后继是键值比 p.val大所有的结点中键值最小的那个。
 * 你可以直接访问结点，但无法直接访问树。每个节点都会有其父节点的引用。
 *
 * 示例 1:
 * 输入:
 * root = {"$id":"1","left":{"$id":"2","left":null,"parent":{"$ref":"1"},"right":null,"val":1},"parent":null,"right":{"$id":"3","left":null,"parent":{"$ref":"1"},"right":null,"val":3},"val":2}
 * p = 1
 * 输出: 2
 * 解析: 1的中序后继结点是2。注意p和返回值都是Node类型的。
 *
 * 示例 2:
 * 输入:
 * root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":1},"parent":{"$ref":"2"},"right":null,"val":2},"parent":{"$ref":"1"},"right":{"$id":"5","left":null,"parent":{"$ref":"2"},"right":null,"val":4},"val":3},"parent":null,"right":{"$id":"6","left":null,"parent":{"$ref":"1"},"right":null,"val":6},"val":5}
 * p = 6
 * 输出: null
 * 解析: 该结点没有中序后继，因此返回null。
 *
 * 示例 3:
 * 输入:
 * root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":2},"parent":{"$ref":"2"},"right":{"$id":"5","left":null,"parent":{"$ref":"3"},"right":null,"val":4},"val":3},"parent":{"$ref":"1"},"right":{"$id":"6","left":null,"parent":{"$ref":"2"},"right":{"$id":"7","left":{"$id":"8","left":null,"parent":{"$ref":"7"},"right":null,"val":9},"parent":{"$ref":"6"},"right":null,"val":13},"val":7},"val":6},"parent":null,"right":{"$id":"9","left":{"$id":"10","left":null,"parent":{"$ref":"9"},"right":null,"val":17},"parent":{"$ref":"1"},"right":{"$id":"11","left":null,"parent":{"$ref":"9"},"right":null,"val":20},"val":18},"val":15}
 * p = 15
 * 输出: 17
 *
 * 示例 4:
 * 输入:
 * root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":2},"parent":{"$ref":"2"},"right":{"$id":"5","left":null,"parent":{"$ref":"3"},"right":null,"val":4},"val":3},"parent":{"$ref":"1"},"right":{"$id":"6","left":null,"parent":{"$ref":"2"},"right":{"$id":"7","left":{"$id":"8","left":null,"parent":{"$ref":"7"},"right":null,"val":9},"parent":{"$ref":"6"},"right":null,"val":13},"val":7},"val":6},"parent":null,"right":{"$id":"9","left":{"$id":"10","left":null,"parent":{"$ref":"9"},"right":null,"val":17},"parent":{"$ref":"1"},"right":{"$id":"11","left":null,"parent":{"$ref":"9"},"right":null,"val":20},"val":18},"val":15}
 * p = 13
 * 输出: 15
 *
 * 注意:
 * 如果给定结点不存在中序后继，返回null。
 * 树中各结点的值均保证唯一。
 * 注意我们使用的是Node类型而不是TreeNode类型，它们的字符串表示不一样。
 */
public class InorderSuccessorInBstII {

    public Node inorderSuccessor(Node x) {
        if(x.right!=null) {
            Node ans = x.right;
            while(ans.left!=null) {
                ans = ans.left;
            }
            return ans;
        }else {
            Node ans = x;
            while(ans.parent!=null&&ans==ans.parent.right) {
                ans = ans.parent;
            }
            return ans.parent;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

}
