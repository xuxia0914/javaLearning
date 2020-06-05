package cn.leetcode.xux.general.midium;

import java.util.LinkedList;
import java.util.List;

/**
 * 386. 字典序排数
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 *
 * 例如，
 * 给定 n =13，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 */
public class LexicographicalNumbers {

    List<Integer> res = new LinkedList<>();

    public List<Integer> lexicalOrder(int n) {
        res.clear();
        if(n<1) {
            return res;
        }
        for(int i=1;i<10;i++) {
            helper(n, i);
        }
        return res;
    }

    public void helper(int n, int curr) {
        if(curr<=n) {
            res.add(curr);
        }else {
            return;
        }
        for(int i=0;i<10;i++) {
            helper(n, curr*10+i);
        }
    }

    public static void main(String[] args) {
        LexicographicalNumbers ln = new LexicographicalNumbers();
        System.out.println(ln.lexicalOrder(13));
    }

}
