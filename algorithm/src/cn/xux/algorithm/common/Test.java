package cn.xux.algorithm.common;

import java.util.*;

public class Test {

    int result_int;
    Map<String, List<String>> mem = new HashMap<>();
    int mod = 1000000007;

    public static void main(String[] args) {
        Test test = new Test();
//        System.out.println(test.spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
//        System.out.println(test.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
//        System.out.println(test.spiralOrder(new int[][]{{6,9,7}}));
//        System.out.println(test.exist(new char[][]{{'a'}}, "ab"));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(test.missingTwo(new int[]{1,2,3,4,6,7,9,10}));
    }

    /**
     * 面试题67. 把字符串转换成整数
     * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     * 说明：
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *
     * 示例 1:
     * 输入: "42"
     * 输出: 42
     *
     *  示例 2:
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     *
     *  示例 3:
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     *
     *  示例 4:
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     *      因此无法执行有效的转换。
     *
     *  示例 5:
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     */
    public int strToInt(String str) {
        if(str==null||str.length()==0) {
            return 0;
        }
        str = str.trim();
        if(str.length()==0) {
            return 0;
        }
        boolean started = false;
        boolean sign = true;
        long result = 0;
        for(char c : str.toCharArray()) {
            if(c<'0'||c>'9') {
                if(started) {
                    break;
                }else {
                    if(c=='+') {
                        started = true;
                    }else if(c=='-') {
                        started = true;
                        sign = false;
                    }else {
                        break;
                    }
                }
            }else {
                result = result*10+c-'0';
                if(sign&&result>Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }else if(!sign&&-result<Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
                started = true;
            }
        }
        result = sign?result:-result;
        if(result>Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }else if(result<Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }else {
            return (int)result;
        }
    }

    /**
     * 面试题57. 和为s的两个数字
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。
     * 如果有多对数字的和等于s，则输出任意一对即可。
     *
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[2,7] 或者 [7,2]
     *
     *  示例 2：
     * 输入：nums = [10,26,30,31,47,60], target = 40
     * 输出：[10,30] 或者 [30,10]
     *
     * 限制：
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums==null||nums.length<2) {
            return new int[]{};
        }
        int left = 0;
        int right = nums.length-1;
        while(left<right) {
            int curr = nums[left]+nums[right];
            if(curr==target) {
                return new int[]{nums[left], nums[right]};
            }else if(curr<target) {
                left++;
            }else {
                right--;
            }
        }
        return new int[]{};
    }

    /**
     * 面试题58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     *
     * 示例 1：
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     *
     *  示例 2：
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     *
     * 限制：
     * 1 <= k < s.length <= 10000
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n)+s.substring(0, n);
    }

    /**
     * 面试题58 - I. 翻转单词顺序
     * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
     * 例如输入字符串"I am a student. "，则输出"student. a am I"。
     *
     * 示例 1：
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     *
     *  示例 2：
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     *
     *  示例 3：
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *
     * 说明：
     * 无空格字符构成一个单词。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    public String reverseWords(String s) {
        if(s==null||s.length()==0) {
            return s;
        }
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=strs.length-1;i>=0;i--) {
            if(!"".equals(strs[i])) {
                sb.append(strs[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * 面试题 17.15. 最长单词
     * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
     * 若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
     *
     * 示例：
     * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
     * 输出： "dogwalker"
     * 解释： "dogwalker"可由"dog"和"walker"组成。
     *
     * 提示：
     * 0 <= len(words) <= 100
     * 1 <= len(words[i]) <= 100
     */
    public String longestWord(String[] words) {
        if(words==null||words.length<2) {
            return "";
        }
        Set<String> set = new HashSet<>();
        for(String word : words) {
            if(word!=null&&word.length()>0) {
                set.add(word);
            }
        }
        String result = "";
        for(String word : words) {
            set.remove(word);
            if((word.length()>result.length()||(word.length()==result.length()&&word.compareTo(result)<0))
                    &&isCombined(word, set)) {
                result = word;
            }
            set.add(word);
        }
        return result;
    }

    public boolean isCombined(String target, Set<String> set) {
        if(target.equals("")) {
            return true;
        }
        for(String s : set) {
            if(target.startsWith(s)&&isCombined(target.substring(s.length()), set)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排
     * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     *
     * 示例 1：
     * 输入: s1 = "abc", s2 = "bca"
     * 输出: true
     *
     * 示例 2：
     * 输入: s1 = "abc", s2 = "bad"
     * 输出: false
     *
     * 说明：
     * 0 <= len(s1) <= 100
     * 0 <= len(s2) <= 100
     */
    public boolean CheckPermutation(String s1, String s2) {
        if(s1==null||s2==null||s1.length()!=s2.length()) {
            return false;
        }
        int[] cnts = new int[26];
        for(char c : s1.toCharArray()) {
            cnts[c-'a']++;
        }
        for(char c : s2.toCharArray()) {
            cnts[c-'a']--;
        }
        for(int cnt : cnts) {
            if(cnt!=0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试题 01.03. URL化
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。
     * 假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
     * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * 示例1:
     *  输入："Mr John Smith    ", 13
     *  输出："Mr%20John%20Smith"
     *
     * 示例2:
     *  输入："               ", 5
     *  输出："%20%20%20%20%20"
     *
     * 提示：
     * 字符串长度在[0, 500000]范围内
     */
    public String replaceSpaces(String S, int length) {
        char[] chars = S.toCharArray();
        int spaceCnt = 0;
        for(int i=0;i<length;i++) {
            if(S.charAt(i)==' ') {
                spaceCnt++;
            }
        }
        int right = length+spaceCnt*2-1;
        int left = length-1;
        while(right>left) {
            if(chars[left]==' ') {
                left--;
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right--] = '%';
            }else {
                chars[right--] = chars[left--];
            }
        }
        return new String(chars).trim();
    }

    /**
     * 面试题 01.04. 回文排列
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     * 回文串不一定是字典当中的单词。
     *
     * 示例1：
     * 输入："tactcoa"
     * 输出：true（排列有"tacocat"、"atcocta"，等等）
     */
    public boolean canPermutePalindrome(String s) {
        if(s==null) {
            return false;
        }
        Map<Character, Integer> cnts = new HashMap<>();
        for(char c : s.toCharArray()) {
            cnts.put(c, cnts.getOrDefault(c, 0)+1);
        }
        boolean hasOdd = false;
        for(Map.Entry<Character, Integer> entry : cnts.entrySet()) {
            if(entry.getValue()%2==1) {
                if(hasOdd) {
                    return false;
                }else {
                    hasOdd = true;
                }
            }
        }
        return true;
    }

    /**
     * 面试题 01.05. 一次编辑
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
     * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     *
     * 示例 1:
     * 输入:
     * first = "pale"
     * second = "ple"
     * 输出: True
     *
     * 示例 2:
     * 输入:
     * first = "pales"
     * second = "pal"
     * 输出: False
     */
    public boolean oneEditAway(String first, String second) {
        if(first==null||second==null) {
            return false;
        }
        int len1 = first.length();
        int len2 = second.length();
        if(Math.abs(len1-len2)>1) {
            return false;
        }
        int idx1 = 0;
        int idx2 = 0;
        boolean hasEdit = false;
        while(idx1<len1&&idx2<len2) {
            if(first.charAt(idx1)==second.charAt(idx2)) {
                idx1++;
                idx2++;
            }else {
                if(!hasEdit) {
                    if(len1>len2) {
                        idx1++;
                    }else if(len1<len2) {
                        idx2++;
                    }else {
                        idx1++;
                        idx2++;
                    }
                    hasEdit = true;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 面试题 01.07. 旋转矩阵
     * 给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。
     * 不占用额外内存空间能否做到？
     *
     * 示例 1:
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     *
     * 示例 2:
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     */
    public void rotate(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix.length!=matrix[0].length) {
            return;
        }
        int len = matrix.length;
        for(int i=0;i<len-i-1;i++) {
            for(int j=i;j<len-i-1;j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[len-j-1][i];
                matrix[len-j-1][i] = matrix[len-i-1][len-j-1];
                matrix[len-i-1][len-j-1] = matrix[j][len-i-1];
                matrix[j][len-i-1] = tmp;
            }
        }
    }

    /**
     * 面试题 01.08. 零矩阵
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     * 示例 1：
     * 输入：
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出：
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     *
     * 示例 2：
     * 输入：
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出：
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     */
    public void setZeroes(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row0 = false;
        boolean col0 = false;
        for(int j=0;j<n;j++) {
            if(matrix[0][j]==0) {
                row0 = true;
                break;
            }
        }
        for(int i=0;i<m;i++) {
            if(matrix[i][0]==0) {
                col0 = true;
                break;
            }
        }
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(matrix[i][j]==0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int j=1;j<n;j++) {
            if(matrix[0][j]==0) {
                for(int i=1;i<m;i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i=0;i<m;i++) {
            if(matrix[i][0]==0) {
                for(int j=1;j<n;j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(row0) {
            for(int j=0;j<n;j++) {
                matrix[0][j] = 0;
            }
        }
        if(col0) {
            for(int i=0;i<m;i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 面试题 01.09. 字符串轮转
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
     * （比如，waterbottle是erbottlewat旋转后的字符串）。
     *
     * 示例1:
     *  输入：s1 = "waterbottle", s2 = "erbottlewat"
     *  输出：True
     *
     * 示例2:
     *  输入：s1 = "aa", "aba"
     *  输出：False
     *
     * 提示：
     * 字符串长度在[0, 100000]范围内。
     *
     * 说明:
     * 你能只调用一次检查子串的方法吗？
     */
    public boolean isFlipedString(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return false;
        }
        s1 += s1;
        return s1.contains(s2);
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     * 注意：本题相对原题稍作改动
     *
     * 示例：
     * 输入： 1->2->3->4->5 和 k = 2
     * 输出： 4
     *
     * 说明：
     * 给定的 k 保证是有效的。
     */
    public int kthToLast(ListNode head, int k) {
        if(head==null||k<1) {
            return -1;
        }
        ListNode right = head;
        while(k-->0) {
            right = right.next;
        }
        ListNode left = head;
        while(right!=null) {
            left = left.next;
            right = right.next;
        }
        return left.val;
    }

    /**
     * 面试题 02.03. 删除中间节点
     * 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。
     *
     * 示例：
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 面试题 02.04. 分割链表
     * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
     * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。
     * 分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
     *
     * 示例:
     * 输入: head = 3->5->8->5->10->2->1, x = 5
     * 输出: 3->1->2->10->5->5->8
     */
    public ListNode partition(ListNode head, int x) {
        if(head==null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode preTail = newHead;
        while(preTail!=null&&preTail.next!=null&&preTail.next.val<x) {
            preTail = preTail.next;
        }
        ListNode curr = preTail.next;
        while(curr!=null&&curr.next!=null) {
            while(curr!=null&&curr.next!=null&&curr.next.val>=x) {
                curr = curr.next;
            }
            if(curr!=null&&curr.next!=null) {
                ListNode preNext = curr.next;
                curr.next = curr.next.next;
                ListNode tmp = preTail.next;
                preTail.next = preNext;
                preNext.next = tmp;
                preTail = preTail.next;
            }
        }
        return newHead.next;
    }

    /**
     * 面试题 02.05. 链表求和
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * 编写函数对这两个整数求和，并用链表形式返回结果。
     *
     * 示例：
     * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
     * 输出：2 -> 1 -> 9，即912
     *
     * 进阶：假设这些数位是正向存放的，请再做一遍。
     * 示例：
     * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
     * 输出：9 -> 1 -> 2，即912
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode reaultTail = result;
        ListNode node1 = l1;
        ListNode node2 = l2;
        int carry = 0;
        while(node1!=null||node2!=null) {
            if(node1!=null) {
                carry += node1.val;
                node1 = node1.next;
            }
            if(node2!=null) {
                carry += node2.val;
                node2 = node2.next;
            }
            reaultTail.next = new ListNode(carry%10);
            reaultTail = reaultTail.next;
            carry = carry/10;
        }
        if(carry>0) {
            reaultTail.next = new ListNode(carry);
        }
        return result.next;
    }

    /**
     * 面试题 02.06. 回文链表
     * 编写一个函数，检查输入的链表是否是回文的。
     *
     * 示例 1：
     * 输入： 1->2
     * 输出： false
     *
     * 示例 2：
     * 输入： 1->2->2->1
     * 输出： true
     *
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast!=null) {
            slow = slow.next;
        }
        ListNode pre = null;
        while(slow!=null) {
            ListNode tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
        }
        ListNode curr1 = head;
        ListNode curr2 = pre;
        while(curr1!=null&&curr2!=null) {
            if(curr1.val!=curr2.val) {
                return false;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return true;
    }

    /**
     * 面试题 02.08. 环路检测
     * 给定一个有环链表，实现一个算法返回环路的开头节点。
     * 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1a
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     *
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：tail connects to node index 0
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     *
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：no cycle
     * 解释：链表中没有环。
     *
     * 进阶：
     * 你是否可以不用额外空间解决此题？
     */
    public ListNode detectCycle(ListNode head) {
        if(head==null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) {
                break;
            }
        }
        if(fast==null||fast.next==null) {
            return null;
        }
        fast = head;
        while(slow!=fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 914. 卡牌分组
     * 给定一副牌，每张牌上都写着一个整数。
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     * 每组都有 X 张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回 true。
     *
     * 示例 1：
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     *
     * 示例 2：
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     *
     * 示例 3：
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     *
     * 示例 4：
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     *
     * 示例 5：
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     *
     * 提示：
     * 1 <= deck.length <= 10000
     * 0 <= deck[i] < 10000
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if(deck==null||deck.length<2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : deck) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        int X = deck.length;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int curr = entry.getValue();
            while(curr!=0) {
                int tmp = X;
                X = curr;
                curr = tmp%curr;
            }
            if(X==1) {
                return false;
            }
        }
        return X>1;
    }

    /**
     * 面试题 04.01. 节点间通路
     * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     *
     * 示例1:
     *  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
     *  输出：true
     *
     * 示例2:
     *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
     *  输出 true
     *
     * 提示：
     * 节点数量n在[0, 1e5]范围内。
     * 节点编号大于等于 0 小于 n。
     * 图中可能存在自环和平行边。
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        if(graph==null||graph.length==0) {
            return false;
        }
        List<Integer>[] neighborTable = new List[n];
        for(int[] border : graph) {
            if(neighborTable[border[0]]==null) {
                neighborTable[border[0]] = new ArrayList<>();
            }
            neighborTable[border[0]].add(border[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> mem = new HashSet<>();
        queue.offer(start);
        mem.add(start);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(neighborTable[curr]!=null) {
                for(int next : neighborTable[curr]) {
                    if(mem.add(next)) {
                        if(next==target) {
                            return true;
                        }
                        queue.offer(next);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 面试题 04.02. 最小高度树
     * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     *
     * 示例:
     * 给定有序数组: [-10,-3,0,5,9],
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *           0
     *          / \
     *        -3   9
     *        /   /
     *      -10  5
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length==0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length-1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if(start>end) {
            return null;
        }
        int mid = (start+end)/2;
        TreeNode currNode = new TreeNode(nums[mid]);
        currNode.left = sortedArrayToBST(nums, start, mid-1);
        currNode.right = sortedArrayToBST(nums, mid+1, end);
        return currNode;
    }

    /**
     * 面试题 04.03. 特定深度节点链表
     * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
     * 返回一个包含所有深度的链表的数组。
     *
     * 示例：
     * 输入：[1,2,3,4,5,null,7,8]
     *         1
     *        /  \
     *       2    3
     *      / \    \
     *     4   5    7
     *    /
     *   8
     * 输出：[[1],[2,3],[4,5,7],[8]]
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if(tree==null) {
            return new ListNode[0];
        }
        List<ListNode> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        while(!queue.isEmpty()) {
            int size = queue.size();
            ListNode head = new ListNode(0);
            ListNode tail = head;
            while(size-->0) {
                TreeNode curr = queue.poll();
                tail.next = new ListNode(curr.val);
                tail = tail.next;
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
            result.add(head.next);
        }
        return result.toArray(new ListNode[result.size()]);
    }

    /**
     * 面试题 04.04. 检查平衡性
     * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
     *
     * 示例 1:
     * 给定二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     *
     * 示例 2:
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *       1
     *      / \
     *     2   2
     *    / \
     *   3   3
     *  / \
     * 4   4
     * 返回 false 。
     */
    public boolean isBalanced(TreeNode root) {
        return depth(root)!=-1;
    }

    public int depth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        return leftDepth==-1||rightDepth==-1||Math.abs(leftDepth-rightDepth)>1?
                -1:Math.max(leftDepth, rightDepth)+1;
    }

    /**
     * 面试题 04.05. 合法二叉搜索树
     * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
     *
     * 示例 1:
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     *
     * 示例 2:
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer left, Integer right) {
        if(root==null) {
            return true;
        }
        if((left!=null&&root.val<=left)||(right!=null&&root.val>=right)) {
            return false;
        }
        return isValidBST(root.left, left, root.val)&&isValidBST(root.right, root.val, right);
    }

    /**
     * 面试题 04.06. 后继者
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     *
     * 示例 1:
     * 输入: root = [2,1,3], p = 1
     *   2
     *  / \
     * 1   3
     * 输出: 2
     *
     * 示例 2:
     * 输入: root = [5,3,6,2,4,null,null,1], p = 6
     *       5
     *      / \
     *     3   6
     *    / \
     *   2   4
     *  /
     * 1
     * 输出: null
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null) {
            return null;
        }
        if(p.right!=null) {
            TreeNode curr = p.right;
            while(curr.left!=null) {
                curr = curr.left;
            }
            return curr;
        }
        TreeNode pre = null;
        TreeNode curr = root;
        while(curr!=null&&curr!=p) {
            if(curr.val>p.val) {
                pre = curr;
                curr = curr.left;
            }else {
                curr = curr.right;
            }
        }
        return curr==p?pre:null;
    }

    /**
     * 面试题 04.08. 首个共同祖先
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。
     * 注意：这不一定是二叉搜索树。
     *
     * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
     *     3
     *    / \
     *   5   1
     *  / \ / \
     * 6  2 0  8
     *   / \
     *  7   4
     * 示例 1:
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输入: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     * 说明:
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return null;
        }
        if(root==p||root==q) {
            return root;
        }
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        if(leftResult==null) {
            return rightResult;
        }
        if(rightResult==null) {
            return leftResult;
        }
        return root;
    }

    /**
     * 面试题 04.09. 二叉搜索树序列
     * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉树，输出所有可能生成此树的数组。
     *
     * 示例:
     * 给定如下二叉树
     *         2
     *        / \
     *       1   3
     * 返回:
     * [
     *    [2,1,3],
     *    [2,3,1]
     * ]
     */
    List<List<Integer>> result_int_list_list = new ArrayList<>();
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        if(root!=null) {
            nodes.add(root);
        }
        BSTSequences(new ArrayList<>(), nodes);
        return result_int_list_list;
    }

    public void BSTSequences(List<Integer> curr, List<TreeNode> nodes) {
        if(nodes.size()==0) {
            result_int_list_list.add(curr);
        }
        for(int i=0;i<nodes.size();i++) {
            List<TreeNode> nextNodes = new ArrayList<>(nodes);
            List<Integer> nextCurr = new ArrayList<>(curr);
            TreeNode node = nextNodes.remove(i);
            nextCurr.add(node.val);
            if(node.left!=null) {
                nextNodes.add(node.left);
            }
            if(node.right!=null) {
                nextNodes.add(node.right);
            }
            BSTSequences(nextCurr, nextNodes);
        }
    }

    /**
     * 面试题 04.10. 检查子树
     * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
     * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
     *
     * 示例1:
     *  输入：t1 = [1, 2, 3], t2 = [2]
     *  输出：true
     *
     * 示例2:
     *  输入：t1 = [1, null, 2, 4], t2 = [3, 2]
     *  输出：false
     *
     * 提示：
     * 树的节点数目范围为[0, 20000]。
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if(t1==null||t2==null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(t1);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(checkSameTree(curr, t2)) {
                return true;
            }
            if(curr.left!=null) {
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
            }
        }
        return false;
    }

    public boolean checkSameTree(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null) {
            return true;
        }
        if(t1==null||t2==null||t1.val!=t2.val) {
            return false;
        }
        return checkSameTree(t1.left, t2.left)&&checkSameTree(t1.right, t2.right);
    }

    /**
     * 面试题 04.12. 求和路径
     * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
     * 设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
     * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，
     * 但是其方向必须向下(只能从父节点指向子节点方向)。
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
     * 返回:3
     * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
     *
     * 提示：
     * 节点总数 <= 10000
     */
    public int pathSum1(TreeNode root, int sum) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            result += pathSum2(curr, sum);
            if(curr.left!=null) {
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
            }
        }
        return result;
    }

    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        if (root.val == sum) {
            result++;
        }
        return result + pathSum2(root.left, sum-root.val)
                + pathSum2(root.right, sum-root.val);
    }

    /**
     * 面试题 05.01. 插入
     * 插入。给定两个32位的整数N与M，以及表示比特位置的i与j。
     * 编写一种方法，将M插入N，使得M从N的第j位开始，到第i位结束。
     * 假定从j位到i位足以容纳M，也即若M = 10 011，那么j和i之间至少可容纳5个位。
     * 例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。
     *
     * 示例1:
     *  输入：N = 10000000000, M = 10011, i = 2, j = 6
     *  输出：N = 10001001100
     *
     * 示例2:
     *  输入： N = 0, M = 11111, i = 0, j = 4
     *  输出：N = 11111
     */
    public int insertBits(int N, int M, int i, int j) {
        for(int k=i;k<=j;k++) {
            if(((1<<k)&N)>0) {
                N -= (1<<k);
            }
        }
        N += M<<i;
        return N;
    }

    /**
     * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。
     * 它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，
     * 直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。
     * 另外，车不能与其他友方（白色）象进入同一个方格。
     *
     * 返回车能够在一次移动中捕获到的卒的数量。
     *
     * 示例 1：
     * 输入：[
     * [".",".",".",".",".",".",".","."],
     * [".",".",".","p",".",".",".","."],
     * [".",".",".","R",".",".",".","p"],
     * [".",".",".",".",".",".",".","."],
     * [".",".",".",".",".",".",".","."],
     * [".",".",".","p",".",".",".","."],
     * [".",".",".",".",".",".",".","."],
     * [".",".",".",".",".",".",".","."]]
     * 输出：3
     * 解释：在本例中，车能够捕获所有的卒。
     *
     * 示例 2：
     * 输入：[
     * [".",".",".",".",".",".",".","."],
     * [".","p","p","p","p","p",".","."],
     * [".","p","p","B","p","p",".","."],
     * [".","p","B","R","B","p",".","."],
     * [".","p","p","B","p","p",".","."],
     * [".","p","p","p","p","p",".","."],
     * [".",".",".",".",".",".",".","."],
     * [".",".",".",".",".",".",".","."]]
     * 输出：0
     * 解释：象阻止了车捕获任何卒。
     *
     * 示例 3：
     * 输入：[
     * [".",".",".",".",".",".",".","."],
     * [".",".",".","p",".",".",".","."],
     * [".",".",".","p",".",".",".","."],
     * ["p","p",".","R",".","p","B","."],
     * [".",".",".",".",".",".",".","."],
     * [".",".",".","B",".",".",".","."],
     * [".",".",".","p",".",".",".","."],
     * [".",".",".",".",".",".",".","."]]
     * 输出：3
     * 解释： 车可以捕获位置 b5，d6 和 f5 的卒。
     *  
     * 提示：
     * board.length == board[i].length == 8
     * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
     * 只有一个格子上存在 board[i][j] == 'R'
     */
    public int numRookCaptures(char[][] board) {
        int result = 0;
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(board[i][j]=='R') {
                    result += numRookCaptures(board, i, j, 0);
                    result += numRookCaptures(board, i, j, 1);
                    result += numRookCaptures(board, i, j, 2);
                    result += numRookCaptures(board, i, j, 3);
                }
            }
        }
        return result;
    }

    public int numRookCaptures(char[][] board, int i, int j, int direct) {
        if(i<0||i>=8||j<0||j>=8||board[i][j]=='B') {
            return 0;
        }
        if(board[i][j]=='p') {
            return 1;
        }
        switch (direct) {
            case 0 : return numRookCaptures(board, i-1, j, direct);
            case 1 : return numRookCaptures(board, i, j-1, direct);
            case 2 : return numRookCaptures(board, i+1, j, direct);
            case 3 : return numRookCaptures(board, i, j+1, direct);
            default : return 0;
        }
    }

    /**
     * 面试题 05.02. 二进制数转字符串
     * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字不在0和1之间，或者无法精确地用32位以内的二进制表示，则打印“ERROR”。
     *
     * 示例1:
     *  输入：0.625
     *  输出："0.101"
     *
     * 示例2:
     *  输入：0.1
     *  输出："ERROR"
     *  提示：0.1无法被二进制准确表示
     *
     * 提示：
     * 32位包括输出中的"0."这两位。
     */
    public String printBin(double num) {
        if(num<0d) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        double curr = 0.5d;
        int cnt = 30;
        while(cnt-->0&&num>0d) {
            if(num>=curr) {
                sb.append('1');
                num -= curr;
            }else {
                sb.append('0');
            }
            curr /= 2d;
        }
        return num>0d?"ERROR":sb.toString();
    }

    /**
     * 面试题 05.04. 下一个数
     * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
     *
     * 示例1:
     *  输入：num = 2（或者0b10）
     *  输出：[4, 1] 或者（[0b100, 0b1]）
     *
     * 示例2:
     *  输入：num = 1
     *  输出：[2, -1]
     *
     * 提示:
     * num的范围在[1, 2147483647]之间；
     * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
     */
    public int[] findClosedNumbers(int num) {
        int[] result = new int[]{-1, -1};
        int left = 2;
        int right = 1;
        int cnt = 0;
        while(right<=num) {
            if((num&left)!=0&&(num&right)==0) {
                result[1] = num/right*right-left+right;
                right >>= 1;
                while(cnt-->0) {
                    result[1] += right;
                    right >>= 1;
                }
                break;
            }
            if((right&num)!=0) {
                cnt++;
            }
            left <<= 1;
            right <<= 1;
        }
        left = 2;
        right = 1;
        cnt = 0;
        while(right<=num) {
            if((num&left)==0&&(num&right)!=0) {
                result[0] = num/right*right-right+left;
                right = 1;
                while(cnt-->0) {
                    result[0] += right;
                    right *= 2;
                }
                break;
            }
            if((right&num)!=0) {
                cnt++;
            }
            left <<= 1;
            right <<= 1;
        }
        return result;
    }

    /**
     * 面试题 05.03. 翻转数位
     * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     *
     * 示例 1：
     * 输入: num = 1775(110111011112)
     * 输出: 8
     *
     * 示例 2：
     * 输入: num = 7(01112)
     * 输出: 4
     */
    public int reverseBits(int num) {
        int result = 0;
        int pre = 0;
        int cnt = 0;
        while(num!=0) {
            if((num&1)==0) {
                result = Math.max(pre+cnt+1, result);
                pre = cnt;
                cnt = 0;
            }else {
                cnt++;
            }
            num >>>= 1;
        }
        return Math.max(pre+cnt+1, result);
    }

    /**
     * 面试题 05.06. 整数转换
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     *
     * 示例1:
     *  输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
     *  输出：2
     *
     * 示例2:
     *  输入：A = 1，B = 2
     *  输出：2
     *
     * 提示:
     * A，B范围在[-2147483648, 2147483647]之间
     */
    public int convertInteger(int A, int B) {
        int result = 0;
        while(A!=0||B!=0) {
            result += (A&1)^(B&1);
            A >>>= 1;
            B >>>= 1;
        }
        return result;
    }

    /**
     * 面试题 05.07. 配对交换
     * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
     *
     * 示例1:
     *  输入：num = 2（或者0b10）
     *  输出 1 (或者 0b01)
     *
     * 示例2:
     *  输入：num = 3
     *  输出：3
     *
     * 提示:
     * num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
     */
    public int exchangeBits(int num) {
        int curr = 2;
        int result = 0;
        while(num>0) {
            result += curr*(num&1);
            num >>= 1;
            result += curr/2*(num&1);
            num >>= 1;
            curr *= 4;
        }
        return result;
    }

    /**
     * 面试题 08.01. 三步问题
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
     * 实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     *
     * 示例1:
     *  输入：n = 3
     *  输出：4
     *  说明: 有四种走法
     *
     * 示例2:
     *  输入：n = 5
     *  输出：13
     *
     * 提示:
     * n范围在[1, 1000000]之间
     */
    public int waysToStep(int n) {
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }
        if(n==3) {
            return 4;
        }
        int pre1 = 1;
        int pre2 = 2;
        int pre3 = 4;
        int curr = 4;
        while(curr++<=n) {
            int tmp = pre3;
            pre3 = ((pre1+pre2)%mod+pre3)%mod;
            pre1 = pre2;
            pre2 = tmp;
        }
        return pre3;
    }

    /**
     * 面试题 08.02. 迷路的机器人
     * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
     * 设计一种算法，寻找机器人从左上角移动到右下角的路径。
     *
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。
     *
     * 示例 1:
     * 输入:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
     *
     * 解释:
     * 输入中标粗的位置即为输出表示的路径，即
     * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
     * 说明：r 和 c 的值均不超过 100。
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> result = new LinkedList<>();
        if(obstacleGrid==null||obstacleGrid.length==0||obstacleGrid[0].length==0) {
            return result;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        boolean[][] canGet = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(i==0&&j==0) {
                    canGet[i][j] = obstacleGrid[i][j]==0;
                }else if(((i>0&&canGet[i-1][j])||(j>0&&canGet[i][j-1]))&&obstacleGrid[i][j]==0) {
                    canGet[i][j] = true;
                }
            }
        }
        if(!canGet[m-1][n-1]) {
            return result;
        }
        int i = m-1;
        int j = n-1;
        while(i>=0&&j>=0) {
            if(i>0&&canGet[i-1][j]) {
                List<Integer> list = new ArrayList<>();
                list.add(i--);
                list.add(j);
                result.add(0, list);
            }else if(j>0&&canGet[i][j-1]) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j--);
                result.add(0, list);
            }else {
                break;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        result.add(0, list);
        return result;
    }

    /**
     * 面试题 08.03. 魔术索引
     * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
     * 给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
     * 若有多个魔术索引，返回索引值最小的一个。
     *
     * 示例1:
     *  输入：nums = [0, 2, 3, 4, 5]
     *  输出：0
     *  说明: 0下标的元素为0
     *
     * 示例2:
     *  输入：nums = [1, 1, 1]
     *  输出：1
     *
     * 提示:
     * nums长度在[1, 1000000]之间
     */
    public int findMagicIndex(int[] nums) {
        if(nums==null||nums.length==0) {
            return -1;
        }
        return findMagicIndex(nums, 0, nums.length-1);
    }

    public int findMagicIndex(int[] nums, int start, int end) {
        if(start>end) {
            return -1;
        }
        if(start==end) {
            return nums[start]==start?start:-1;
        }
        int left = start;
        int right = end;
        int mid = (left+right)/2;
        if(nums[mid]>mid) {
            int leftResult = findMagicIndex(nums, start, mid-1);
            int rightResult = findMagicIndex(nums, nums[mid], end);
            return leftResult!=-1?leftResult:rightResult;
        }else if(nums[mid]<mid) {
            int leftResult = findMagicIndex(nums, start, nums[mid]);
            int rightResult = findMagicIndex(nums, mid+1, end);
            return leftResult!=-1?leftResult:rightResult;
        }else {
            return findMagicIndex(nums, start, mid);
        }
    }

    /**
     * 面试题 08.04. 幂集
     * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     * 说明：解集不能包含重复的子集。
     * 示例:
     *  输入： nums = [1,2,3]
     *  输出：
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null) {
            return result;
        }
        subsets(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }

    public void subsets(List<List<Integer>> result, List<Integer> curr, int[] nums, int idx) {
        if(idx>=nums.length) {
            result.add(new ArrayList(curr));
            return;
        }
        subsets(result, new ArrayList(curr), nums, idx+1);
        List<Integer> list = new ArrayList<>(curr);
        list.add(nums[idx]);
        subsets(result, list, nums, idx+1);
    }

    /**
     * 面试题 08.05. 递归乘法
     * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     *
     * 示例1:
     *  输入：A = 1, B = 10
     *  输出：10
     *
     * 示例2:
     *  输入：A = 3, B = 4
     *  输出：12
     *
     * 提示:
     * 保证乘法范围不会溢出
     */
    public int multiply(int A, int B) {
        if(A==0||B==0) {
            return 0;
        }
        if(A<B) {
            return multiply(B, A);
        }
        if(B==1) {
            return A;
        }
        int pre = B>>1;
        int remain = B-pre-pre;
        int bRes = multiply(A, pre);
        return bRes+bRes+multiply(A, remain);
    }

    /**
     * 面试题 08.06. 汉诺塔问题
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * (1) 每次只能移动一个盘子;
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * (3) 盘子只能叠在比它大的盘子上。
     * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
     * 你需要原地修改栈。
     *
     * 示例1:
     *  输入：A = [2, 1, 0], B = [], C = []
     *  输出：C = [2, 1, 0]
     *
     * 示例2:
     *  输入：A = [1, 0], B = [], C = []
     *  输出：C = [1, 0]
     *
     * 提示:
     * A中盘子的数目不大于14个。
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        if(A==null||A.size()==0) {
            return;
        }
        hanota(A.size(), A, B, C);
    }

    public void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if(n==1) {
            C.add(A.remove(A.size()-1));
            return;
        }
        hanota(n-1, A, C, B);
        hanota(1, A, B, C);
        hanota(n-1, B, A, C);
    }

    /**
     * 面试题 08.07. 无重复字符串的排列组合
     * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
     *
     * 示例1:
     *  输入：S = "qwe"
     *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
     *
     * 示例2:
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     *
     * 提示:
     * 字符都是英文字母。
     * 字符串长度在[1, 9]之间。
     */
    public String[] permutation2(String S) {
        if(S==null||S.length()==0) {
            return new String[0];
        }
        List<String> result = new ArrayList<>();
        result.add(S);
        int len = S.length();
        for(int i=0;i<len;i++) {
            int size = result.size();
            for(int j=i+1;j<len;j++) {
                for(int k=0;k<size;k++) {
                    char[] chars = result.get(k).toCharArray();
                    char tmp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = tmp;
                    result.add(String.valueOf(chars));
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * 面试题 08.08. 有重复字符串的排列组合
     * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
     *
     * 示例1:
     *  输入：S = "qqe"
     *  输出：["eqq","qeq","qqe"]
     *
     * 示例2:
     *  输入：S = "ab"
     *  输出：["ab", "ba"]
     *
     * 提示:
     * 字符都是英文字母。
     * 字符串长度在[1, 9]之间。
     */
    public String[] permutation1(String S) {
        if(S==null||S.length()==0) {
            return new String[0];
        }
        int[] cnts = new int[52];
        for(char c : S.toCharArray()) {
            if(c>='a') {
                cnts[c-'a']++;
            }else {
                cnts[c-'A'+26]++;
            }
        }
        List<String> result = new ArrayList<>();
        permutation(result, cnts, "");
        return result.toArray(new String[result.size()]);
    }

    public void permutation(List<String> result, int[] cnts, String curr) {
        boolean done = true;
        for(int cnt : cnts) {
            if(cnt!=0) {
                done = false;
                break;
            }
        }
        if(done) {
            result.add(curr);
            return ;
        }
        for(int i=0;i<52;i++) {
            if(cnts[i]>0) {
                cnts[i]--;
                if(i<26) {
                    permutation(result, cnts, curr+(char)(i+'a'));
                }else {
                    permutation(result, cnts, curr+(char)(i-26+'A'));
                }
                cnts[i]++;
            }
        }
    }

    /**
     * 面试题 08.09. 括号
     * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
     * 说明：解集不能包含重复的子集。
     * 例如，给出 n = 3，生成结果为：
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesis(result, "", n, n);
        return result;
    }

    public void generateParenthesis(List<String> result, String curr, int left, int right) {
        if(left==0&&right==0) {
            result.add(curr);
            return;
        }
        if(left>0) {
            generateParenthesis(result, curr+'(', left-1, right);
        }
        if(right>left) {
            generateParenthesis(result, curr+')', left, right-1);
        }
    }

    /**
     * 面试题 08.10. 颜色填充
     * 颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。
     * 给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，
     * 将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
     *
     * 示例1:
     *  输入：
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     *  输出：[[2,2,2],[2,2,0],[2,0,1]]
     *  解释:
     * 在图像的正中间，(坐标(sr,sc)=(1,1)),
     * 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2，
     * 因为它不是在上下左右四个方向上与初始点相连的像素点。
     *
     * 说明：
     * image 和 image[0] 的长度在范围 [1, 50] 内。
     * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
     * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image==null||image.length==0||image[0].length==0) {
            return image;
        }
        int m = image.length;
        int n = image[0].length;
        if(sr<0||sr>=m||sc<0||sc>=n) {
            return image;
        }
        int src = image[sr][sc];
        if(src!=newColor) {
            floodFill(image, sr, sc, src, newColor);
        }
        return image;
    }

    public void floodFill(int[][] image, int i, int j, int color, int newColor) {
        if(i<0||i>=image.length||j<0||j>=image[0].length||image[i][j]!=color) {
            return;
        }
        image[i][j] = newColor;
        floodFill(image, i-1, j, color, newColor);
        floodFill(image, i+1, j, color, newColor);
        floodFill(image, i, j-1, color, newColor);
        floodFill(image, i, j+1, color, newColor);
    }

    /**
     * 面试题 08.11. 硬币
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
     * (结果可能会很大，你需要将结果模上1000000007)
     *
     * 示例1:
     *  输入: n = 5
     *  输出：2
     *  解释: 有两种方式可以凑成总金额:
     * 5=5
     * 5=1+1+1+1+1
     *
     * 示例2:
     *  输入: n = 10
     *  输出：4
     *  解释: 有四种方式可以凑成总金额:
     * 10=10
     * 10=5+5
     * 10=5+1+1+1+1+1
     * 10=1+1+1+1+1+1+1+1+1+1
     *
     * 说明：
     * 注意:
     * 你可以假设：
     * 0 <= n (总金额) <= 1000000
     */
    public int waysToChange(int n) {
        int[] dp = new int[n+1];
        int[] coins = new int[]{1,5,10,25};
        dp[0] = 1;
        for(int coin : coins) {
            for(int i=1;i<=n;i++) {
                if(i>=coin) {
                    dp[i] = (dp[i]+dp[i-coin])%mod;
                }
            }
        }
        return dp[n];
    }

    /**
     * 面试题 08.14. 布尔运算
     * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
     * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
     *
     * 示例 1:
     * 输入: s = "1^0|0|1", result = 0
     * 输出: 2
     *
     * 解释: 两种可能的括号方法是
     * 1^(0|(0|1))
     * 1^((0|0)|1)
     *
     * 示例 2:
     * 输入: s = "0&0&0&1^1|0", result = 1
     * 输出: 10
     *
     * 提示：
     * 运算符的数量不超过 19 个
     */
    public int countEval(String s, int result) {
        if(s==null||s.length()==0||(result!=0&&result!=1)) {
            return 0;
        }
        int len = s.length();
        int[][][] dp = new int[len][len][2];
        for(int i=0;i<len;i+=2) {
            char c = s.charAt(i);
            if(c=='0') {
                dp[i][i][0] = 1;
            }else {
                dp[i][i][1] = 1;
            }
        }
        for(int i=2;i<len;i+=2) {
            for(int j=0;j+i<len;j+=2) {
                for(int k=j+1;k<i+j;k+=2) {
                    int pre0 =  dp[j][k-1][0];
                    int pre1 =  dp[j][k-1][1];
                    int post0 = dp[k+1][i+j][0];
                    int post1 = dp[k+1][i+j][1];
                    switch(s.charAt(k)) {
                        case '&' :
                            dp[j][i+j][0] += pre0*(post0+post1)+pre1*post0;
                            dp[j][i+j][1] += pre1*post1;
                            break;
                        case '|' :
                            dp[j][i+j][0] += pre0*post0;
                            dp[j][i+j][1] += pre1*(post0+post1)+pre0*post1;
                            break;
                        case '^' :
                            dp[j][i+j][0] += pre0*post0+pre1*post1;
                            dp[j][i+j][1] += pre1*post0+pre0*post1;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return dp[0][len-1][result];
    }

    /**
     * 面试题 10.02. 变位词组
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     * 注意：本题相对原题稍作修改
     *
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if(strs==null||strs.length==0) {
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            int[] cnts = new int[26];
            for(char c : s.toCharArray()) {
                cnts[c-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int cnt : cnts) {
                sb.append(cnt).append('#');
            }
            String tmp = sb.toString();
            if(!map.containsKey(tmp)) {
                map.put(tmp, new ArrayList<String>());
            }
            map.get(tmp).add(s);
        }
        for(Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * 面试题 10.03. 搜索旋转数组
     * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
     * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
     *
     * 示例1:
     *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
     *  输出: 8（元素5在该数组中的索引）
     *
     * 示例2:
     *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
     *  输出：-1 （没有找到）
     *
     * 提示:
     * arr 长度范围在[1, 1000000]之间
     */
    public int search1(int[] arr, int target) {
        if(arr==null||arr.length==0) {
            return -1;
        }
        int left = 0;
        int right = arr.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(arr[left]<arr[mid]) {
                if(arr[left]<=target&&target<=arr[mid]) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }else if(arr[left]>arr[mid]) {
                if(target>=arr[left]||target<=arr[mid]) {
                    right = mid;
                }else {
                    left = mid+1;
                }
            }else {
                if(arr[left]!=target) {
                    left++;
                }else {
                    return left;
                }
            }
        }
        return arr[left]==target?left:-1;
    }

    /**
     * 面试题 10.05. 稀疏数组搜索
     * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
     *
     * 示例1:
     *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
     *  输出：-1
     *  说明: 不存在返回-1。
     *
     * 示例2:
     *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
     *  输出：4
     *
     * 提示:
     * words的长度在[1, 1000000]之间
     */
    public int findString(String[] words, String s) {
        if(words==null||words.length==0) {
            return -1;
        }
        int left = 0;
        int right = words.length-1;
        while(left<right) {
            while(left<right&&words[left].equals("")) {
                left++;
            }
            while(left<right&&words[right].equals("")) {
                right--;
            }
            int mid = (left+right)/2;
            while(mid<right&&words[mid].equals("")) {
                mid++;
            }
            if(words[mid].compareTo(s)<0) {
                left = mid+1;
            }else if(words[mid].compareTo(s)>0) {
                right=mid-1;
            }else {
                return mid;
            }
        }
        return left==right&&words[left].equals(s)?left:-1;
    }

    /**
     * 面试题 10.09. 排序矩阵查找
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     *
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n-1;
        while(i<m&&j>=0) {
            if(matrix[i][j]>target) {
                j--;
            }else if(matrix[i][j]<target) {
                i++;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 面试题 10.11. 峰与谷
     * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
     * 例如，在数组{5, 8, 6, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。
     * 现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
     *
     * 示例:
     * 输入: [5, 3, 1, 2, 3]
     * 输出: [5, 1, 3, 2, 3]
     *
     * 提示：
     * nums.length <= 10000
     */
    public void wiggleSort(int[] nums) {
        if(nums==null||nums.length<3) {
            return;
        }
        for(int i=1;i<nums.length;i++) {
            if((i%2==1&&nums[i]>nums[i-1])||(i%2==0&&nums[i]<nums[i-1])) {
                int tmp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = tmp;
            }
        }
    }

    /**
     * 面试题 16.01. 交换数字
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     *
     * 示例：
     * 输入: numbers = [1,2]
     * 输出: [2,1]
     *
     * 提示：
     * numbers.length == 2
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0]+numbers[1]-numbers[0];
        numbers[1] = numbers[0]^numbers[1];
        numbers[0] = numbers[0]^numbers[1];
        return new int[]{numbers[0]+numbers[1]-numbers[0], numbers[0]+numbers[1]-numbers[1]};
    }

    /**
     * 面试题 16.04. 井字游戏
     * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
     * 以下是井字游戏的规则：
     * 玩家轮流将字符放入空位（" "）中。
     * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
     * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
     * 当所有位置非空时，也算为游戏结束。
     * 如果游戏结束，玩家不允许再放置字符。
     * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；
     * 如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
     *
     * 示例 1：
     * 输入： board = ["O X"," XO","X O"]
     * 输出： "X"
     *
     * 示例 2：
     * 输入： board = ["OOX","XXO","OXO"]
     * 输出： "Draw"
     * 解释： 没有玩家获胜且不存在空位
     *
     * 示例 3：
     * 输入： board = ["OOX","XXO","OX "]
     * 输出： "Pending"
     * 解释： 没有玩家获胜且仍存在空位
     *
     * 提示：
     * 1 <= board.length == board[i].length <= 100
     * 输入一定遵循井字棋规则
     */
    public String tictactoe(String[] board) {
        if(board==null||board.length==0) {
            return null;
        }
        boolean pending = false;
        int len = board.length;
        int cnt = 0;
        for(int i=0;i<len;i++) {
            cnt = 0;
            for(int j=0;j<len;j++) {
                char c = board[i].charAt(j);
                if(c=='X') {
                    cnt++;
                }else if(c=='O') {
                    cnt--;
                }else {
                    pending = true;
                    break;
                }
            }
            if(cnt==len) {
                return "X";
            }else if(cnt==-len) {
                return "O";
            }
        }
        for(int i=0;i<len;i++) {
            cnt = 0;
            for(int j=0;j<len;j++) {
                char c = board[j].charAt(i);
                if(c=='X') {
                    cnt++;
                }else if(c=='O') {
                    cnt--;
                }else {
                    pending = true;
                    break;
                }
            }
            if(cnt==len) {
                return "X";
            }else if(cnt==-len) {
                return "O";
            }
        }
        cnt = 0;
        for(int i=0;i<len;i++) {
            char c = board[i].charAt(i);
            if(c=='X') {
                cnt++;
            }else if(c=='O') {
                cnt--;
            }else {
                pending = true;
                break;
            }
        }
        if(cnt==len) {
            return "X";
        }else if(cnt==-len) {
            return "O";
        }
        cnt = 0;
        for(int i=0;i<len;i++) {
            char c = board[i].charAt(len-1-i);
            if(c=='X') {
                cnt++;
            }else if(c=='O') {
                cnt--;
            }else {
                pending = true;
                break;
            }
        }
        if(cnt==len) {
            return "X";
        }else if(cnt==-len) {
            return "O";
        }
        return pending?"Pending":"Draw";
    }

    /**
     * 面试题 16.05. 阶乘尾数
     * 设计一个算法，算出 n 阶乘有多少个尾随零。
     *
     * 示例 1:
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     *
     * 示例 2:
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * 说明: 你算法的时间复杂度应为 O(log n) 。
     */
    public int trailingZeroes(int n) {
        int result = 0;
        while(n>=5) {
            n /= 5;
            result += n;
        }
        return result;
    }

    /**
     * 面试题 16.06. 最小差
     * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     *
     * 示例：
     * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
     * 输出： 3，即数值对(11, 8)
     *
     * 提示：
     * 1 <= a.length, b.length <= 100000
     * -2147483648 <= a[i], b[i] <= 2147483647
     * 正确结果在区间[-2147483648, 2147483647]内
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int idxA = 0;
        int idxB = 0;
        long min = Long.MAX_VALUE;
        while(idxA<a.length&&idxB<b.length) {
            if(a[idxA]==b[idxB]) {
                return 0;
            }else if(a[idxA]>b[idxB]) {
                long curr = (long)a[idxA]-(long)b[idxB];
                if(curr<min) {
                    min = curr;
                }
                idxB++;
            }else {
                long curr = (long)b[idxB]-(long)a[idxA];
                if(curr<min) {
                    min = curr;
                }
                idxA++;
            }
        }
        return (int)min;
    }

    /**
     * 面试题 16.07. 最大数值
     * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
     *
     * 示例：
     * 输入： a = 1, b = 2
     * 输出： 2
     */
    public int maximum(int a, int b) {
        int i = (int)(((long)a-(long)b)>>>63);
        return (i^1)*a + i*b;
    }

    /**
     * 面试题 16.10. 生存人数
     * 给定N个人的出生年份和死亡年份，第i个人的出生年份为birth[i]，死亡年份为death[i]，实现一个方法以计算生存人数最多的年份。
     * 你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。
     * 例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。
     * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
     *
     * 示例：
     * 输入：
     * birth = {1900, 1901, 1950}
     * death = {1948, 1951, 2000}
     * 输出： 1901
     *
     * 提示：
     * 0 < birth.length == death.length <= 10000
     * birth[i] <= death[i]
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int[] cnts = new int[102];
        int len = birth.length;
        for(int i=0;i<len;i++) {
            cnts[birth[i]-1900]++;
            cnts[death[i]-1900+1]--;
        }
        int result = 0;
        int sum = cnts[0];
        int max = cnts[0];
        for(int i=1;i<101;i++) {
            sum += cnts[i];
            if(sum>max) {
                result = i;
                max = sum;
            }
        }
        return 1900+result;
    }

    /**
     * 面试题 16.11. 跳水板
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。
     * 你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     *
     * 返回的长度需要从小到大排列。
     *
     * 示例：
     * 输入：
     * shorter = 1
     * longer = 2
     * k = 3
     * 输出： {3,4,5,6}
     *
     * 提示：
     * 0 < shorter <= longer
     * 0 <= k <= 100000
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k<1) {
            return new int[0];
        }
        if(shorter==longer) {
            return new int[]{shorter*k};
        }
        int[] res = new int[k+1];
        res[0] = shorter*k;
        int dis = longer-shorter;
        for(int i=1;i<=k;i++) {
            res[i] = res[i-1]+dis;
        }
        return res;
    }

    /**
     * 面试题 16.15. 珠玑妙算
     * 珠玑妙算游戏（the game of master mind）的玩法如下。
     * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
     * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。
     * 作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
     * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
     *
     * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
     *
     * 示例：
     * 输入： solution="RGBY",guess="GGRR"
     * 输出： [1,1]
     * 解释： 猜中1次，伪猜中1次。
     *
     * 提示：
     * len(solution) = len(guess) = 4
     * solution和guess仅包含"R","G","B","Y"这4种字符
     */
    public int[] masterMind(String solution, String guess) {
        int res0 = 0;
        int[] cntSol = new int[4];
        int[] cntGue = new int[4];
        for(int i=0;i<4;i++) {
            char c1 = solution.charAt(i);
            char c2 = guess.charAt(i);
            if(c1==c2) {
                res0++;
            }else {
                switch(c1) {
                    case 'R':cntSol[0]++; break;
                    case 'Y':cntSol[1]++; break;
                    case 'G':cntSol[2]++; break;
                    case 'B':cntSol[3]++; break;
                }
                switch(c2) {
                    case 'R':cntGue[0]++; break;
                    case 'Y':cntGue[1]++; break;
                    case 'G':cntGue[2]++; break;
                    case 'B':cntGue[3]++; break;
                }
            }
        }
        int res1 = 0;
        for(int i=0;i<4;i++) {
            res1 += Math.min(cntSol[i], cntGue[i]);
        }
        return new int[]{res0, res1};
    }

    /**
     * 面试题 16.16. 部分排序
     * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
     * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
     *
     * 示例：
     * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
     * 输出： [3,9]
     *
     * 提示：
     * 0 <= len(array) <= 1000000
     */
    public int[] subSort(int[] array) {
        if(array==null||array.length<2) {
            return new int[]{-1,-1};
        }
        int right = -1;
        int max = array[0];
        for(int i=0;i<array.length;i++) {
            if(array[i]<max) {
                right = i;
            }else {
                max = array[i];
            }
        }
        if(right==-1) {
            return new int[]{-1,-1};
        }
        int left = array.length;
        int min = array[array.length-1];
        for(int i=array.length-1;i>=0;i--) {
            if(array[i]>min) {
                left = i;
            }else {
                min = array[i];
            }
        }
        return new int[]{left, right};
    }

    /**
     * 面试题 16.19. 水域大小
     * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
     * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
     * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     *
     * 示例：
     * 输入：
     * [
     *   [0,2,1,0],
     *   [0,1,0,1],
     *   [1,1,0,1],
     *   [0,1,0,1]
     * ]
     * 输出： [1,2,4]
     *
     * 提示：
     * 0 < len(land) <= 1000
     * 0 < len(land[i]) <= 1000
     */
    public int[] pondSizes(int[][] land) {
        if(land==null||land.length==0||land[0].length==0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<land.length;i++) {
            for(int j=0;j<land[0].length;j++) {
                if(land[i][j]==0) {
                    list.add(pondSizes(land, i, j));
                }
            }
        }
        int[] result = new int[list.size()];
        for(int i=0;i<result.length;i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int pondSizes(int[][] land, int i, int j) {
        int result = 0;
        if(i<0||i>=land.length||j<0||j>=land[0].length||land[i][j]!=0) {
            return result;
        }else {
            land[i][j] = 1;
            result++;
            for(int k=-1;k<=1;k++) {
                for(int l=-1;l<=1;l++) {
                    result += pondSizes(land, i+k, j+l);
                }
            }
            return result;
        }
    }

    /**
     * 面试题 16.20. T9键盘
     * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
     *
     * 示例 1:
     * 输入: num = "8733", words = ["tree", "used"]
     * 输出: ["tree", "used"]
     *
     * 示例 2:
     * 输入: num = "2", words = ["a", "b", "c", "d"]
     * 输出: ["a", "b", "c"]
     *
     * 提示：
     * num.length <= 1000
     * words.length <= 500
     * words[i].length == num.length
     * num中不会出现 0, 1 这两个数字
     */
    private String[] dict = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> getValidT9Words(String num, String[] words) {
        List<String> result = new ArrayList<>();
        if(num==null||num.length()==0||words==null||words.length==0) {
            return result;
        }
        for(String word : words) {
            if(word.length()==num.length()&&getValidT9Words(num, 0, word)) {
                result.add(word);
            }
        }
        return result;
    }

    public boolean getValidT9Words(String num, int idx, String word) {
        if(idx==num.length()) {
            return true;
        }
        for(char c : dict[num.charAt(idx)-'2'].toCharArray()) {
            if(c==word.charAt(idx)) {
                return getValidT9Words(num, idx+1, word);
            }
        }
        return false;
    }

    /**
     * 面试题 16.21. 交换和
     * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
     * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
     * 若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
     *
     * 示例:
     * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
     * 输出: [1, 3]
     *
     * 示例:
     * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
     * 输出: []
     *
     * 提示：
     * 1 <= array1.length, array2.length <= 100000
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        if(array1==null||array1.length==0||array2==null||array2.length==0) {
            return new int[0];
        }
        int sum1 = 0;
        for(int num : array1) {
            sum1 += num;
        }
        int sum2 = 0;
        Set<Integer> set = new HashSet<>();
        for(int num : array2) {
            set.add(num);
            sum2 += num;
        }
        if((sum1-sum2)%2!=0) {
            return new int[0];
        }
        for(int i=0;i<array1.length;i++) {
            int target = array1[i] - (sum1-sum2)/2;
            if(set.contains(target)) {
                return new int[]{array1[i], target};
            }
        }
        return new int[0];
    }

    /**
     * 面试题 16.22. 兰顿蚂蚁
     * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
     * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
     * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
     * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
     * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，黑色方格由 'X' 表示，白色方格由 '_' 表示，
     * 蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，分别表示蚂蚁 左、上、右、下 的朝向。
     * 只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
     *
     * 示例 1:
     * 输入: 0
     * 输出: ["R"]
     *
     * 示例 2:
     * 输入: 2
     * 输出:
     * [
     *   "_X",
     *   "LX"
     * ]
     *
     * 示例 3:
     * 输入: 5
     * 输出:
     * [
     *   "_U",
     *   "X_",
     *   "XX"
     * ]
     *
     * 说明：
     * K <= 100000
     */
    public List<String> printKMoves(int K) {
        List<String> result = new ArrayList<>();
        if(K<0) {
            return result;
        }
        char[] poss = new char[]{'R', 'D', 'L', 'U'};
        Set<String> set = new HashSet<>();
        int[] edges = new int[4];
        int currDirect = 0;
        int[] currPos = new int[2];
        while(K-->0) {
            String currPosStr = currPos[0]+","+currPos[1];
            if(set.contains(currPosStr)) {
                set.remove(currPosStr);
                currDirect = (currDirect+3)%4;
            }else {
                set.add(currPosStr);
                currDirect = (currDirect+1)%4;
            }
            switch(currDirect) {
                case 0 : currPos[0] += 1; break;
                case 1 : currPos[1] += 1; break;
                case 2 : currPos[0] -= 1; break;
                case 3 : currPos[1] -= 1; break;
            }
            updateEdges(edges, currPos);
        }
        for(int i=edges[3];i<=edges[1];i++) {
            StringBuilder curr = new StringBuilder();
            for(int j=edges[2];j<=edges[0];j++) {
                if(i==currPos[1]&&j==currPos[0]) {
                    curr.append(poss[currDirect]);
                }else {
                    curr.append(set.contains(j+","+i)?'X':'_');
                }
            }
            result.add(curr.toString());
        }
        return result;
    }

    public void updateEdges(int[] edges, int[] currPos) {
        edges[0] = Math.max(edges[0], currPos[0]);
        edges[1] = Math.max(edges[1], currPos[1]);
        edges[2] = Math.min(edges[2], currPos[0]);
        edges[3] = Math.min(edges[3], currPos[1]);
    }

    /**
     * 面试题 16.24. 数对和
     * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
     *
     * 示例 1:
     * 输入: nums = [5,6,5], target = 11
     * 输出: [[5,6]]
     *
     * 示例 2:
     * 输入: nums = [5,6,5,6], target = 11
     * 输出: [[5,6],[5,6]]
     *
     * 提示：
     * nums.length <= 100000
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null||nums.length<2) {
            return result;
        }
        Map<Integer, Integer> mem = new HashMap<>();
        for(int num : nums) {
            if(mem.getOrDefault(target-num, 0)>0) {
                mem.put(target-num, mem.get(target-num)-1);
                List<Integer> list = new ArrayList<>();
                list.add(num);
                list.add(target-num);
                result.add(list);
            }else {
                mem.put(num, mem.getOrDefault(num, 0)+1);
            }
        }
        return result;
    }

    /**
     * 面试题 16.26. 计算器
     * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
     * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     *
     * 示例 1:
     * 输入: "3+2*2"
     * 输出: 7
     *
     * 示例 2:
     * 输入: " 3/2 "
     * 输出: 1
     *
     * 示例 3:
     * 输入: " 3+5 / 2 "
     * 输出: 5
     *
     * 说明：
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     */
    public int calculate(String s) {
        int num = 0;
        char pre = '+';
        Stack<Integer> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c==' ') {
                continue;
            }else if(c>='0'&&c<='9') {
                num = num*10+c-'0';
            }else {
                if(pre=='*') {
                    stack.push(stack.pop()*num);
                }else if(pre=='/') {
                    stack.push(stack.pop()/num);
                }else if(pre=='+') {
                    stack.push(num);
                }else {
                    stack.push(-num);
                }
                num = 0;
                pre = c;
            }
        }
        if(pre=='*') {
            stack.push(stack.pop()*num);
        }else if(pre=='/') {
            stack.push(stack.pop()/num);
        }else if(pre=='+') {
            stack.push(num);
        }else {
            stack.push(-num);
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    /**
     * 面试题 17.01. 不用加号的加法
     * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     *
     * 示例:
     * 输入: a = 1, b = 1
     * 输出: 2
     *
     * 提示：
     * a, b 均可能是负数或 0
     * 结果不会溢出 32 位整数
     */
    public int add1(int a, int b) {
        int res = a;
        int carry = b;
        while(carry!=0) {
            int tmp = res;
            res ^= carry;
            carry = (carry&tmp)<<1;
        }
        return res;
    }

    /**
     * 面试题 17.04. 消失的数字
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     * 注意：本题相对书上原题稍作改动
     *
     * 示例 1：
     * 输入：[3,0,1]
     * 输出：2
     *
     * 示例 2：
     * 输入：[9,6,4,2,3,5,7,0,1]
     * 输出：8
     */
    public int missingNumber1(int[] nums) {
        if(nums==null) {
            return -1;
        }
        int n = nums.length;
        int res = 0;
        for(int i=0;i<n;i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res^n;
        /*int idx = 0;
        while(idx<nums.length) {
            if(nums[idx]!=n||nums[idx]!=idx) {
                int tmp = nums[nums[idx]];
                nums[nums[idx]] = nums[idx];
                nums[idx] = tmp;
            }else {
                idx++;
            }
        }
        for(int i=0;i<n;i++) {
            if(nums[idx]!=idx) {
                return idx;
            }
        }
        return n;*/
    }

    /**
     * 面试题 17.05.  字母与数字
     * 给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。
     * 返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。
     *
     * 示例 1:
     * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
     * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
     *
     * 示例 2:
     * 输入: ["A","A"]
     * 输出: []
     *
     * 提示：
     * array.length <= 100000
     */
    public String[] findLongestSubarray(String[] array) {
        if(array==null||array.length==0) {
            return new String[0];
        }
        int len = array.length;
        int[] mem = new int[len+1];
        for(int i=1;i<=len;i++) {
            mem[i] = mem[i-1]+(array[i-1].charAt(0)>='0'&&array[i-1].charAt(0)<='9'?1:-1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        for(int i=0;i<=len;i++) {
            if(map.containsKey(mem[i])) {
                int pre = map.get(mem[i]);
                if(i-pre>right-left) {
                    right = i;
                    left = pre;
                }
            }else {
                map.put(mem[i], i);
            }
        }
        return Arrays.copyOfRange(array, left,right-1);
    }

    /**
     * 面试题 17.06. 2出现的次数
     * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
     *
     * 示例:
     * 输入: 25
     * 输出: 9
     * 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
     *
     * 提示：
     * n <= 10^9
     */
    public int numberOf2sInRange(int n) {
        if(n<2) {
            return 0;
        }
        int tmp = n;
        int bitIdx = 0;
        int result = 0;
        while(tmp>0) {
            int curr = tmp%10;
            tmp /= 10;
            int left = tmp;
            int right = n%(int)Math.pow(10, bitIdx)+1;
            result += left*(int)Math.pow(10, bitIdx);
            if(curr==2) {
                result += right;
            }
            if(curr>2) {
                result += (int)Math.pow(10, bitIdx);
            }
            bitIdx++;
        }
        return result;
    }

    /**
     * 面试题 17.11. 单词距离
     * 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
     * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
     *
     * 示例：
     * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
     * 输出：1
     *
     * 提示：
     * words.length <= 100000
     */
    public int findClosest(String[] words, String word1, String word2) {
        if(words==null||words.length==0||word1==null||word2==null) {
            return -1;
        }
        if(word1.equals(word2)) {
            return 0;
        }
        int result = words.length;
        int idx1 = -1;
        int idx2 = -1;
        for(int i=0;i<words.length;i++) {
            if(words[i].equals(word1)) {
                idx1 = i;
                if(idx2!=-1) {
                    result = Math.min(result, idx1-idx2);
                }
            }else if(words[i].equals(word2)) {
                idx2 = i;
                if(idx1!=-1) {
                    result = Math.min(result, idx2-idx1);
                }
            }
        }
        return result;
    }

    /**
     * 面试题 17.12. BiNode
     * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
     * 返回转换后的单向链表的头节点。
     * 注意：本题相对原题稍作改动
     *
     * 示例：
     * 输入： [4,2,5,1,3,null,6,0]
     * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
     *
     * 提示：
     * 节点数量不会超过 100000。
     */
    public TreeNode convertBiNode(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode left = convertBiNode(root.left);
        TreeNode right = convertBiNode(root.right);
        root.left = null;
        root.right = right;
        if(left==null) {
            return root;
        }
        TreeNode result = left;
        while(left.right!=null) {
            left = left.right;
        }
        left.right = root;
        return result;
    }

    /**
     * 面试题 17.14. 最小K个数
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     *
     * 示例：
     *
     * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
     * 输出： [1,2,3,4]
     * 提示：
     *
     * 0 <= len(arr) <= 100000
     * 0 <= k <= min(100000, len(arr))
     */
    public int[] smallestK(int[] arr, int k) {
        if(arr==null||arr.length==0||k<=0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2)->o2-o1);
        for(int i : arr) {
            queue.offer(i);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        int[] result = new int[k];
        while(k-->0) {
            result[k] = queue.poll();
        }
        return result;
    }

    /**
     * 面试题 17.16. 按摩师
     * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
     * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
     * 注意：本题相对原题稍作改动
     *
     * 示例 1：
     * 输入： [1,2,3,1]
     * 输出： 4
     * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
     *
     * 示例 2：
     * 输入： [2,7,9,3,1]
     * 输出： 12
     * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
     *
     * 示例 3：
     * 输入： [2,1,4,5,3,1,1,3]
     * 输出： 12
     * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
     */
    public int massage(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        if(nums.length==1) {
            return nums[0];
        }
        if(nums.length==2) {
            return Math.max(nums[0], nums[1]);
        }
        int pre2 = nums[0];
        int pre1 = Math.max(nums[0], nums[1]);
        for(int i=2;i<nums.length;i++) {
            int tmp = pre1;
            pre1 = Math.max(pre1, pre2+nums[i]);
            pre2 = tmp;
        }
        return pre1;
    }

    /**
     * 面试题 17.17. 多次搜索
     * 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。
     * 输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
     *
     * 示例：
     * 输入：
     * big = "mississippi"
     * smalls = ["is","ppi","hi","sis","i","ssippi"]
     * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
     *
     * 提示：
     * 0 <= len(big) <= 1000
     * 0 <= len(smalls[i]) <= 1000
     * smalls的总字符数不会超过 100000。
     * 你可以认为smalls中没有重复字符串。
     * 所有出现的字符均为英文小写字母。
     */
    public int[][] multiSearch(String big, String[] smalls) {
        int bigLen = big.length();
        int smallsLen = smalls.length;
        int[][] result = new int[smallsLen][];
        for(int i=0;i<smallsLen;i++) {
            List<Integer> list = new ArrayList<>();
            String curr = smalls[i];
            if(curr!=null&&curr.length()!=0) {
                for(int j=0;j<=bigLen-curr.length();j++) {
                    if(curr.equals(big.substring(j, j+curr.length()))) {
                        list.add(j);
                    }
                }
            }
            int[] array = new int[list.size()];
            for(int j=0;j<list.size();j++) {
                array[j] = list.get(j);
            }
            result[i] = array;
        }
        return result;
    }

    /**
     * 面试题 17.18. 最短超串
     * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
     * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
     *
     * 示例 1:
     * 输入:
     * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
     * small = [1,5,9]
     * 输出: [7,10]
     *
     * 示例 2:
     * 输入:
     * big = [1,2,3]
     * small = [4]
     * 输出: []
     *
     * 提示：
     * big.length <= 100000
     * 1 <= small.length <= 100000
     */
    public int[] shortestSeq(int[] big, int[] small) {
        if(small==null||small.length==0||big==null||big.length<small.length) {
            return new int[0];
        }
        int len = big.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : small) {
            map.put(num, len);
        }
        int[] result = new int[]{len, len};
        for(int i=0;i<len;i++) {
            if(map.containsKey(big[i])) {
                map.put(big[i], i);
                Map.Entry<Integer, Integer> min = null;
                boolean flag = true;
                for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if(entry.getValue()==len) {
                        flag = false;
                        break;
                    }
                    if(min==null||min.getValue()>entry.getValue()) {
                        min = entry;
                    }
                }
                if(flag&&(result[0]==len||result[1]-result[0]>i-min.getValue())) {
                    result[0] = min.getValue();
                    result[1] = i;
                    map.put(min.getKey(), len);
                }
            }
        }
        return result[0]==len?new int[0]:result;
    }

    /**
     * 面试题 17.19. 消失的两个数字
     * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
     * 以任意顺序返回这两个数字均可。
     *
     * 示例 1:
     * 输入: [1]
     * 输出: [2,3]
     *
     * 示例 2:
     * 输入: [2,3]
     * 输出: [1,4]
     *
     * 提示：
     * nums.length <= 30000
     */
    public int[] missingTwo(int[] nums) {
        if(nums==null) {
            return null;
        }
        int n = nums.length;
        int x = 0;
        for(int i=0;i<n;i++) {
            x ^= i+1;
            x ^= nums[i];
        }
        x ^= n+1;
        x ^= n+2;
        int diff = x&(-x);
        int result1 = 0;
        int result2 = 0;
        for(int i=0;i<n;i++) {
            if((nums[i]&diff)==0) {
                result1 ^= nums[i];
            }else {
                result2 ^= nums[i];
            }
        }
        for(int i=1;i<=n+2;i++) {
            if((i&diff)==0) {
                result1 ^= i;
            }else {
                result2 ^= i;
            }
        }
        return new int[]{result1, result2};
        /*int i = 0;
        while(i<n) {
            if(nums[i]==i+1||nums[i]==n+1||nums[i]==n+2) {
                i++;
            }else {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
            }
        }
        int result1 = n+1;
        int result2 = n+2;
        for(int j=n-1;j>=0;j--) {
            if(nums[j]!=j+1) {
                if(nums[j]==n+1) {
                    result1 = j+1;
                }else {
                    result2 = j+1;
                }
            }
        }
        return new int[]{result1, result2};*/
    }

    /**
     * 面试题 17.22. 单词转换
     * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
     * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
     *
     * 示例 1:
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出:
     * ["hit","hot","dot","lot","log","cog"]
     *
     * 示例 2:
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * 输出: []
     * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
     */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(beginWord==null||endWord==null||wordList==null||wordList.size()==0) {
            return new ArrayList<>();
        }
        Queue<List<String>> queue = new LinkedList<>();
        List<String> firstList = new ArrayList<>();
        firstList.add(beginWord);
        queue.offer(firstList);
        int size = wordList.size();
        for(int i=0;i<size;i++) {
            if(beginWord.equals(wordList.get(i))) {
                wordList.set(i, null);
            }
        }
        while(!queue.isEmpty()) {
            List<String> currList = queue.poll();
            String currStr = currList.get(currList.size()-1);
            if(currStr.equals(endWord)) {
                return currList;
            }
            for(int i=0;i<wordList.size();i++) {
                if(wordList.get(i)!=null&&canChange(currStr, wordList.get(i))) {
                    List<String> newList = new ArrayList<>(currList);
                    newList.add(wordList.get(i));
                    wordList.set(i, null);
                    queue.offer(newList);
                }
            }
        }
        return new ArrayList<>();
    }

    public boolean canChange(String word, String target) {
        if(word==null||target==null||word.length()==0||target.length()==0||word.length()!=target.length()) {
            return false;
        }
        boolean flag = false;
        for(int i=0;i<word.length();i++) {
            if(word.charAt(i)!=target.charAt(i)) {
                if(flag) {
                    return false;
                }else {
                    flag = true;
                }
            }
        }
        return true;
    }

    /**
     * 面试题 17.23. 最大黑方阵
     * 给定一个方阵，其中每个单元(像素)非黑即白。设计一个算法，找出 4 条边皆为黑色像素的最大子方阵。
     * 返回一个数组 [r, c, size] ，其中 r, c 分别代表子方阵左上角的行号和列号，size 是子方阵的边长。
     * 若有多个满足条件的子方阵，返回 r 最小的，若 r 相同，返回 c 最小的子方阵。若无满足条件的子方阵，返回空数组。
     *
     * 示例 1:
     * 输入:
     * [
     *    [1,0,1],
     *    [0,0,1],
     *    [0,0,1]
     * ]
     * 输出: [1,0,2]
     * 解释: 输入中 0 代表黑色，1 代表白色，标粗的元素即为满足条件的最大子方阵
     *
     * 示例 2:
     * 输入:
     * [
     *    [0,1,1],
     *    [1,0,1],
     *    [1,1,0]
     * ]
     * 输出: [0,0,1]
     *
     * 提示：
     * matrix.length == matrix[0].length <= 200
     */
    public int[] findSquare(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return new int[0];
        }
        int[] result = new int[3];
        int m = matrix.length;
        int n = matrix[0].length;
        int[][][] dp = new int[m][n][2];
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                if(matrix[i][j]==0) {
                    dp[i][j][0] = i<m-1?dp[i+1][j][0]+1:1;
                    dp[i][j][1] = j<n-1?dp[i][j+1][1]+1:1;
                }
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                for(int k=Math.min(dp[i][j][0], dp[i][j][1]);k>result[2];k--) {
                    if(dp[i+k-1][j][1]>=k&&dp[i][j+k-1][0]>=k) {
                        result[0] = i;
                        result[1] = j;
                        result[2] = k;
                        break;
                    }
                }
            }
        }
        return result[2]==0?new int[0]:result;
    }

    /**
     * 面试题03. 数组中重复的数字
     * 找出数组中重复的数字。
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     *
     * 示例 1：
     * 输入：[2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     *
     * 限制：
     * 2 <= n <= 100000
     */
    public int findRepeatNumber(int[] nums) {
        int index = 0;
        while(true) {
            if(index==nums[index]) {
                index++;
            }else if(nums[index]==nums[nums[index]]) {
                return nums[index];
            }else {
                int tmp = nums[nums[index]];
                nums[nums[index]]  = nums[index];
                index = tmp;
            }
        }
    }

    /**
     * 面试题04. 二维数组中的查找
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     *
     * 限制：
     * 0 <= n <= 1000
     * 0 <= m <= 1000
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = m-1;
        int j = 0;
        while(i>=0&&j<n) {
            if(matrix[i][j]==target) {
                return true;
            }else if(matrix[i][j]<target) {
                j++;
            }else {
                i--;
            }
        }
        return false;
    }

    /**
     * 面试题05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     *
     * 示例 1：
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *
     * 限制：
     * 0 <= s 的长度 <= 10000
     */
    public String replaceSpace(String s) {
        if(s==null||s.length()==0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c!=' ') {
                sb.append(c);
            }else {
                sb.append("%20");
            }
        }
        return sb.toString();
        //return s.replaceAll(" ", "%20");
    }

    /**
     * 面试题06. 从尾到头打印链表
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     * 示例 1：
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *
     * 限制：
     * 0 <= 链表长度 <= 10000
     */
    public int[] reversePrint(ListNode head) {
        if(head==null) {
            return new int[0];
        }
        ListNode curr = head;
        int len = 0;
        while(curr!=null) {
            len++;
            curr  = curr.next;
        }
        int[] result = new int[len];
        curr = head;
        for(int i=len-1;i>=0;i--) {
            result[i] = curr.val;
            curr = curr.next;
        }
        return result;
    }

    /**
     * 面试题07. 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * 例如，给出
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 限制：
     * 0 <= 节点个数 <= 5000
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0) {
            return null;
        }
        return buildTree(preorder, 0, inorder, 0, preorder.length);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int[] inorder, int inStart, int len) {
        if(len<=0) {
            return null;
        }
        int value = preorder[preStart];
        int leftLen = 0;
        for(;leftLen<len;leftLen++) {
            if(inorder[inStart+leftLen]==value) {
                break;
            }
        }
        TreeNode node = new TreeNode(value);
        node.left = buildTree(preorder, preStart+1, inorder, inStart, leftLen);
        node.right = buildTree(preorder, preStart+1+leftLen, inorder, inStart+leftLen+1, len-leftLen-1);
        return node;
    }

    /**
     * 面试题10- I. 斐波那契数列
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：1
     *
     * 示例 2：
     * 输入：n = 5
     * 输出：5
     *
     * 提示：
     * 0 <= n <= 100
     */
    public int fib(int n) {
        if(n<2) {
            return n;
        }
        int dp1 = 0;
        int dp2 = 1;
        for(int i=2;i<=n;i++) {
            int tmp = dp2;
            dp2 = (dp1+dp2)%mod;
            dp1 = tmp;
        }
        return dp2;
    }

    /**
     * 面试题10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     *
     * 示例 2：
     * 输入：n = 7
     * 输出：21
     *
     * 提示：
     * 0 <= n <= 100
     */
    public int numWays(int n) {
        if(n==0||n==1) {
            return 1;
        }
        int dp1 = 1;
        int dp2 = 1;
        for(int i=2;i<=n;i++) {
            int tmp = dp2;
            dp2 = (dp2+dp1)%mod;
            dp1 = tmp;
        }
        return dp2;
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
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
     * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
     * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
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
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
     * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(A);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
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

    public boolean isSame(TreeNode A, TreeNode B) {
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
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode result = new TreeNode(root.val);
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
    public boolean isSymmetric(TreeNode root) {
        if(root==null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
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
    public int[] levelOrderI(TreeNode root) {
        if(root==null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
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
    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size-->0) {
                TreeNode curr = queue.poll();
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
    public List<List<Integer>> levelOrderIII(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean asc = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            while(size-->0) {
                TreeNode curr = queue.poll();
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
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result_list.clear();
        if(root==null) {
            return result_list;
        }
        pathSum(new ArrayList<Integer>(), root, 0, sum);
        return result_list;
    }

    public void pathSum(List<Integer> curr, TreeNode node, int pre, int sum) {
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
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        TreeNode head = stack.peek();
        TreeNode pre = null;
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
    public String[] permutation0(String s) {
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
                tmp[index++] = nums[index1++];
            }else if(index1==mid+1) {
                tmp[index++] = nums[index2++];
            }else if(nums[index1]>nums[index2]) {
                result_int += end-index2+1;
                tmp[index++] = nums[index1++];
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
    public int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
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
        int h = ret&(-ret);
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

/**
 * 面试题09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 */
class CQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(value);
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public int deleteHead() {
        return stack1.isEmpty()?-1:stack1.pop();
    }
}
/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

class LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return this.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        this.put(key, value);
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldestEntry) {
        return this.size()>capacity;
    }

}

/**
 * 面试题 16.02. 单词频率
 * 设计一个方法，找出任意指定单词在一本书中的出现频率。
 * 你的实现应该支持如下操作：
 * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
 * get(word)查询指定单词在数中出现的频率
 *
 * 示例：
 * WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
 * wordsFrequency.get("you"); //返回0，"you"没有出现过
 * wordsFrequency.get("have"); //返回2，"have"出现2次
 * wordsFrequency.get("an"); //返回1
 * wordsFrequency.get("apple"); //返回1
 * wordsFrequency.get("pen"); //返回1
 *
 * 提示：
 * book[i]中只包含小写字母
 * 1 <= book.length <= 100000
 * 1 <= book[i].length <= 10
 * get函数的调用次数不会超过100000
 */
class WordsFrequency {

    Map<String, Integer> mem;

    public WordsFrequency(String[] book) {
        mem = new HashMap<>();
        for(String word : book) {
            mem.put(word, mem.getOrDefault(word, 0)+1);
        }
    }

    public int get(String word) {
        return mem.getOrDefault(word, 0);
    }

}

/**
 * 面试题 10.10. 数字流的秩
 * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。
 * 请实现数据结构和算法来支持这些操作，也就是说：
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 * 注意：本题相对原题稍作改动
 *
 * 示例:
 * 输入:
 * ["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"]
 * [[], [1], [0], [0]]
 * 输出:
 * [null,0,null,1]
 *
 * 提示：
 * x <= 50000
 * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 */
class StreamRank {

    Map<Integer, Integer> mem;

    public StreamRank() {
        mem = new HashMap<>();
    }

    public void track(int x) {
        mem.put(x, mem.getOrDefault(x, 0)+1);
    }

    public int getRankOfNumber(int x) {
        int result = 0;
        for(Map.Entry<Integer, Integer> entry : mem.entrySet()) {
            if(entry.getKey()<=x) {
                result += entry.getValue();
            }
        }
        return result;
    }

}

/**
 * 面试题 03.06. 动物收容所
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在收养该收容所的动物时，
 * 收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
 * 换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，
 * 比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 *
 * 示例1:
 *  输入：
 * ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [], [], []]
 *  输出：
 * [null,null,null,[0,0],[-1,-1],[1,0]]
 *
 * 示例2:
 *  输入：
 * ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
 *  输出：
 * [null,null,null,null,[2,1],[0,0],[1,0]]
 *
 * 说明:
 * 收纳所的最大容量为20000
 */
class AnimalShelf {

    Queue<Integer> queueCat;
    Queue<Integer> queueDog;

    public AnimalShelf() {
        queueCat = new LinkedList<>();
        queueDog = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        if(animal[1]==0) {
            queueCat.offer(animal[0]);
        }else {
            queueDog.offer(animal[1]);
        }
    }

    public int[] dequeueAny() {
        if(queueCat.isEmpty()&&queueDog.isEmpty()) {
            return new int[]{-1, -1};
        }else if(queueCat.isEmpty()||(!queueDog.isEmpty()&&queueCat.peek()>queueDog.peek())) {
            return new int[]{queueDog.poll(), 1};
        }else {
            return new int[]{queueCat.poll(), 0};
        }
    }

    public int[] dequeueDog() {
        return queueDog.isEmpty()?new int[]{-1,-1}:new int[]{queueDog.poll(), 1};
    }

    public int[] dequeueCat() {
        return queueCat.isEmpty()?new int[]{-1,-1}:new int[]{queueCat.poll(), 0};
    }

}

/**
 * 面试题 03.05. 栈排序
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
 * 最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 *
 * 示例1:
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：[null,null,null,1,null,2]
 *
 * 示例2:
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：[null,null,null,null,null,true]
 *
 * 说明:
 * 栈中的元素数目在[0, 5000]范围内。
 */
class SortedStack {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public SortedStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int val) {
        while(!stack1.isEmpty()&&stack1.peek()<val) {
            stack2.push(stack1.pop());
        }
        stack1.push(val);
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public void pop() {
        if(!stack1.isEmpty()) {
            stack1.pop();
        }
    }

    public int peek() {
        if(!stack1.isEmpty()) {
            return stack1.peek();
        }
        return -1;
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }

}

/**
 * 面试题 03.04. 化栈为队
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 *
 * 示例：
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * 说明：
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 */
class MyQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2  =new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }
}

/**
 * 面试题 03.01. 三合一
 * 三合一。描述如何只用一个数组来实现三个栈。
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 *  输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *  输出：[null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 *
 * 示例2:
 *  输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *  输出：[null, null, null, null, 2, 1, -1, -1]
 */
class TripleInOne {

    int[] stack;
    int[] idxs;

    public TripleInOne(int stackSize) {
        stack = new int[stackSize*3];
        idxs = new int[]{0,1,2};
    }

    public void push(int stackNum, int value) {
        if(idxs[stackNum]<stack.length) {
            stack[idxs[stackNum]] = value;
            idxs[stackNum] += 3;
        }
    }

    public int pop(int stackNum) {
        if(idxs[stackNum]<3) {
            return -1;
        }else {
            stackNum -= 3;
            return stack[idxs[stackNum]];
        }
    }

    public int peek(int stackNum) {
        if(idxs[stackNum]<3) {
            return -1;
        }else {
            return stack[idxs[stackNum]-3];
        }
    }

    public boolean isEmpty(int stackNum) {
        return idxs[stackNum]<3;
    }

}

/**
 * 面试题 03.03. 堆盘子
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。
 * 因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
 * 请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
 * 此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，
 * 应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 *
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 *
 * 示例1:
 *  输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 *  输出：
 * [null, null, null, 2, 1, -1]
 *
 * 示例2:
 *  输入：
 * ["StackOfPlates", "push", "push", "push", "popAt", "popAt", "popAt"]
 * [[2], [1], [2], [3], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, 3]
 */
class StackOfPlates {

    int cap;
    List<Stack<Integer>> list;

    public StackOfPlates(int cap) {
        this.cap = cap;
        list = new LinkedList<>();
    }

    public void push(int val) {
        if(cap<=0) {
            return;
        }
        if(list.size()==0||list.get(list.size()-1).size()==cap) {
            list.add(new Stack());
        }
        list.get(list.size()-1).push(val);
    }

    public int pop() {
        if(cap<=0||list.size()==0) {
            return -1;
        }
        Stack<Integer> stack = list.get(list.size()-1);
        int result = stack.pop();
        if(stack.isEmpty()) {
            list.remove(list.size()-1);
        }
        return result;
    }

    public int popAt(int index) {
        if(cap<=0||list.size()==0||index>=list.size()) {
            return -1;
        }
        Stack<Integer> stack = list.get(index);
        int result = stack.pop();
        if(stack.isEmpty()) {
            list.remove(index);
        }
        return result;
    }

}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */