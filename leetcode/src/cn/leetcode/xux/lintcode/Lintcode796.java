package cn.leetcode.xux.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 796. 开锁
 * 中文English
 * 你面前有一个有四个圆形轮子的锁。每个轮子有10个槽:‘0’,‘1’,‘2’,‘3’,‘4’,‘5’,‘6’,‘7’,‘8’,‘9’。轮子可以自由旋转并环绕:例如，我们可以把‘9’变成‘0’，或者‘0’变成‘9’。每个动作包括转动一个轮子一个槽。
 * 锁最初是‘0000’开始的，这是一个表示四个轮子状态的字符串。
 * 你被给了一个死锁的列表，意思是如果锁显示了这些代码中的任何一个，锁的轮子将停止转动，你将无法打开它。
 * 给定一个表示将解锁锁的轮子的值的目标，返回打开锁所需要的最小总次数，如果不可能，则返回-1。
 *
 * 样例
 * 样例 1:
 *
 * 给出死锁列表=[“0201”、“0101”、“0102”、“1212”、“2002”),目标= " 0202 "
 * 返回 6
 *
 * 解释:
 * 一系列有效的动作将是“0000”->“1000”->“1100”->“1200”->“1201”->“1202”->“0202”。
 * 请注意，像“0000”->“0001”->“0002”->“0102”->“0202”的序列将是无效的，
 * 因为锁的轮子在显示器变成了“0102”后卡住了。
 * 样例 2:
 *
 * 给出死锁列表 = ["8888"]， 目标 = "0009"
 * 返回 1
 *
 * 解释:
 * 我们可以从“0000”->“0009”转到最后一个转轮。
 * 注意事项
 * 1.死锁的列表长度将在[1,500]范围内。
 * 2.目标不在死锁列表中。
 * 3.每一个字符串在死锁列表和目标字段将是一串4位数字有10000种可能性从'0000'到'9999'。
 */
public class Lintcode796 {

    public static void main(String[] args) {
        //["0201","0101","0102","1212","2002"]
        //"0202"
        System.out.println(new Lintcode796().openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
    }

    /**
     * @param deadends: the list of deadends
     * @param target: the value of the wheels that will unlock the lock
     * @return: the minimum total number of turns
     */
    public int openLock(String[] deadends, String target) {
        // Write your code here
        Set<Integer> dl = new HashSet<>();
        for(String deadend : deadends) {
            dl.add(Integer.parseInt(deadend));
        }
        int tar = Integer.parseInt(target);
        int start = 0;
        if(dl.contains(tar)||dl.contains(start)) {
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        dl.add(start);
        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int curr = queue.poll();
                int tmp = curr;
                for(int i=0;i<=3;i++) {
                    int tens = (int)Math.pow(10, i);
                    int digit = tmp%10;
                    int dig1 = (digit+1)%10;
                    int nei1 = curr+(dig1-digit)*tens;
                    if(nei1==tar) {
                        return level;
                    }else if(dl.add(nei1)) {
                        queue.offer(nei1);
                    }
                    int dig2 = (digit+9)%10;
                    int nei2 = curr+(dig2-digit)*tens;
                    if(nei2==tar) {
                        return level;
                    }else if(dl.add(nei2)) {
                        queue.offer(nei2);
                    }
                    tmp /= 10;
                }
            }
            level++;
        }
        return -1;
    }

}
