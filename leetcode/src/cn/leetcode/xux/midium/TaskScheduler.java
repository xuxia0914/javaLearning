package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *
 * 注：
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        if(tasks==null||tasks.length==0) {
            return 0;
        }
        if(n<1) {
            return tasks.length;
        }
        int len = tasks.length;
        int[] array = new int[26];
        for(char c : tasks) {   //统计每个字符出现的次数
            array[c-'A']++;
        }
        int maxNum = 0;
        for(int i : array) {    //字符出现的最多次数
            maxNum = Math.max(maxNum, i);
        }
        int maxFre = 0;
        for(int i : array) {    //出现maxNum次的字符的个数
            if(i==maxNum) {
                maxFre++;
            }
        }
        return Math.max(len, (n+1)*(maxNum-1)+maxFre);  //公式
    }

    public int leastInterval1(char[] tasks, int n) {
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
                    max = cnt[i];
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


    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();
        System.out.println(ts.leastInterval1(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));  //16
    }

}
