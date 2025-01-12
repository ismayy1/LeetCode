package hard;
/*
    There are some robots and factories on the X-axis. You are given an integer array robot where
    robot[i] is the position of the ith robot. You are also given a 2D integer array factory where
    factory[j] = [positionj, limitj] indicates that positionj is the position of the jth factory and
    that the jth factory can repair at most limitj robots.
    The positions of each robot are unique. The positions of each factory are also unique. Note that
    a robot can be in the same position as a factory initially.
    All the robots are initially broken; they keep moving in one direction. The direction could be
    the negative or the positive direction of the X-axis. When a robot reaches a factory that did not
    reach its limit, the factory repairs the robot, and it stops moving.
    At any moment, you can set the initial direction of moving for some robot. Your target is to
    minimize the total distance traveled by all the robots.
    Return the minimum total distance traveled by all the robots. The test cases are generated such
    that all the robots can be repaired.

    Note that:
    All robots move at the same speed.
    If two robots move in the same direction, they will never collide.
    If two robots move in opposite directions and they meet at some point, they do not collide. They
    cross each other.
    If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
    If the robot moved from a position x to a position y, the distance it moved is |y - x|.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Exc15_MinTotalDistanceTraveled {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        int m = robot.size();
        int n = factory.length;
        long[][] dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];

                long cost = 0;
                for (int k = 1; k <= Math.min(factory[i - 1][1], j); k++) {
                    cost += Math.abs(robot.get(j - k) - factory[i - 1][0]);
                    if (dp[i - 1][j - k] != Long.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + cost);
                    }
                }
            }
        }

        return dp[n][m];
    }
}
