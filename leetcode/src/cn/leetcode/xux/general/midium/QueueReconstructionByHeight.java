package cn.leetcode.xux.general.midium;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 * Note:
 * The number of people is less than 1,100.
 * Example
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] people) {
        if(people==null||people.length<2) {
            return people;
        }
        sort(people, 0, people.length-1);
        for(int i=0;i<people.length;i++) {
            if(people[i][1]<i) {
                int[] tmp = people[i];
                for(int j=i;j>tmp[1];j--) {
                    people[j] = people[j-1];
                }
                people[tmp[1]] = tmp;
            }
        }
        return people;
    }

    //按照pair[0]降序， pair[1]升序排列
    public void sort(int[][] people, int start, int end) {
        if(start>=end) {
            return;
        }
        int left = start, right = end;
        int[] key = people[start];
        int[] tmp;
        while(left<right) {
            while(left<right&&(people[right][0]<key[0]||(people[right][0]==key[0]&&people[right][1]>=key[1]))) {
                right--;
            }
            while(left<right&&(people[left][0]>key[0]||(people[left][0]==key[0]&&people[left][1]<=key[1]))) {
                left++;
            }
            if(left<right) {
                tmp = people[left];
                people[left] = people[right];
                people[right] = tmp;
            }
        }
        people[start] = people[right];
        people[right] = key;
        sort(people, start, right-1);
        sort(people, right+1, end);
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight q = new QueueReconstructionByHeight();
        System.out.println(q.reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}));
    }

}
