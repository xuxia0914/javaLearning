package cn.leetcode.xux.easy;

/**
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 */
public class StudentAttendanceRecordI {

    public boolean checkRecord(String s) {
        if(s==null||s.length()<2) {
            return true;
        }
        int numA = 0;
        int numL = 0;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='A') {
                numA++;
                numL=0;
            }else if(s.charAt(i)=='L') {
                numL++;
            }else {
                numL=0;
            }
            if(numA>1||numL>2) {
                return false;
            }
        }
        return true;
    }

}
