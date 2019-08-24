package cn.leetcode.xux.midium;

/**
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 * Example:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
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
        return Math.max(len, (n+1)*maxNum-n+maxFre-1);  //公式
    }

}
