package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 *
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    public static List<List<Integer>> solution(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(list.size()==0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        List<List<Integer>> post = solution(list.subList(1, list.size()));
        result.addAll(post);
        for(List<Integer> li : post) {
            List<Integer> newList = new ArrayList<Integer>(li);
            newList.add(list.get(0));
            result.add(newList);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(solution(list));
    }

}
