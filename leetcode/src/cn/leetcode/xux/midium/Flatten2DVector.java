package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 251 Flatten 2D Vector
 * Implement an iterator to flatten a 2d vector.
 *
 * For example,
 * Given 2d vector =
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 */
public class Flatten2DVector {

    public static void main(String[] args) {
        List<Integer> list0 = new ArrayList<>();
        list0.add(1);
        list0.add(2);
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        List<List<Integer>> listOfList = new ArrayList<>();
        listOfList.add(list0);
        listOfList.add(list1);
        listOfList.add(list2);
        Vector2D vector2D = new Vector2D(listOfList);
        while(vector2D.hasNext()) {
            System.out.println(vector2D.next());
        }
    }

}

class Vector2D {
    List<List<Integer>> listOfList;
    int i;
    int j;

    Vector2D(List<List<Integer>> listOfList) {
        this.listOfList = listOfList;
        this.i = 0;
        this.j = 0;
    }

    public boolean hasNext() {
        while(i<this.listOfList.size()&&this.j==this.listOfList.get(this.i).size()) {
            this.i++;
            this.j=0;
        }
        if(this.i==this.listOfList.size()) {
            return false;
        }else {
            return true;
        }
    }

    public int next() {
        return this.listOfList.get(this.i).get(this.j++);
    }

}
