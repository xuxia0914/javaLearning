package cn.leetcode.xux.midium;

/**
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
 * Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.
 * Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.
 * Example 1:
 * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * Output: [1,0,0,0,0]
 * Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.
 * Note:
 * 1 <= arr1.length <= 1000
 * 1 <= arr2.length <= 1000
 * arr1 and arr2 have no leading zeros
 * arr1[i] is 0 or 1
 * arr2[i] is 0 or 1
 */
public class AddingTwoNegabinaryNumbers {

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        if(arr1==null||arr1.length==0||(arr1.length==1&&arr1[0]==0)) {
            return arr2;
        }
        if(arr2==null||arr2.length==0||(arr2.length==1&&arr2[0]==0)) {
            return arr1;
        }
        int len1 = arr1.length;
        int len2 = arr2.length;
        int index1 = len1-1;
        int index2 = len2-1;
        int carry = 0;
        int[] res = new int[Math.max(len1, len2)+2];
        int len3 = res.length;
        int index3 = len3 - 1;
        int tmp;
        while(index1>=0||index2>=0) {
            tmp = carry;
            if(index1>=0) {
                tmp += arr1[index1--];
            }
            if(index2>=0) {
                tmp += arr2[index2--];
            }
            if(tmp==-1) {
                res[index3--] = 1;
                carry = 1;
            }
            if(tmp==0||tmp==1) {
                res[index3--] = tmp;
                carry = 0;
            }
            if(tmp==2) {
                res[index3--] = 0;
                carry = -1;
            }
            if(tmp==3) {
                res[index3--] = 1;
                carry = -1;
            }
        }
        if(carry==-1) {
            res[index3--] = 1;
            res[index3] = 1;
        }else {
            res[index3] = carry;
        }
        int cnt = 0;
        for(int i:res) {
            if(i==0) {
                cnt++;
            }else {
                break;
            }
        }
        int[] newRes = new int[len3-cnt];
        for(int i=cnt;i<len3;i++) {
            newRes[i-cnt] = res[i];
        }
        return newRes.length==0?new int[]{0}:newRes;
    }

}
