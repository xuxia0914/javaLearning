package cn.xux.algorithm.leetcode.general.midium;

/**
 * 779. 第K个语法符号
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 *
 * 例子:
 * 输入: N = 1, K = 1
 * 输出: 0
 *
 * 输入: N = 2, K = 1
 * 输出: 0
 *
 * 输入: N = 2, K = 2
 * 输出: 1
 *
 * 输入: N = 4, K = 5
 * 输出: 1
 *
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 *
 * 注意：
 * N 的范围 [1, 30].
 * K 的范围 [1, 2^(N-1)].
 */
public class KThSymbolInGrammar {

    public int kthGrammar(int N, int K) {
        int curr = 0;
        int cnt = (int)Math.pow(2, N-1);
        while(cnt>1) {
            cnt /= 2;
            if(K-1>=cnt) {
                if(curr==0) {
                    curr = 1;
                }else {
                    curr = 0;
                }
                K = K-cnt;
            }else {
                if(curr==0) {
                    curr = 0;
                }else {
                    curr = 1;
                }
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        KThSymbolInGrammar ksg = new KThSymbolInGrammar();
        System.out.println(ksg.kthGrammar(1, 1));
        System.out.println(ksg.kthGrammar(2, 1));
        System.out.println(ksg.kthGrammar(2, 2));
        System.out.println(ksg.kthGrammar(4, 5));
    }

}
