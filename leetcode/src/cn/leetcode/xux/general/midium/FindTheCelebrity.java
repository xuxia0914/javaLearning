package cn.leetcode.xux.general.midium;

/**
 * 277. Find the Celebrity 寻找名人
 * 如果 a 不认识任何人，不代表a是名人。
 * 如果 a 不被任何人认识，不代表a是名人
 * 只有当a不认识任何人，并且，a不被任何人认识，a才是名人
 *
 * 如果a 认识 b， a不可能是名人
 * 如果a不认识b，b不可能是名人
 *
 * 就是这几个最重要的逻辑，搞清楚就行了。
 */
public class FindTheCelebrity {

    /* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

    boolean knows(int a, int b) {
        return true;
    }

    /**
     * 设定候选人 res 为0，原理是先遍历一遍，对于遍历到的人i，
     * 若候选人 res 认识i，则将候选人 res 设为i，
     * 完成一遍遍历后，我们来检测候选人 res 是否真正是名人，
     * 我们如果判断不是名人，则返回 -1，如果并没有冲突，返回 res
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        int res = 0;
        for(int i=1;i<n;i++) {
            if(knows(res, i)) {
                res = i;
            }
        }
        for(int i=0;i<n;i++) {
            if(res!=i&&(!knows(i, res)||knows(res, i))) {
                return -1;
            }
        }
        return 1;
    }

    /**
     * 我们还可以进一步减少 API 的调用量，找候选者的方法跟上面相同，但是在验证的时候，分为两段，
     * 先验证候选者前面的所有人，若候选者认识任何人，或者任何人不认识候选者，直接返回 -1。
     * 再验证候选者后面的人，这时候只需要验证是否有人不认识候选者就可以了，
     * 因为我们在最开始找候选者的时候就已经保证了候选者不会认识后面的任何人
     * @param n
     * @return
     */
    public int findCelebrity1(int n) {
        int res = 0;
        for(int i=1;i<n;i++) {
            if(knows(res, i)) {
                res = i;
            }
        }
        for(int i=0;i<res;i++) {
            if(!knows(i, res)||knows(res, i)) {
                return -1;
            }
        }
        for(int i=res+1;i<n;i++) {
            if(!knows(i, res)) {
                return -1;
            }
        }
        return 1;
    }

}
