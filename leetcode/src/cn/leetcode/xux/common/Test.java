package cn.leetcode.xux.common;

import java.util.*;

public class Test {

    int result_int;
    Map<String, List<String>> mem = new HashMap<>();

    public static void main(String[] args) {
        Test test = new Test();
//        System.out.println(test.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
//        System.out.println(test.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
//        System.out.println(test.spiralOrder(new int[][]{{6,9,7}}));
        System.out.println(test.exist(new char[][]{{'a'}}, "ab"));
    }

    /**
     * 面试题11. 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
     *
     * 示例 1：
     * 输入：[3,4,5,1,2]
     * 输出：1
     *
     * 示例 2：
     * 输入：[2,2,2,0,1]
     * 输出：0
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        int mid;
        while (left<right) {
            mid = (left+right)/2;
            if (numbers[mid]>numbers[right]) {
                left = mid+1;
            }else if(numbers[mid]<numbers[right]) {
                right = mid;
            }else {
                right--;
            }
        }
        return numbers[left];
    }

    /**
     * 面试题12. 矩阵中的路径
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
     * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     * [["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]]
     * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
     *
     * 示例 1：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     *
     * 示例 2：
     * 输入：board = [["a","b"],["c","d"]], word = "abcd"
     * 输出：false
     *
     * 提示：
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(exist(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int i, int j, int curr) {
        if(curr==word.length()) {
            return true;
        }
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!=word.charAt(curr)) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j]  = '#';
        boolean res = exist(board, word, i-1, j, curr+1)
                ||exist(board, word, i+1, j, curr+1)
                ||exist(board, word, i, j-1, curr+1)
                ||exist(board, word, i, j+1, curr+1);
        board[i][j] = tmp;
        return res;
    }

    /**
     * 面试题13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     *
     * 示例 2：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     *
     * 提示：
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     */
    public int movingCount(int m, int n, int k) {
        if(m<=0||n<=0||k<0) {
            return 0;
        }
        result_int = 0;
        movingCount(0, 0, k, new boolean[m][n]);
        return result_int;
    }

