package cn.leetcode.xux.general.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 355. 设计推特
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。
 * 你的设计需要支持以下的几个功能：
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。
 * 推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 *
 * 示例:
 * Twitter twitter = new Twitter();
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 */
public class DesignTwitter {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 1);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 4);
        twitter.postTweet(1, 5);
        twitter.postTweet(1, 6);
        twitter.postTweet(1, 7);
        twitter.postTweet(1, 8);
        twitter.postTweet(1, 9);
        twitter.postTweet(1, 10);
        twitter.postTweet(1, 11);
        System.out.println(twitter.getNewsFeed(1));
    }

}

class Twitter {

    List<int[]> list = new ArrayList<>();
    Map<Integer, List<Integer>> followMap = new HashMap<>();

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        int[] news = new int[]{userId, tweetId};
        this.list.add(news);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> users = new ArrayList<>();
        users.add(userId);
        users.addAll(this.followMap.getOrDefault(userId, new ArrayList<>()));
        List<Integer> res = new ArrayList<>();
        for(int i=this.list.size()-1;i>=0;i--) {
            if(users.contains(new Integer(this.list.get(i)[0]))) {
                res.add(this.list.get(i)[1]);
                if(res.size()==10) {
                    break;
                }
            }

        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        List<Integer> list = this.followMap.getOrDefault(followerId, new ArrayList<>());
        list.add(followeeId);
        this.followMap.put(followerId, list);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(this.followMap.containsKey(followerId)&&this.followMap.get(followerId).contains(new Integer(followeeId))) {
            this.followMap.get(followerId).remove(new Integer(followeeId));
        }
    }

}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
