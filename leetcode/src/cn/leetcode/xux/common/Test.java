package cn.leetcode.xux.common;

import javax.management.openmbean.InvalidOpenTypeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    /**
     * 739. 每日温度
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     */
    public int[] dailyTemperatures(int[] T) {
        if(T==null) {
            return null;
        }
        if(T.length==0) {
            return new int[0];
        }
        int len = T.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();
        for(int i=1;i<len;i++) {
            while(!stack.isEmpty()&&T[stack.peek()]<T[i]) {
                int pre = stack.pop();
                result[pre] = i-pre;
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 647. 回文子串
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     *
     * 示例 1:
     * 输入: "abc"
     * 输出: 3
     * 解释: 三个回文子串: "a", "b", "c".
     * 示例 2:
     * 输入: "aaa"
     * 输出: 6
     * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     *
     * 注意:
     * 输入的字符串长度不会超过1000。
     */
    public int countSubstrings(String s) {
        if(s==null||s=="") {
            return 0;
        }
        int len = s.length();
        int result = 0;
        for(int i=0;i<len;i++) {
            for(int j=0;i-j>=0&&i+j<len;j++) {
                if(s.charAt(i-j)==s.charAt(i+j)) {
                    result++;
                }else {
                    break;
                }
            }
        }
        for(int i=0;i>=0&&i+1<len;i++) {
            for(int j=0;i-j>=0&&i+1+j<len;j++) {
                if(s.charAt(i-j)==s.charAt(i+1+j)) {
                    result++;
                }else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 621. 任务调度器
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的最短时间。
     * 示例 1：
     * 输入: tasks = ["A","A","A","B","B","B"], n = 2
     * 输出: 8
     * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     * 注：
     * 任务的总个数为 [1, 10000]。
     * n 的取值范围为 [0, 100]。
     */
    public int leastInterval(char[] tasks, int n) {
        if(tasks==null||tasks.length==0) {
            return 0;
        }
        int len = tasks.length;
        if(n==0) {
            return len;
        }
        int[] cnt = new int[26];
        for(char c : tasks) {
            cnt[c-'A']++;
        }
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);
        int result = 0;
        while(len>0) {
            int curr = -1;
            int max = 0;
            for(int i=0;i<26;i++) {
                if(cnt[i]>max&&(preIndexs[i]==-1||result-preIndexs[i]>n)) {
                    curr = i;
                }
            }
            if(curr!=-1) {
                cnt[curr]--;
                len--;
                preIndexs[curr] = result;
            }
            result++;
        }
        return result;
    }

    /**
     * 581. 最短无序连续子数组
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 你找到的子数组应是最短的，请输出它的长度。
     * 示例 1:
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     */
    public int findUnsortedSubarray(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int len = nums.length;
        int[] mins = new int[len];
        mins[len-1] = nums[len-1];
        for(int i=len-2;i>=0;i--) {
            mins[i] = Math.min(mins[i+1], nums[i]);
        }
        int start = len-1;
        for(int i=0;i<len-1;i++) {
            if(nums[i]>mins[i+1]) {
                start = i;
                break;
            }
        }
        if(start==len-1) {
            return 0;
        }
        int[] maxs = new int[len];
        maxs[0] = nums[0];
        for(int i=1;i<len;i++) {
            maxs[i] = Math.max(maxs[i-1], nums[i]);
        }
        int end = 0;
        for(int i=len-1;i>0;i--) {
            if(nums[i]<maxs[i-1]) {
                end = i;
                break;
            }
        }
        return end-start+1;
    }

    /**
     * 560. 和为K的子数组
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * 示例 1 :
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     * 说明 :
     * 数组的长度为 [1, 20,000]。
     * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     */
    public int subarraySum(int[] nums, int k) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> mem = new HashMap<>();
        mem.put(0, 1);
        for(int num : nums) {
            sum += num;
            if(mem.containsKey(sum-k)) {
                result += mem.get(sum-k);
            }
            mem.put(sum, mem.getOrDefault(sum-k, 0)+1);
        }
        return result;
    }

    /**
     * 494. 目标和
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     * 示例 1:
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * 一共有5种方法让最终目标和为3。
     * 注意:
     * 数组非空，且长度不会超过20。
     * 初始的数组的和不会超过1000。
     * 保证返回的最终结果能被32位整数存下。
     */
    Map<String, Integer> mem1 = new HashMap<>();
    public int findTargetSumWays(int[] nums, int S) {
        return helper(nums, 0, S);
    }

    public int helper(int[] nums, int start, int sum) {
        if(start>=nums.length) {
            return sum==0?1:0;
        }
        if(mem1.containsKey(""+start+"#"+sum)) {
            return mem1.get(""+start+"#"+sum);
        }
        int result = helper(nums, start+1, sum-nums[start])
                +helper(nums, start+1, sum+nums[start]);
        mem1.put(""+start+"#"+sum, result);
        return result;
    }

    /**
     * 448. 找到所有数组中消失的数字
     * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
     * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
     * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     * 示例:
     * 输入:[4,3,2,7,8,2,3,1]
     * 输出:[5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new LinkedList<>();
        if(nums==null||nums.length==0) {
            return result;
        }
        int len = nums.length;
        int[] mem = new int[len];
        for(int num : nums) {
            mem[num-1] = num;
        }
        for(int i=0;i<len;i++) {
            if(mem[i]!=i+1) {
                result.add(i+1);
            }
        }
        return result;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> result = new LinkedList<>();
        if(nums==null||nums.length==0) {
            return result;
        }
        int len = nums.length;
        int tmp;
        for(int i=0;i<len;i++) {
            if(nums[i]==0) {
                continue;
            }else {
                int j = i;
                while(nums[j-1]!=0) {
                    tmp = nums[j-1];
                    nums[j-1] = 0;
                    j = tmp;
                }
            }
        }
        for(int i=0;i<len;i++) {
            if(nums[i]!=0) {
                result.add(i+1);
            }
        }
        return result;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * 说明：
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     * 示例 1:
     * 输入:s: "cbaebabacd" p: "abc"
     * 输出:[0, 6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     * 示例 2:
     * 输入:s: "abab" p: "ab"
     * 输出:[0, 1, 2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if(s==null||p==null||p.length()==0||p.length()>s.length()) {
            return result;
        }
        int[] key = new int[26];
        for(char c : p.toCharArray()) {
            key[c-'a']++;
        }
        int[] curr = new int[26];
        int lenP = p.length();
        int lenS = s.length();
        int i = 0;
        for(;i<lenP;i++) {
            curr[s.charAt(i)-'a']++;
        }
        if(equals(key, curr)) {
            result.add(0);
        }
        for(;i<lenS;i++) {
            curr[s.charAt(i-lenP)-'a']--;
            curr[s.charAt(i)-'a']++;
            if(equals(key, curr)) {
                result.add(s.charAt(i-lenP)-'a'+1);
            }
        }
        return result;
    }

    public boolean equals(int[] a, int[] b) {
        if(a==null&&b==null) {
            return true;
        }
        if(a==null||b==null||a.length!=b.length) {
            return false;
        }
        for(int i=0;i<a.length;i++) {
            if(a[i]!=b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * 示例：
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     * 返回 3。和等于 8 的路径有:
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     */
    public int pathSum(BinaryTreeNode root, int sum) {
        if(root==null) {
            return 0;
        }
        int result = 0;
        result += helper1(root, sum);
        result += pathSum(root.left, sum);
        result += pathSum(root.right, sum);
        return result;
    }

    public int helper1(BinaryTreeNode root, int sum) {
        if(root==null) {
            return 0;
        }
        int res = 0;
        if(root.val==sum) {
            res++;
        }
        if(root.left!=null) {
            res += helper1(root.left, sum-root.val);
        }
        if(root.right!=null) {
            res += helper1(root.right, sum-root.val);
        }
        return res;
    }

    /**
     * 416. 分割等和子集
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 注意:
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * 示例 2:
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     */
    public boolean canPartition(int[] nums) {
        if(nums==null||nums.length<2) {
            return false;
        }
        int sum = 0;
        for(int i : nums) {
            sum += i;
        }
        if(sum%2==1) {
            return false;
        }
        int target = sum/2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int num : nums) {
            for(int i=target-num;i>=0;i--) {
                if(dp[i]) {
                    if(i+num<target) {
                        dp[i+num] = true;
                    }else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
     * 注意：
     * 总人数少于1100人。
     * 示例
     * 输入:[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * 输出:[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
        if(people==null||people.length<2) {
            return people;
        }
        int len = people.length;
        //sort(people, 0, len-1);
        Arrays.sort(people, (o1, o2) -> o1[0]==o2[0]?o1[1]-o2[1]:o2[0]-o1[0]);
        int[] tmp;
        for(int i=0;i<len;i++) {
            tmp = people[i];
            if(tmp[1]<i) {
                for(int j=i;j>tmp[1];j--) {
                    people[j] = people[j-1];
                }
                people[tmp[1]] = tmp;
            }
        }
        return people;
    }
    //按照先matrix[i][0]降序，后matrx[i][1]升序排列，快速
    public void sort(int[][] matrix, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start;
        int right = end;
        int[] key = matrix[start];
        int[] tmp;
        while(left<right) {
            while(left<right&&(matrix[right][0]<key[0]||(matrix[right][0]==key[0]&&matrix[right][1]>=key[1]))) {
                right--;
            }
            while(left<right&&(matrix[left][0]>key[0]||(matrix[left][0]==key[0]&&matrix[left][1]<=key[1]))) {
                left++;
            }
            if(left<right) {
                tmp = matrix[left];
                matrix[left] = matrix[right];
                matrix[right] = tmp;
            }
        }
        matrix[start] = matrix[right];
        matrix[right] = key;
        sort(matrix, start, right-1);
        sort(matrix, right+1, end);
    }

    /**
     * 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * 示例:
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     */
    public String decodeString(String s) {
        return null;
    }

    /**
     * 338. 比特位计数
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * 示例 1:
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     */
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        result[0] = 0;
        int curr = 1;
        for(int i=1;i<=num;i++) {
            if(i==curr*2) {
                result[i] = 1;
                curr *= 2;
            }else {
                result[i] = 1+result[i-curr];
            }
        }
        return result;
    }

    /**
     * 337. 打家劫舍 III
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
     * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 示例 1:
     * 输入: [3,2,3,null,3,null,1]
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * 示例 2:
     * 输入: [3,4,5,1,3,null,1]
     *      3
     *     / \
     *    4   5
     *   / \   \
     *  1   3   1
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
     * @param root
     * @return
     */
    Map<BinaryTreeNode, Integer> mem = new HashMap<>();
    public int rob(BinaryTreeNode root) {
        int result = 0;
        if(root==null) {
            return 0;
        }
        if(mem.containsKey(root)) {
            return mem.get(root);
        }
        int left = rob(root.left);
        int right = rob(root.right);

        result = Math.max(result, left+right);
        int leftLeft = 0;
        int leftRight = 0;
        if(root.left!=null) {
            leftLeft = rob(root.left.left);
            leftRight = rob(root.left.right);
        }

        int rightLeft = 0;
        int rightRight = 0;
        if(root.right!=null) {
            rightLeft = rob(root.right.left);
            rightRight = rob(root.right.right);
        }

        result = Math.max(result, root.val+leftLeft+leftRight+rightLeft+rightRight);
        mem.put(root, result);
        return result;
    }

    /**
     * 279. 完全平方数
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * 示例 1:
     * 输入: n = 12
     * 输出: 3
     * 解释: 12 = 4 + 4 + 4.
     * 示例 2:
     * 输入: n = 13
     * 输出: 2
     * 解释: 13 = 4 + 9.
     */
    public int numSquares(int n) {
        if(n<=0) {
            return -1;
        }
        int[] dp = new int[n+1];
        for(int i=1;i<=n;i++) {
            dp[i] = i;
            for(int j=1;i-j*j>=0;j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }

    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 示例 1:
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     * 输入: coins = [2], amount = 3
     * 输出: -1
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if(coins==null||coins.length==0) {
            return -1;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<=amount;i++) {
            for(int coin : coins) {
                if(i-coin>=0&&dp[i-coin]!=Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i-coin]+1, dp[i]);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    /**
     * 240. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
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
        int i = m-1;
        int j = 0;
        while(i>=0&&j<n) {
            if(matrix[i][j]==target) {
                return true;
            }else if(matrix[i][j]>target) {
                i--;
            }else {
                j++;
            }
        }
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        for(int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<numCourses;i++) {
            if(inDegrees[i]==0) {
                queue.offer(i);
            }
        }
        int course;
        while(!queue.isEmpty()) {
            course = queue.poll();
            numCourses--;
            for(int[] prerequisite : prerequisites) {
                if(prerequisite[1]==course&&--inDegrees[prerequisite[0]]==0) {
                    queue.offer(prerequisite[0]);
                }
            }
        }
        return numCourses==0;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        ListNode tmp;
        while(curr!=null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    public int rob(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int len = nums.length;
        if(len==1) {
            return nums[0];
        }
        if(len==2) {
            return Math.max(nums[0], nums[1]);
        }
        int max1 = nums[0];
        int max2 = Math.max(nums[0], nums[1]);
        int tmp;
        for(int i=2;i<len;i++) {
            tmp = max2;
            max2 = Math.max(max2, max1+nums[i]);
            max1 = tmp;
        }
        return max2;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
        int lenA = 0;
        int lenB = 0;
        ListNode currA = headA;
        while(currA!=null) {
            lenA++;
            currA = currA.next;
        }
        ListNode currB = headB;
        while(currB!=null) {
            lenB++;
            currB = currB.next;
        }
        currA = headA;
        currB = headB;
        while(lenA>lenB) {
            currA = currA.next;
            lenA--;
        }
        while(lenB>lenA) {
            currB = currB.next;
            lenB--;
        }
        while(currA!=null) {
            if(currA==currB) {
                return currA;
            }else {
                currA = currA.next;
                currB = currB.next;
            }
        }
        return null;
    }

    public int maxProduct(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int min = nums[0];
        int res = nums[0];
        int curr, tmp;
        for(int i=1;i<len;i++) {
            curr = nums[i];
            tmp = max;
            max = Math.max(curr, Math.max(max*curr, min*curr));
            min = Math.min(curr, Math.min(min*curr, tmp*curr));
            res = Math.max(res, max);
        }
        return res;
    }

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
        int[][] people = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        test.reconstructQueue(people);
        //System.out.println(test.canFinish(2, new int[0][0]));
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
