package cn.leetcode.xux.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    /**
     * 5067. 统计只含单一字母的子串
     * 给你一个字符串 S，返回只含 单一字母 的子串个数。
     *
     * 示例 1：
     * 输入： "aaaba"
     * 输出： 8
     * 解释：
     * 只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
     * "aaa" 出现 1 次。
     * "aa" 出现 2 次。
     * "a" 出现 4 次。
     * "b" 出现 1 次。
     * 所以答案是 1 + 2 + 4 + 1 = 8。
     *
     * 示例 2:
     * 输入： "aaaaaaaaaa"
     * 输出： 55
     *
     * 提示：
     * 1 <= S.length <= 1000
     * S[i] 仅由小写英文字母组成。
     * */
    public int countLetters(String S) {
        char pre = S.charAt(0);
        int cnt = 1;
        int res = 0;
        for(int i=1;i<S.length();i++) {
            char curr = S.charAt(i);
            if(curr==pre) {
                cnt++;
            }else {
                res += cnt*(cnt+1)/2;
                cnt = 1;
                pre = curr;
            }
        }
        res += cnt*(cnt+1)/2;
        return res;
    }

    /**
     * 5068. 前后拼接
     * 给你一个「短语」列表 phrases，请你帮忙按规则生成拼接后的「新短语」列表。
     * 「短语」（phrase）是仅由小写英文字母和空格组成的字符串。
     * 「短语」的开头和结尾都不会出现空格，「短语」中的空格不会连续出现。
     * 「前后拼接」（Before and After puzzles）是合并两个「短语」形成「新短语」的方法。
     * 我们规定拼接时，第一个短语的最后一个单词 和 第二个短语的第一个单词 必须相同。
     * 返回每两个「短语」 phrases[i] 和 phrases[j]（i != j）进行「前后拼接」得到的「新短语」。
     * 注意，两个「短语」拼接时的顺序也很重要，我们需要同时考虑这两个「短语」。
     * 另外，同一个「短语」可以多次参与拼接，但「新短语」不能再参与拼接。
     * 请你按字典序排列并返回「新短语」列表，列表中的字符串应该是 不重复的 。
     *
     * 示例 1：
     * 输入：phrases = ["writing code","code rocks"]
     * 输出：["writing code rocks"]
     *
     * 示例 2：
     * 输入：phrases = ["mission statement",
     *                 "a quick bite to eat",
     *                 "a chip off the old block",
     *                 "chocolate bar",
     *                 "mission impossible",
     *                 "a man on a mission",
     *                 "block party",
     *                 "eat my words",
     *                 "bar of soap"]
     * 输出：["a chip off the old block party",
     *       "a man on a mission impossible",
     *       "a man on a mission statement",
     *       "a quick bite to eat my words",
     *       "chocolate bar of soap"]
     *
     * 示例 3：
     * 输入：phrases = ["a","b","a"]
     * 输出：["a"]
     *
     * 提示：
     * 1 <= phrases.length <= 100
     * 1 <= phrases[i].length <= 100
     */
