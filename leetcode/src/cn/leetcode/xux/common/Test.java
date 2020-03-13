package cn.leetcode.xux.common;

import java.util.*;

public class Test {

    int result_int;

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.permutation("abc"));
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
    public List<String> permutation(String s) {
        //TODO
        return null;
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
/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */