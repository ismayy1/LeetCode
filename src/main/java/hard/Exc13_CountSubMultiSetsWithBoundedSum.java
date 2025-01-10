package hard;
/*
    You are given a 0-indexed array nums of non-negative integers, and two integers l and r.
    Return the count of sub-multisets within nums where the sum of elements in each subset falls
    within the inclusive range of [l, r]. Since the answer may be large, return it modulo 109 + 7.
    A sub-multiset is an unordered collection of elements of the array in which a given value x can
    occur 0, 1, ..., occ[x] times, where occ[x] is the number of occurrences of x in the array.
    Note that:
    Two sub-multisets are the same if sorting both sub-multisets results in identical multisets.
    The sum of an empty multiset is 0.

    Example 1:
    Input: nums = [1,2,2,3], l = 6, r = 6
    Output: 1
    Explanation: The only subset of nums that has a sum of 6 is {1, 2, 3}.
 */

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Exc13_CountSubMultiSetsWithBoundedSum {

    static final int MOD = 1_000_000_007;

    public int countSubMultisets(List<Integer> nums, int l, int r) {
        TreeMap<Integer, Integer> numsMap = new TreeMap<>();
        for (int num : nums) {
            numsMap.put(num, numsMap.getOrDefault(num, 0) + 1);
        }

        long[] cache = new long[r + 1];
        cache[0] = 1;

        for (Map.Entry<Integer, Integer> entry : numsMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            // preprocessing
            long[] pSum = Arrays.copyOf(cache, r + 1);
            for (int i = 0; i <= r; ++i) {
                if (i >= num) {
                    pSum[i] += pSum[i - num];
                    pSum[i] %= MOD;
                }
            }

            // update dp cache
            for (int i = r; i >= 0; --i) {
                if (num > 0) {
                    int j = i - (freq + 1) * num;
                    cache[i] = pSum[i] - (j >= 0 ? pSum[j] : 0);
                    cache[i] = Math.floorMod(cache[i], MOD);
                } else {
                    cache[i] *= freq + 1;
                    cache[i] %= MOD;
                }
            }
        }

        long res = 0;
        for (int i = l; i <= r; ++i) {
            res += cache[i];
            res %= MOD;
        }
        return (int) res;
    }
}
