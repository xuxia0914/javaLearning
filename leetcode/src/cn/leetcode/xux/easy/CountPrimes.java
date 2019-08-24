package cn.leetcode.xux.easy;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * Example:
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {

    public static int solution(int n) {
        if(n<2) {
            return 0;
        }
        int num = 0;
        boolean[] flags = new boolean[n];
        for(int i=2;i<n;i++) {
            if(!flags[i]) {
                num++;
                for(int j=2;i*j<n;j++) {
                    flags[i*j] = true;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(solution(10));
    }

}
