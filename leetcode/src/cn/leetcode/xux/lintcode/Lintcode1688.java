package cn.leetcode.xux.lintcode;

import java.util.Stack;

/**
 * 1688. 军训
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 军训时有n个人从左到右站成一排，给出n个人的身高。命令向右看齐时，
 * 每个人能看见他右边比他矮的人，当遇到右边第一个身高大于等于他的人以后，
 * 则无法继续看到这个人右边的其他人（包括这个身高大于等于他的人也不能看到）。
 * 输出每个人能够看到的人数量总和
 *
 * 样例
 * 样例 1:
 *
 * 给出 `a = [10,3,7,4,12,2]`, 返回 `5`
 * 输入:
 * 10 3 7 4 12 2
 * 输出:
 * 5
 *
 * 解释：
 * 定义b[i]表示第i个人向右能够看到的人数，则： 3+0+1+0+1+0 = 5，故答案为5
 * 样例 2:
 *
 * 给出 `a = [1,1,1,1]`, 返回 `0`
 * 输入:
 * 1 1 1 1
 * 输出:
 * 0
 *
 * 解释：
 * 因为每个人只能看见自己右边比自己低的人
 * 注意事项
 * 1 \leq n \leq 100001≤n≤10000
 */
public class Lintcode1688 {

    /**
     * @param a: the height array a
     * @return: return the sum number of people who can be seen
     */
    public int getAns(int[] a) {
        // write your code here
        if(a==null||a.length<2) {
            return 0;
        }
        int n = a.length;
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for(int i=n-1;i>=0;i--) {
            while(!stack.isEmpty()&&a[stack.peek()]<a[i]) {
                stack.pop();
            }
            ans += (stack.isEmpty()?n:stack.peek())-i-1;
            stack.push(i);
        }
        return ans;
    }

}