    public void movingCount(int i, int j, int k, boolean[][] visited) {
        if(i<0||i>=visited.length||j<0||j>=visited[0].length||visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        int target = 0;
        int tmpI = i;
        while(tmpI>0) {
            target += tmpI%10;
            tmpI /= 10;
        }
        int tmpJ = j;
        while(tmpJ>0) {
            target += tmpJ%10;
            tmpJ /= 10;
        }
        if(target>k) {
            return ;
        }
        result_int++;
        movingCount(i-1, j, k, visited);
        movingCount(i+1, j, k, visited);
        movingCount(i, j-1, k, visited);
        movingCount(i, j+1, k, visited);
    }

    /**
     * 面试题14- I. 剪绳子
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 示例 1：
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     *
     * 示例 2:
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     * 提示：
     * 2 <= n <= 58
     */
    public int cuttingRopeI(int n) {
        if(n==2||n==3) {
            return n-1;
        }
        int m = n/3-1;
        int result = (int)Math.pow(3, m);
        if(n%3==0) {
            return result*3;
        }else if(n%3==1) {
            return result*4;
        }else {
            return result*6;
        }
    }

    /**
     * 面试题14- II. 剪绳子 II
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     *
     * 示例 2:
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     * 提示：
     * 2 <= n <= 1000
     */
    public int cuttingRopeII(int n) {
        if(n==2||n==3) {
            return n-1;
        }
        int mod = 1000000007;
        int m = n/3-1;
        long result = 1;
        while(m-->0) {
            result = result*3%mod;
        }
        if(n%3==0) {
            return (int)result*3%mod;
        }else if(n%3==1) {
            return (int)result*4%mod;
        }else {
            return (int)result*6%mod;
        }
    }

    /**
     * 面试题15. 二进制中1的个数
     * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     *
     * 示例 1：
     * 输入：00000000000000000000000000001011
     * 输出：3
     * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
     *
     * 示例 2：
     * 输入：00000000000000000000000010000000
     * 输出：1
     * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
     *
     * 示例 3：
     * 输入：11111111111111111111111111111101
     * 输出：31
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        while(n!=0) {
            n &= n-1;
            result++;
        }
        return result;
    }

    /**
     * 面试题16. 数值的整数次方
     * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
     *
     * 示例 1:
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     *
     * 示例 2:
     * 输入: 2.10000, 3
     * 输出: 9.26100
     *
     * 示例 3:
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     *
     * 说明:
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     */
    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n<0) {
            if(n==Integer.MIN_VALUE) {
                return myPow(1/x, -(n+1))/x;
            }else {
                return myPow(1/x, -n);
            }
        }
        double pre = myPow(x, n/2);
        return n%2==1?pre*pre*x:pre*pre;
    }

    /**
     * 面试题17. 打印从1到最大的n位数
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     *
     * 示例 1:
     * 输入: n = 1
     * 输出: [1,2,3,4,5,6,7,8,9]
     *
     * 说明：
     * 用返回一个整数列表来代替打印
     * n 为正整数
     */
    public int[] printNumbers(int n) {
        if(n<1) {
            return new int[0];
        }
        int target = (int)Math.pow(10,n);
        int[] result = new int[target-1];
        for(int i=0;i<result.length;i++) {
            result[i] = i+1;
        }
        return result;
    }

    /**
     * 面试题18. 删除链表的节点
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     *
     * 注意：此题对比原题有改动
     * 示例 1:
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     *
     * 示例 2:
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     * 说明：
     * 题目保证链表中节点的值互不相同
     * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if(head==null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode curr = newHead;
        while(curr!=null&&curr.next!=null) {
            if(curr.next.val==val) {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }
        return newHead.next;
    }

    /**
     * 面试题20. 表示数值的字符串
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"及"-1E-16"都表示数值，
     * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     */
    public boolean isNumber(String s) {
        if(s==null||s.trim().length()==0) {
            return false;
        }
        s = s.trim();
        String[] ss = s.split("e", -1);
        if(ss.length==0||ss.length>2) {
            return false;
        }
        boolean pre = isNum(ss[0]);
        if(ss.length==1) {
            return pre;
        }else {
            boolean post = ss[1].startsWith("+")||ss[1].startsWith("-")?isInteger(ss[1].substring(1)):isInteger(ss[1]);
            return pre&&post;
        }
    }
    public boolean isNum(String s) {
        if(s.length()==0) {
            return false;
        }
        if(s.startsWith("+")||s.startsWith("-")) {
            s = s.substring(1);
        }
        String[] ss = s.split("\\.", -1);
        if(ss.length==0||ss.length>2) {
            return false;
        }
        boolean pre = isInteger(ss[0]);
        if(ss.length==1) {
            return isInteger(ss[0]);
        }else {
            boolean post = isInteger(ss[1]);
            return (ss[0].length()==0&&post)||(ss[1].length()==0&&pre)||(pre&&post);
        }
    }
    public boolean isInteger(String s) {
        if(s.length()==0) {
            return false;
        }
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c>'9'||c<'0') {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试题21. 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * 示例：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     *
     * 提示：
     * 1 <= nums.length <= 50000
     * 1 <= nums[i] <= 10000
     */
    public int[] exchange(int[] nums) {
        if(nums==null||nums.length<2) {
            return nums;
        }
        int len = nums.length;
        int left = 0;
        int right = len-1;
        while(left<right) {
            while(left<right&&nums[left]%2==1) {
                left++;
            }
            while(left<right&&nums[right]%2==0) {
                right--;
            }
            if(left<right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }

    /**
     * 面试题22. 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
     *
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5.
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null||k<=0) {
            return null;
        }
        ListNode right = head;
        while(k-->0&&right!=null) {
            right = right.next;
        }
        if(k>0) {
            return null;
        }
        ListNode left = head;
        while(right!=null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }

    /**
     * 面试题24. 反转链表
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     *
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * 限制：
     * 0 <= 节点个数 <= 5000
     */
    public ListNode reverseList(ListNode head) {
        if(head==null) {
            return null;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
        /*ListNode pre = null;
        ListNode curr = head;
        while(curr!=null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;*/
    }

    /**
     * 面试题25. 合并两个排序的链表
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
     *
     * 示例1：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 限制：
     * 0 <= 链表长度 <= 1000
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while(l1!=null&&l2!=null) {
            if(l1.val<=l2.val) {
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }
        }
        curr.next = l1==null?l2 : l1;
        return head.next;
    }

    /**
     * 面试题26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * 例如:给定的树 A:
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 B：
     *    4
     *   /
     *  1
     * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
     *
     * 示例 1：
     * 输入：A = [1,2,3], B = [3,1]
     * 输出：false
     *
     * 示例 2：
     * 输入：A = [3,4,5,1,2], B = [4,1]
     * 输出：true
     *
     * 限制：
     * 0 <= 节点个数 <= 10000
     */
    public boolean isSubStructure(BinaryTreeNode A, BinaryTreeNode B) {
        if(A==null||B==null) {
            return false;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(A);
        while(!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            if(isSame(curr, B)) {
                return true;
            }else {
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
        }
        return false;
    }

    public boolean isSame(BinaryTreeNode A, BinaryTreeNode B) {
        if(B==null) {
            return true;
        }
        if(A==null||A.val!=B.val) {
            return false;
        }
        return isSame(A.left, B.left)&&isSame(A.right, B.right);
    }

    /**
     * 面试题27. 二叉树的镜像
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * 例如输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 镜像输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     *
     * 限制：
     * 0 <= 节点个数 <= 1000
     */
    public BinaryTreeNode mirrorTree(BinaryTreeNode root) {
        if(root==null) {
            return null;
        }
        BinaryTreeNode result = new BinaryTreeNode(root.val);
        result.left = mirrorTree(root.right);
        result.right = mirrorTree(root.left);
        return result;
    }

    /**
     * 面试题28. 对称的二叉树
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     *
     * 示例 1：
     * 输入：root = [1,2,2,3,4,4,3]
     * 输出：true
     *
     * 示例 2：
     * 输入：root = [1,2,2,null,3,null,3]
     * 输出：false
     *
     * 限制：
     * 0 <= 节点个数 <= 1000
     */
    //递归
    public boolean isSymmetric(BinaryTreeNode root) {
        if(root==null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(BinaryTreeNode left, BinaryTreeNode right) {
        if(left==null&&right==null) {
            return true;
        }
        if(left==null||right==null||left.val!=right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right)&&isSymmetric(left.right, right.left);
    }

    /**
     * 面试题29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     *
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * 限制：
     * 0 <= matrix.length <= 100
     * 0 <= matrix[i].length <= 100
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return new int[0];
        }
        int[] result = new int[matrix.length*matrix[0].length];
        int up = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        int index = 0;
        while(up<=bottom&&left<=right) {
            for(int i=left;i<=right;i++) {
                result[index++] = matrix[up][i];
            }
            for(int i=up+1;i<=bottom;i++) {
                result[index++] = matrix[i][right];
            }
            if(index<result.length) {
                for(int i=right-1;i>=left;i--) {
                    result[index++] = matrix[bottom][i];
                }
                for(int i=bottom-1;i>=up+1;i--) {
                    result[index++] = matrix[i][left];
                }
            }
            up++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }

    /**
     * 面试题31. 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
     * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     *
     * 示例 2：
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     *
     * 提示：
     * 0 <= pushed.length == popped.length <= 1000
     * 0 <= pushed[i], popped[i] < 1000
     * pushed 是 popped 的排列。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed==null) {
            return false;
        }
        int len = pushed.length;
        if(len<3) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int poppedIndex = 0;
        for(int i=0;i<len;i++) {
            stack.push(pushed[i]);
            while(!stack.isEmpty()&&stack.peek()==popped[poppedIndex]) {
                stack.pop();
                poppedIndex++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 面试题32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     *
     * 例如:给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回：[3,9,20,15,7]
     *
     * 提示：节点总数 <= 1000
     */
    public int[] levelOrderI(BinaryTreeNode root) {
        if(root==null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            list.add(curr.val);
            if(curr.left!=null) {
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
            }
        }
        int[] result = new int[list.size()];
        for(int i=0;i<result.length;i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 面试题32 - II. 从上到下打印二叉树 II
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * 例如:给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 提示：
     * 节点总数 <= 1000
     */
    public List<List<Integer>> levelOrderII(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size-->0) {
                BinaryTreeNode curr = queue.poll();
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

    /**
     * 面试题32 - III. 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     *
     * 提示：
     * 节点总数 <= 1000
     */
    public List<List<Integer>> levelOrderIII(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean asc = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            while(size-->0) {
                BinaryTreeNode curr = queue.poll();
                if(asc) {
                    list.add(curr.val);
                }else {
                    list.add(0, curr.val);
                }
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
            result.add(list);
            asc = !asc;
        }
        return result;
    }

    /**
     * 面试题33. 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
     * 假设输入的数组的任意两个数字都互不相同。
     *
     * 参考以下这颗二叉搜索树：
     *      5
     *     / \
     *    2   6
     *   / \
     *  1   3
     *
     * 示例 1：
     * 输入: [1,6,3,2,5]
     * 输出: false
     *
     * 示例 2：
     * 输入: [1,3,2,6,5]
     * 输出: true
     *
     * 提示：
     * 数组长度 <= 1000
     */
    //O(n2)
    public boolean verifyPostorder(int[] postorder) {
        if(postorder==null) {
            return false;
        }
        return verifyPostorder(postorder, 0, postorder.length-1);
    }

    public boolean verifyPostorder(int[] postorder, int start, int end) {
        if(end-start<=1) {
            return true;
        }
        boolean isLeft = true;
        int leftStart = start;
        int leftEnd = end-1;
        int rightEnd = end-1;
        for(int i=start;i<end;i++) {
            if(isLeft&&postorder[i]>postorder[end]) {
                isLeft = false;
                leftEnd = i-1;
                continue;
            }
            if(!isLeft&&postorder[i]<postorder[end]) {
                return false;
            }
        }
        return verifyPostorder(postorder, leftStart, leftEnd)&&verifyPostorder(postorder, leftEnd+1, rightEnd);
    }

    //O(n)
    public boolean verifyPostorder1(int[] postorder) {
        if(postorder==null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i=postorder.length-1;i>=0;i--) {
            if(postorder[i]>root) {
                return false;
            }
            while(!stack.isEmpty()&&stack.peek()>postorder[i]) {
                root = stack.pop();
            }
            stack.push(postorder[i]);
        }
        return true;
    }

    /**
     * 面试题34. 二叉树中和为某一值的路径
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     *
     * 提示：
     * 节点总数 <= 10000
     */
    List<List<Integer>> result_list = new ArrayList<>();
    public List<List<Integer>> pathSum(BinaryTreeNode root, int sum) {
        result_list.clear();
        if(root==null) {
            return result_list;
        }
        pathSum(new ArrayList<Integer>(), root, 0, sum);
        return result_list;
    }

    public void pathSum(List<Integer> curr, BinaryTreeNode node, int pre, int sum) {
        pre += node.val;
        List<Integer> newCurr = new ArrayList<>(curr);
        newCurr.add(node.val);
        if(node.left==null&&node.right==null) {
            if(pre==sum) {
                result_list.add(newCurr);
            }
            return;
        }
        if(node.left!=null) {
            pathSum(newCurr, node.left, pre, sum);
        }
        if(node.right!=null) {
            pathSum(newCurr, node.right, pre, sum);
        }
    }

    /**
     * 面试题35. 复杂链表的复制
     * 请实现 copyRandomList 函数，复制一个复杂链表。
     * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     *
     * 示例 1：
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * 示例 2：
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     *
     * 示例 3：
     * 输入：head = [[3,null],[3,0],[3,null]]
     * 输出：[[3,null],[3,0],[3,null]]
     *
     * 示例 4：
     * 输入：head = []
     * 输出：[]
     * 解释：给定的链表为空（空指针），因此返回 null。
     *
     * 提示：
     * -10000 <= Node.val <= 10000
     * Node.random 为空（null）或指向链表中的节点。
     * 节点数目不超过 1000 。
     */
    public ListNodeWithPointer copyRandomList(ListNodeWithPointer head) {
        if(head==null) {
            return null;
        }
        ListNodeWithPointer newHead = new ListNodeWithPointer(head.val);
        Map<ListNodeWithPointer, ListNodeWithPointer> map = new HashMap<>();
        map.put(head, newHead);
        Queue<ListNodeWithPointer> queue = new LinkedList<>();
        queue.offer(head);
        while(!queue.isEmpty()) {
            ListNodeWithPointer curr = queue.poll();
            if(curr.next!=null) {
                if(map.containsKey(curr.next)) {
                    map.get(curr).next = map.get(curr.next);
                }else {
                    ListNodeWithPointer node = new ListNodeWithPointer(curr.next.val);
                    map.get(curr).next = node;
                    map.put(curr.next, node);
                    queue.offer(curr.next);
                }
            }
            if(curr.point!=null) {
                if(map.containsKey(curr.point)) {
                    map.get(curr).point = map.get(curr.point);
                }else {
                    ListNodeWithPointer node = new ListNodeWithPointer(curr.point.val);
                    map.get(curr).point = node;
                    map.put(curr.point, node);
                    queue.offer(curr.point);
                }
            }
        }
        return newHead;
    }

    /**
     * 面试题36. 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     * 为了让您更好地理解问题，以下面的二叉搜索树为例：
     * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
     * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
     */
    public BinaryTreeNode treeToDoublyList(BinaryTreeNode root) {
        if(root==null) {
            return null;
        }
        BinaryTreeNode curr = root;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        BinaryTreeNode head = stack.peek();
        BinaryTreeNode pre = null;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            curr.left = pre;
            if(pre!=null) {
                pre.right = curr;
            }
            pre = curr;
            curr = curr.right;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        head.left = pre;
        pre.right = head;
        return head;
    }

    /**
     * 面试题38. 字符串的排列
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     *
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     *
     * 限制：
     * 1 <= s 的长度 <= 8
     */
    public String[] permutation(String s) {
        List<String> list = permutationHelper(s);
        return list.toArray(new String[list.size()]);
    }

    public List<String> permutationHelper(String s) {
        List<String> result = new ArrayList<>();
        if(s==null||s.length()==0) {
            return result;
        }
        if(s.length()==1) {
            result.add(s);
            return result;
        }
        if(mem.containsKey(s)) {
            return mem.get(s);
        }
        Set<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++) {
            char curr = s.charAt(i);
            if(!set.add(curr)) {
                continue;
            }
            List<String> remain = permutationHelper(s.substring(0, i)+s.substring(i+1));
            for(String str : remain) {
                result.add(curr+str);
            }
        }
        mem.put(s, result);
        return result;
    }

    /**
     * 面试题39. 数组中出现次数超过一半的数字
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1:
     * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
     * 输出: 2
     *
     * 限制：
     * 1 <= 数组长度 <= 50000
     */
    public int majorityElement(int[] nums) {
        int curr = nums[0];
        int cnt = 1;
        for(int i=1;i<nums.length;i++) {
            if(cnt==0) {
                curr = nums[i];
            }
            cnt += curr==nums[i]?1:-1;
        }
        return curr;
    }

    /**
     * 面试题40. 最小的k个数
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     * 示例 1：
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     *
     * 示例 2：
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     *
     * 限制：
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr==null||arr.length==0) {
            return new int[]{};
        }
        if(k>=arr.length) {
            return arr;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2)->o2-o1);
        for(int num : arr) {
            queue.offer(num);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        int[] result = new int[queue.size()];
        while(!queue.isEmpty()) {
            result[queue.size()-1] = queue.poll();
        }
        return result;
    }

    /**
     * 面试题42. 连续子数组的最大和
     * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     *
     * 示例1:
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 提示：
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     */
    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int curr = nums[0];
        int result = nums[0];
        for(int i=1;i<nums.length;i++) {
            curr = Math.max(curr+nums[i], nums[i]);
            result = Math.max(result, curr);
        }
        return result;
    }

    /**
     * 面试题44. 数字序列中某一位的数字
     * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
     * 请写一个函数，求任意第n位对应的数字。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：3
     *
     * 示例 2：
     * 输入：n = 11
     * 输出：0
     *
     * 限制：
     * 0 <= n < 2^31
     */
    public int findNthDigit(int n) {
        if(n<10) {
            return n;
        }
        int num = 9;
        int len = 1;
        while(n-num*len>0&&len<9) {
            n -= num*len;
            num *= 10;
            len++;
        }
        int tar = num/9 + (n-1)/len;
        int index = (n-1)%len;
        return (tar+"").charAt(index)-'0';
    }

    /**
     * 面试题45. 把数组排成最小的数
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * 示例 1:
     * 输入: [10,2]
     * 输出: "102"
     *
     * 示例 2:
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     *
     * 提示:
     * 0 < nums.length <= 100
     *
     * 说明:
     * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
     * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
     */
    public String minNumber(int[] nums) {
        if(nums==null||nums.length==0) {
            return "";
        }
        Integer[] newNums = new Integer[nums.length];
        for(int i=0;i<nums.length;i++) {
            newNums[i] = nums[i];
        }
        Arrays.sort(newNums, (o1, o2)->(""+o1+o2).compareTo(""+o2+o1));
        /*Arrays.sort(newNums, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.valueOf(""+o1+o2)-Integer.valueOf(""+o2+o1);
            }
        });*/
        StringBuilder result = new StringBuilder();
        for(int num : newNums) {
            result.append(num);
        }
        return result.toString();
    }

    /**
     * 面试题46. 把数字翻译成字符串
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
     * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     *
     * 示例 1:
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     *
     * 提示：
     * 0 <= num < 231
     */
    public int translateNum(int num) {
        if(num<10) {
            return 1;
        }
        int result = translateNum(num/10);
        if(num%100>9&&num%100<26) {
            result += translateNum(num/100);
        }
        return result;
    }

    /**
     * 面试题47. 礼物的最大价值
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     *
     * 示例 1:
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 12
     * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
     *
     * 提示：
     * 0 < grid.length <= 200
     * 0 < grid[0].length <= 200
     */
    public int maxValue(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int pre = 0;
                if(i>0) {
                    pre = Math.max(pre, grid[i-1][j]);
                }
                if(j>0) {
                    pre = Math.max(pre, grid[i][j-1]);
                }
                grid[i][j] += pre;
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * 面试题48. 最长不含重复字符的子字符串
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * 示例 1:
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 示例 2:
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 示例 3:
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 提示：
     * s.length <= 40000
     */
    public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int result = 1;
        Map<Character, Integer> map = new HashMap();
        int start = 0;
        for(int i=0;i<s.length();i++) {
            if(map.containsKey(s.charAt(i))&&map.get(s.charAt(i))>=start) {
                start = map.get(s.charAt(i))+1;
            }
            map.put(s.charAt(i), i);
            result = Math.max(result, i-start+1);
        }
        return result;
    }

    /**
     * 面试题49. 丑数
     * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * 示例:
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     *
     * 说明:
     * 1 是丑数。
     * n 不超过1690。
     */
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        for(int i=1;i<n;i++) {
            res[i] = Math.min(res[index2]*2, Math.min(res[index3]*3, res[index5]*5));
            if(res[i]==res[index2]*2) {
                index2++;
            }
            if(res[i]==res[index3]*3) {
                index3++;
            }
            if(res[i]==res[index5]*5) {
                index5++;
            }
        }
        return res[n-1];
    }

    /**
     * 面试题50. 第一个只出现一次的字符
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
     *
     * 示例:
     * s = "abaccdeff"
     * 返回 "b"
     * s = ""
     * 返回 " "
     *
     * 限制：
     * 0 <= s 的长度 <= 50000
     */
    public char firstUniqChar(String s) {
        if(s==null||s.length()==0) {
            return ' ';
        }
        Map<Character, Integer> map = new HashMap();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for(char c : s.toCharArray()) {
            if(map.get(c)==1) {
                return c;
            }
        }
        return ' ';
    }

    /**
     * 面试题51. 数组中的逆序对
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
     * 输入一个数组，求出这个数组中的逆序对的总数。
     *
     * 示例 1:
     * 输入: [7,5,6,4]
     * 输出: 5
     *
     * 限制：
     * 0 <= 数组长度 <= 50000
     */
    public int reversePairs(int[] nums) {
        if(nums==null||nums.length<2) {
            return 0;
        }
        result_int = 0;
        mergeSort(nums, 0, nums.length-1);
        return result_int;
    }

    public void mergeSort(int[] nums, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int mid = (left+right)/2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid+1, end);
        int index1 = left;
        int index2 = mid+1;
        int index = 0;
        int[] tmp = new int[end-start+1];
        while(index<end-start+1) {
            if(index2==end+1) {
                result_int += end-index2+1;
                tmp[index++] = nums[index1++];
            }else if(index1==mid+1) {
                tmp[index++] = nums[index2++];
            }else if(nums[index1]>nums[index2]) {
                result_int += end-index2+1;
                tmp[index++] = nums[index1++];
            }else if(nums[index2]>nums[index1]) {
                tmp[index++] = nums[index2++];
            }else {
                tmp[index++] = nums[index2++];
            }
        }
        for(int i=start;i<=end;i++) {
            nums[i] = tmp[i-start];
        }
    }

    /**
     * 面试题52. 两个链表的第一个公共节点
     * 输入两个链表，找出它们的第一个公共节点。
     * 如下面的两个链表：
     * 在节点 c1 开始相交。
     *
     * 示例 1：
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     * 示例 2：
     * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
     * 输出：Reference of the node with value = 2
     * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
     *
     * 示例 3：
     * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
     * 输出：null
     * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
     * 解释：这两个链表不相交，因此返回 null。
     *
     * 注意：
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
        ListNode currA = headA;
        ListNode currB = headB;
        while(currA!=currB) {
            currA = currA!=null?currA.next:headB;
            currB = currB!=null?currB.next:headA;
        }
        return currA;
    }

    /**
     * 面试题53 - II. 0～n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     *
     * 示例 1:
     * 输入: [0,1,3]
     * 输出: 2
     *
     * 示例 2:
     * 输入: [0,1,2,3,4,5,6,7,9]
     * 输出: 8
     *
     * 限制：
     * 1 <= 数组长度 <= 10000
     */
    public int missingNumber(int[] nums) {
        if(nums==null||nums.length==0) {
            return -1;
        }
        int len = nums.length;
        int left = 0;
        int right = len-1;
        int mid;
        while(left<right) {
            mid = (left+right)/2;
            if(mid==nums[mid]) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        return nums[right]==right?len:right;
    }

    /**
     * 面试题53 - I. 在排序数组中查找数字 I
     * 统计一个数字在排序数组中出现的次数。
     *
     * 示例 1:
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     *
     * 示例 2:
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     *
     * 限制：
     * 0 <= 数组长度 <= 50000
     */
    public int search(int[] nums, int target) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int len = nums.length;
        int left = 0;
        int right = len-1;
        int mid;
        while(left<right) {
            mid = (left+right+1)/2;
            if(nums[mid]>=target) {
                right = mid-1;
            }else {
                left = mid;
            }
        }
        int low = nums[left]<target?left:-1;
        left = 0;
        right = len-1;
        while(left<right) {
            mid = (left+right)/2;
            if(nums[mid]<=target) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        int high = nums[right]>target?right:len;
        return high-low-1;
    }

    /**
     * 面试题59 - I. 滑动窗口的最大值
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * 提示：
     * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0||k>nums.length) {
            return new int[]{};
        }
        Deque<Integer> deque = new LinkedList();
        int len = nums.length;
        int[] result = new int[len-k+1];
        for(int i=0;i<len;i++)  {
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]) {
                deque.pollLast();
            }
            if(!deque.isEmpty()&&i-deque.peekFirst()>=k) {
                deque.pollFirst();
            }
            deque.offerLast(i);
            if(i>=k-1) {
                result[i-k+1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    /**
     * 面试题54. 二叉搜索树的第k大节点
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     *
     * 示例 1:
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 4
     *
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * 输出: 4
     *
     * 限制：
     * 1 ≤ k ≤ 二叉搜索树元素个数
     */
    public int kthLargest(BinaryTreeNode root, int k) {
        Stack<BinaryTreeNode> stack = new Stack();
        BinaryTreeNode curr = root;
        while(curr!=null) {
            stack.push(curr);
            curr = curr.right;
        }
        while(k>1) {
            curr = stack.pop();
            k--;
            curr = curr.left;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.right;
            }
        }
        return stack.pop().val;
    }

    /**
     * 面试题56 - II. 数组中数字出现的次数 II
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     *
     * 示例 1：
     * 输入：nums = [3,4,3,3]
     * 输出：4
     *
     * 示例 2：
     * 输入：nums = [9,1,7,9,7,9,7]
     * 输出：1
     *
     * 限制：
     * 1 <= nums.length <= 10000
     * 1 <= nums[i] < 2^31return
     */
    public int singleNumber(int[] nums) {
        int one = 0;
        int two = 0;
        int three = 0;
        for(int num : nums) {
            two |= one&num;
            one ^= num;
            three = two&one;
            one &= ~three;
            two &= ~three;
        }
        return one;
    }

    /**
     * 面试题56 - I. 数组中数字出现的次数
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     * 示例 1：
     * 输入：nums = [4,1,4,6]
     * 输出：[1,6] 或 [6,1]
     *
     * 示例 2：
     * 输入：nums = [1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     *
     * 限制：
     * 2 <= nums <= 10000
     */
    public int[] singleNumbers(int[] nums) {
        int a = 0;
        int b = 0;
        int ret = 0;
        for(int num : nums) {
            ret ^= num;
        }
        int h = 1;
        while((ret&h)==0) {
            h <<= 1;
        }
        for(int num : nums) {
            if((num&h)==0) {
                a ^= num;
            }else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    /**
     * 面试题60. n个骰子的点数
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * 示例 1:
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     *
     * 示例 2:
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     *
     * 限制：
     * 1 <= n <= 11
     */
    public double[] twoSum(int n) {
        if(n<1) {
            return new double[]{};
        }
        int[][] dp = new int[n+1][6*n+1];
        for(int i=1;i<=6;i++) {
            dp[1][i] = 1;
        }
        for(int i=2;i<=n;i++) {
            for(int j=i;j<=6*i;j++) {
                for(int k=1;k<=6;k++) {
                    if(j-k<=0) {
                        break;
                    }else {
                        dp[i][j] += dp[i-1][j-k];
                    }
                }
            }
        }
        int sum = (int)Math.pow(6, n);
        double[] result = new double[n*5+1];
        for(int i=0;i<result.length;i++) {
            result[i] = (double)dp[n][i+n]/sum;
        }
        return result;
    }

    /**
     * 面试题65. 不用加减乘除做加法
     * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
     *
     * 示例:
     * 输入: a = 1, b = 1
     * 输出: 2
     *
     * 提示：
     * a, b 均可能是负数或 0
     * 结果不会溢出 32 位整数
     */
    public int add(int a, int b) {
        int result = a;
        int tmp;
        while(b!=0) {
            tmp = result;
            result = result^b;
            b = (b&tmp)<<1;
        }
        return result;
    }

}

/**
 * 面试题59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 *
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */
class MaxQueue {

    Queue<Integer> queue;
    Deque<Integer> maxQueue;

    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }

    public int max_value() {
        return maxQueue.isEmpty()?-1:maxQueue.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while(!maxQueue.isEmpty()&&maxQueue.peekLast()<value) {
            maxQueue.pollLast();
        }
        maxQueue.offer(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) {
            return -1;
        }
        int value = queue.poll();
        if(value==maxQueue.peekFirst()) {
            maxQueue.pollFirst();
        }
        return value;
    }
}

class MinStack {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
        if(stack2.isEmpty()||x<=stack2.peek()) {
            stack2.push(x);
        }
    }

    public void pop() {
        if(stack1.isEmpty()) {
            return;
        }
        int x = stack1.pop();
        if(!stack2.isEmpty()&&stack2.peek()==x) {
            stack2.pop();
        }
    }

    public int top() {
        return stack1.isEmpty()?-1:stack1.peek();
    }

    public int getMin() {
        return stack2.isEmpty()?-1:stack2.peek();
    }

}