package cn.leetcode.xux.common;

import javax.management.openmbean.InvalidOpenTypeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for(int i=1;i<=len;i++) {
            for(int j=0;j<i;j++) {
                if(dp[j]&&wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    int result;

    public int maxPathSum(BinaryTreeNode root) {
        result = Integer.MIN_VALUE;
        helper(root);
        return result;
    }

    public int helper(BinaryTreeNode root) {
        if(root==null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int sum = root.val;
        if(left>0) {
            sum += left;
        }
        if(right>0) {
            sum += right;
        }
        result = Math.max(result, sum);
        return Math.max(Math.max(left, right), 0) + root.val;
    }

    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<2) {
            return 0;
        }
        int min = prices[0];
        int result = 0;
        for(int i=1;i<prices.length;i++) {
            if(prices[i]>min) {
                result = Math.max(result, prices[i]-min);
            }
            if(prices[i]<min) {
                min = prices[i];
            }
        }
        return result;
    }

    public void flatten(BinaryTreeNode root) {
        while(root!=null) {
            if(root.left==null) {
                root = root.right;
                continue;
            }
            BinaryTreeNode pre = root.left;
            while(pre.right!=null) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }

    public BinaryTreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0) {
            return null;
        }
        int n = preorder.length;
        return buildTree(preorder, 0, n-1, inorder, 0, n-1);
    }

    public BinaryTreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart>preEnd||inStart>inEnd) {
            return null;
        }
        BinaryTreeNode result = new BinaryTreeNode(preorder[preStart]);
        for(int i=inStart;i<=inEnd;i++) {
            if(inorder[i]==preorder[preStart]) {
                result.left = buildTree(preorder, preStart+1, preStart+i-inStart, inorder, inStart, i-1);
                result.right = buildTree(preorder, preStart+i-inStart+1, preEnd, inorder, i+1, inEnd);
                break;
            }
        }
        return result;
    }

    public int maxDepth(BinaryTreeNode root) {
        if(root==null) {
            return 0;
        }
        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int largestRectangleArea(int[] heights) {
        result = 0;
        largestRectangleArea(heights, 0, heights.length-1);
        return result;
    }

    public void largestRectangleArea(int[] heights, int start, int end) {
        if(start>end) {
            return;
        }
        int minIndex = start;
        for(int i=start+1;i<=end;i++) {
            if(heights[i]<heights[minIndex]) {
                minIndex = i;
            }
        }
        int curr = heights[minIndex]*(end-start+1);
        result = Math.max(result, curr);
        largestRectangleArea(heights, start, minIndex-1);
        largestRectangleArea(heights, minIndex+1, end);
    }

    public List<List<Integer>> levelOrder(BinaryTreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root==null) {
            return result;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size;
        BinaryTreeNode curr;
        while(!queue.isEmpty()) {
            size = queue.size();
            List<Integer> list = new LinkedList<>();
            while(size-->0) {
                curr = queue.poll();
                list.add(curr.val);
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    public boolean isSymmetric(BinaryTreeNode root) {
        if(root==null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(BinaryTreeNode node1, BinaryTreeNode node2) {
        if(node1==null&&node2==null) {
            return true;
        }
        if(node1==null||node2==null) {
            return false;
        }
        if(node1.val!=node2.val) {
            return false;
        }
        return isSymmetric(node1.left, node2.right)&&isSymmetric(node1.right, node2.left);
    }

    public boolean isValidBST(BinaryTreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(BinaryTreeNode root, Integer low, Integer high) {
        if(root==null) {
            return true;
        }
        if(low!=null&&low>=root.val) {
            return false;
        }
        if(high!=null&&high<=root.val) {
            return false;
        }
        if(!isValidBST(root.left, low, root.val)) {
            return false;
        }
        if(isValidBST(root.right, root.val, high)) {
            return false;
        }
        return true;
    }

    public List<Integer> inorderTraversal(BinaryTreeNode root) {
        List<Integer> result = new LinkedList<>();
        helper(result, root);
        return result;
    }

    public void helper(List<Integer> result, BinaryTreeNode node) {
        if(node==null) {
            return;
        }
        helper(result, node.left);
        result.add(node.val);
        helper(result, node.right);
    }

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++) {
            for(int j=0;j<i;j++) {
                dp[i] += dp[j]*dp[i-1-j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Test test = new Test();
        /*Chopsticks chopsticks = new Chopsticks();
        Bowl bowl = new Bowl();
        User user1 = new User(chopsticks, bowl, false);
        User user2 = new User(chopsticks, bowl, true);
        new Thread(user1).start();
        new Thread(user2).start();*/
    }

}

class Chopsticks {
    boolean status = false;
}

class Bowl {
    boolean status = false;
}

class User implements Runnable {

    boolean flag;

    Chopsticks chopsticks;

    Bowl bowl;

    User(Chopsticks chopsticks, Bowl bowl, boolean flag) {
        this.chopsticks = chopsticks;
        this.bowl = bowl;
        this.flag = flag;
    }

    void getChopsticks() {
        while(this.chopsticks.status) {
            System.out.println(Thread.currentThread()+"chopsticks is used, can't get");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.chopsticks.status = true;
        System.out.println(Thread.currentThread()+"get chopsticks");
    }

    void getBowl() {
        while(this.bowl.status) {
            System.out.println(Thread.currentThread()+"bowl is used, can't get");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.bowl.status = true;
        System.out.println(Thread.currentThread()+"get bowl");
    }

    @Override
    public void run() {
        if(this.flag) {
            getChopsticks();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getBowl();
        }else {
            getBowl();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getChopsticks();
        }
        System.out.println(Thread.currentThread()+"end");
    }

}
