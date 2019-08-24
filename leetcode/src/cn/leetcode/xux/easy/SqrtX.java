package cn.leetcode.xux.easy;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 */
public class SqrtX {

    /**TLE*/
    public static int solution(int x) {
        if(x==0||x==1) {
            return x;
        }
        int start = 0, end = x;
        while(start<=end) {
            int mid = (start+end)/2;
            if(mid*mid==x) {
                return mid;
            }else if(mid*mid>x) {
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return end;
    }

    /**
     * Runtime: 17 ms, faster than 9.35% of Java online submissions for Sqrt(x).
     * Memory Usage: 33.5 MB, less than 9.71% of Java online submissions for Sqrt(x).
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x==0||x==1) {
            return x;
        }
        int i = 1;
        int res = 0;
        while(x>=0) {
            x -= i;
            i += 2;
            res++;
        }
        return res-1;
    }

    public int mySqrt2(int x) {
        int start = 0;
        int end = x;
        while (true) {
            if (start * start == x) {
                return start;
            }
            if (end * end == x) {
                return end;
            }
            if (end - start <= 1) {
                return start;
            }
            long mid = (start + end) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid > x) {
                end = (int) mid;
            } else {
                start = (int) mid;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(1000));
    }

}
