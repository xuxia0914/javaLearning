package cn.leetcode.xux.easy;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * Example:
 * Given n = 5, and version = 4 is the first bad version.
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

    int version;    //the first bad version

    public FirstBadVersion(int version) {
        this.version = version;
    }

    public boolean isBadVersion(int version) {
        return version>=this.version;
    }

    public int firstBadVersion(int n) {
        if(n<1) {
            return 0;
        }
        long left = 1;
        long right = n;
        long mid;
        while(left<right) {
            mid = (left+right)/2;
            if(isBadVersion((int)mid)) {
                right = mid;
            }else {
                left = mid+1;
            }
        }
        if((int)left==n&&!isBadVersion((int)left)) {
            return 0;
        }else {
            return (int)left;
        }
    }

    public static void main(String[] args) {
        FirstBadVersion f = new FirstBadVersion(1702766719);
        System.out.println(f.firstBadVersion(2126753390));
    }

}
