package cn.leetcode.xux.easy;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {

    public static int[] solution(int[] array) {
        if(array==null||array.length==0) {
            return new int[]{};
        }
        int[] tmp1 = new int[array.length];
        boolean carry = true;
        for(int i=array.length-1;i>=0;i--) {
            if(carry) {
                if(array[i]==9) {
                    tmp1[i] = 0;
                }else {
                    tmp1[i] = array[i] + 1;
                    carry = false;
                }
            }else {
                tmp1[i] = array[i];
            }
        }
        if(carry) {
            int[] tmp2 = new int[array.length+1];
            tmp2[0] = 1;
            return tmp2;
        }
        return tmp1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{9,9,9,9};
        for(int i : solution(array)) {
            System.out.print(i + " ");
        }
    }

}
