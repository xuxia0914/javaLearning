package cn.leetcode.xux.easy;

/**
 * We have two special characters. The first character can be represented by one bit 0.
 * The second character can be represented by two bits (10 or 11).
 * Now given a string represented by several bits. Return whether the last character must be a one-bit character or not.
 * The given string will always end with a zero.
 *
 * Example 1:
 * Input:
 * bits = [1, 0, 0]
 * Output: True
 * Explanation:
 * The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
 * Example 2:
 * Input:
 * bits = [1, 1, 1, 0]
 * Output: False
 * Explanation:
 * The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
 * Note:
 *
 * 1 <= len(bits) <= 1000.
 * bits[i] is always 0 or 1.
 */
public class OneBitAndTwoBitCharacters {

    public boolean isOneBitCharacter(int[] bits) {
        int index = 0;
        if(bits[bits.length-1]==1) {
            return false;
        }
        boolean res = true;
        while(index<bits.length) {
            if(bits[index]==0) {
                index++;
                res = true;
            }else {
                index += 2;
                res = false;
            }
        }
        return res;
    }

}