//    public List<String> beforeAndAfterPuzzles(String[] phrases) {
    public String[] beforeAndAfterPuzzles(String[] phrases) {
        Map<String, List<Integer>> startMap = new HashMap<>();
        Map<String, List<Integer>> endMap = new HashMap<>();
        for(int i=0;i<phrases.length;i++) {
            String[] tmp = phrases[i].split(" ");
            if(!startMap.containsKey(tmp[0])) {
                startMap.put(tmp[0], new ArrayList<>());
            }
            startMap.get(tmp[0]).add(i);
            if(!endMap.containsKey(tmp[tmp.length-1])) {
                endMap.put(tmp[tmp.length-1], new ArrayList<>());
            }
            endMap.get(tmp[tmp.length-1]).add(i);
        }
        List<String> res = new ArrayList<>();
        for(String end : endMap.keySet()) {
            if(startMap.containsKey(end)) {
                List<Integer> pres = endMap.get(end);
                List<Integer> posts = startMap.get(end);
                for(int i=0;i<pres.size();i++) {
                    for(int j=0;j<posts.size();j++) {
                        if(pres.get(i)!=posts.get(j)) {
                            res.add(phrases[pres.get(i)].substring(0, phrases[pres.get(i)].lastIndexOf(" "))
                                    + " " + end
                                    +phrases[posts.get(j)].substring(phrases[posts.get(j)].indexOf(" ")));
                        }
                    }
                }
            }
        }
        return res.toArray(new String[res.size()]);
//        return res;
    }

    /**
     * 5070. 与目标颜色间的最短距离
     * 给你一个数组 colors，里面有  1、2、 3 三种颜色。
     * 我们需要在 colors 上进行一些查询操作 queries，其中每个待查项都由两个整数 i 和 c 组成。
     * 现在请你帮忙设计一个算法，查找从索引 i 到具有目标颜色 c 的元素之间的最短距离。
     * 如果不存在解决方案，请返回 -1。
     *
     * 示例 1：
     * 输入：colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
     * 输出：[3,0,3]
     * 解释：
     * 距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
     * 距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
     * 距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
     *
     * 示例 2：
     * 输入：colors = [1,2], queries = [[0,3]]
     * 输出：[-1]
     * 解释：colors 中没有颜色 3。
     *
     * 提示：
     * 1 <= colors.length <= 5*10^4
     * 1 <= colors[i] <= 3
     * 1 <= queries.length <= 5*10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] < colors.length
     * 1 <= queries[i][1] <= 3
     */
    public int[] shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        int[][] dp = new int[n][3];
        for(int i=0;i<n;i++) {
            Arrays.fill(dp[i], 50000);
            dp[i][colors[i]-1] = 0;
            if(i>0) {
                dp[i][colors[i]%3] = Math.min(dp[i][colors[i]%3], dp[i-1][colors[i]%3]+1);
                dp[i][(colors[i]+1)%3] = Math.min(dp[i][(colors[i]+1)%3], dp[i-1][(colors[i]+1)%3]+1);
            }
        }
        for(int i=n-2;i>=0;i--) {
            dp[i][colors[i]%3] = Math.min(dp[i][colors[i]%3], dp[i+1][colors[i]%3]+1);
            dp[i][(colors[i]+1)%3] = Math.min(dp[i][(colors[i]+1)%3], dp[i+1][(colors[i]+1)%3]+1);
        }
        int[] res = new int[queries.length];
        for(int i=0;i<res.length;i++) {
            int[] curr = queries[i];
            res[i] = dp[curr[0]][curr[1]-1]>=50000?-1:dp[curr[0]][curr[1]-1];
        }
        return res;
    }

    /**
     * 5082. 矩阵中 1 的最大数量
     * 现在有一个尺寸为 width * height 的矩阵 M，矩阵中的每个单元格的值不是 0 就是 1。
     * 而且矩阵 M 中每个大小为 sideLength * sideLength 的 正方形 子阵中，1 的数量不得超过 maxOnes。
     * 请你设计一个算法，计算矩阵中最多可以有多少个 1。
     *
     * 示例 1：
     * 输入：width = 3, height = 3, sideLength = 2, maxOnes = 1
     * 输出：4
     * 解释：
     * 题目要求：在一个 3*3 的矩阵中，每一个 2*2 的子阵中的 1 的数目不超过 1 个。
     * 最好的解决方案中，矩阵 M 里最多可以有 4 个 1，如下所示：
     * [1,0,1]
     * [0,0,0]
     * [1,0,1]
     *
     * 示例 2：
     * 输入：width = 3, height = 3, sideLength = 2, maxOnes = 2
     * 输出：6
     * 解释：
     * [1,0,1]
     * [1,0,1]
     * [1,0,1]
     *
     * 提示：
     * 1 <= width, height <= 100
     * 1 <= sideLength <= width, height
     * 0 <= maxOnes <= sideLength * sideLength
     */
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        if(maxOnes==0) {
            return 0;
        }
        if(maxOnes==sideLength * sideLength) {
            return width*height;
        }
        int tmp  = (int)Math.sqrt(maxOnes);
        int w, h;
        if(tmp*tmp == maxOnes) {
            w = tmp;
            h = tmp;
        }else {
            w = tmp+1;
            h = maxOnes/w;
            if(maxOnes/w!=0) {
                h++;
            }
        }
        int res = 0;
        int i, j;
        for(i=0;i<width-w;i+=w) {
            for(j=0;j<height-h;j+=h) {
                res += maxOnes;
            }
        }
        //TODO
        return res;
    }

    //基姆拉尔森计算公式根据日期判断星期几
    public String dayOfTheWeek(int day, int month, int year) {

        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        int iWeek = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return weekDays[iWeek];
    }
    public int maximumSum(int[] arr) {
        if(arr.length==1) {
            return arr[0];
        }
        int n = arr.length;
        int[][] mins = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                if(j==i) {
                    mins[i][j] = arr[j];
                }else {
                    mins[i][j] = Math.min(mins[i][j-1], arr[j]);
                }
            }
        }
        int[] sums = new int[n];
        sums[0] = arr[0];
        for(int i=1;i<n;i++) {
            sums[i] = sums[i-1]+arr[i];
        }
        int res = Integer.MIN_VALUE;
        for(int i=1;i<n;i++) {
            for(int j=0;j<i;j++) {
                res = Math.max(res, sums[i]-sums[j]+arr[j]-mins[j][i]);
            }
        }
        return res;
    }

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int res = 0 ;
        for(int i=0;i<arr1.length-1;i++) {
            if(arr1[i]>=arr1[i+1]) {
                boolean flag = false;
                if(i==0) {
                    for(int j=0;j<arr2.length;j++) {
                        if(arr2[j]<arr1[i+1]) {
                            int tmp = arr1[i];
                            arr1[i] = arr2[j];
                            arr2[j] = tmp;
                            res++;
                            flag = true;
                        }
                    }
                }else {
                    for(int j=0;j<arr2.length;j++) {
                        if(arr2[j]>arr1[i-1]&&arr2[j]<arr1[i+1]) {
                            int tmp = arr1[i];
                            arr1[i] = arr2[j];
                            arr2[j] = tmp;
                            res++;
                            flag = true;
                        }
                    }
                }
                if(!flag) {
                    return -1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Test test = new Test();
    //     System.out.println(test.beforeAndAfterPuzzles(new String[]{"writing code","code rocks"}));
    //    System.out.println(test.shortestDistanceColor(new int[]{1,1,2,1,3,2,2,3,3}, new int[][]{{1,3},{2,2}, {6,1}}));  //3 0 3
//        System.out.println(test.maximumSum(new int[]{1,-2,0,3}));
//        System.out.println(test.maximumSum(new int[]{1,-2,-2,3}));
        System.out.println(test.makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{1,3,2,4}));

    }

}
