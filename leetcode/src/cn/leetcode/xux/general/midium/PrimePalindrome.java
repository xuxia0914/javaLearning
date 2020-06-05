package cn.leetcode.xux.general.midium;

/**
 * Find the smallest prime palindrome greater than or equal to N.
 * Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 * For example, 2,3,5,7,11 and 13 are primes.
 * Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 * For example, 12321 is a palindrome.
 * Example 1:
 * Input: 6
 * Output: 7
 * Example 2:
 * Input: 8
 * Output: 11
 * Example 3:
 * Input: 13
 * Output: 101
 * Note:
 * 1 <= N <= 10^8
 * The answer is guaranteed to exist and be less than 2 * 10^8.
 */
public class PrimePalindrome {

    public int primePalindrome(int N) {
        if(N>=8&&N<=11) {
            return 11;
        }
        int res;
        for(int i=2;i<100000;i++) {
            res = Integer.valueOf("" + i + new StringBuilder(""+i).reverse().substring(1));
            if(res>=N&&isPrime(res)) {
                return res;
            }
        }
        return -1;
    }

    public boolean isPrime(int n) {
        for(int i=(int)Math.sqrt(n);i>1;i--) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PrimePalindrome pp = new PrimePalindrome();
        System.out.println(pp.primePalindrome(6));
    }

}
