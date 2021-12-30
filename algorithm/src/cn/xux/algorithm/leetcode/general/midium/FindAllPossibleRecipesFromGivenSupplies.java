package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 5947. 从给定原材料中找到所有可以做出的菜
 * 你有 n 道不同菜的信息。
 * 给你一个字符串数组 recipes 和一个二维字符串数组 ingredients 。
 * 第 i 道菜的名字为 recipes[i] ，
 * 如果你有它 所有 的原材料 ingredients[i] ，
 * 那么你可以 做出 这道菜。
 * 一道菜的原材料可能是 另一道 菜，
 * 也就是说 ingredients[i] 可能包含 recipes 中另一个字符串。
 * <p>
 * 同时给你一个字符串数组 supplies ，
 * 它包含你初始时拥有的所有原材料，
 * 每一种原材料你都有无限多。
 * <p>
 * 请你返回你可以做出的所有菜。
 * 你可以以 任意顺序 返回它们。
 * <p>
 * 注意两道菜在它们的原材料中可能互相包含。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * 输出：["bread"]
 * 解释：
 * 我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
 * 示例 2：
 * <p>
 * 输入：recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * 输出：["bread","sandwich"]
 * 解释：
 * 我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
 * 我们可以做出 "sandwich" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 。
 * 示例 3：
 * <p>
 * 输入：recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * 输出：["bread","sandwich","burger"]
 * 解释：
 * 我们可以做出 "bread" ，因为我们有原材料 "yeast" 和 "flour" 。
 * 我们可以做出 "sandwich" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 。
 * 我们可以做出 "burger" ，因为我们有原材料 "meat" 且可以做出原材料 "bread" 和 "sandwich" 。
 * 示例 4：
 * <p>
 * 输入：recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast"]
 * 输出：[]
 * 解释：
 * 我们没法做出任何菜，因为我们只有原材料 "yeast" 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j] 和 supplies[k] 只包含小写英文字母。
 * 所有 recipes 和 supplies 中的值互不相同。
 * ingredients[i] 中的字符串互不相同。
 */
public class FindAllPossibleRecipesFromGivenSupplies {

    public static void main(String[] args) {

        FindAllPossibleRecipesFromGivenSupplies ff =
                new FindAllPossibleRecipesFromGivenSupplies();
        System.out.println(ff.findAllRecipes(
                new String[]{"bread","sandwich","burger"},
                Arrays.asList(Arrays.asList("yeast","flour"),
                        Arrays.asList("bread","meat"),
                        Arrays.asList("sandwich","meat","bread")),
                new String[]{"yeast","flour","meat"}
        ));
    }

    Set<String> sup;
    Map<String, List<String>> map;
    Map<String, Boolean> mem;

    public List<String> findAllRecipes(String[] recipes,
                                       List<List<String>> ingredients,
                                       String[] supplies) {
        sup = new HashSet<>();
        sup.addAll(Arrays.asList(supplies));
        int n = recipes.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(recipes[i], ingredients.get(i));
        }
        mem = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (String r : recipes) {
            if (dfs(r, new HashSet<>())) {
                ans.add(r);
            }
        }
        return ans;
    }

    private boolean dfs(String tar, Set<String> pres) {
        if (sup.contains(tar)) {
            return true;
        }
        if (!map.containsKey(tar)) {
            return false;
        }
        if(mem.containsKey(tar)) {
            return mem.get(tar);
        }
        boolean ans = true;
        if (!pres.add(tar)) {
            ans = false;
        } else {
            for (String need : map.get(tar)) {
                if (!dfs(need, pres)) {
                    ans = false;
                    break;
                }
            }
        }
        mem.put(tar, ans);
        return ans;
    }

}
