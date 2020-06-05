package cn.leetcode.xux.general.easy;

public class PrimeNumberOfSetBitsInBinaryRepresentation {

    public int countPrimeSetBits(int L, int R) {
        int res = 0;
        for(int i=L;i<=R;i++) {
            if(isPrime(bits(i))) {
                res++;
            }
        }
        return res;
    }

    boolean isPrime(int n) {
        if(n<2) {
            return false;
        }
        for(int i=2;i<=Math.sqrt(n);i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }

    int bits(int n) {
        int res = 0;
        while(n!=0) {
            n &= n-1;
            res++;
        }
        return res;
    }

}
