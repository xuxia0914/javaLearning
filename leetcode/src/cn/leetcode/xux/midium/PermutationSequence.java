package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note:
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class PermutationSequence {

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++) {
            list.add(i);
        }
        int cnt = 1;
        for(int i=1;i<n;i++) {
            cnt *= i;
        }
        int index;
        int j = n-1;
        while(k>1) {
            index = (k-1)/cnt;
            k -= index*cnt;
            cnt /= j--;
            sb.append(list.remove(index));
        }
        for(int i : list) {
            sb.append(i);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationSequence ps = new PermutationSequence();
        System.out.println(ps.getPermutation(3, 3));    //213
    }

}
