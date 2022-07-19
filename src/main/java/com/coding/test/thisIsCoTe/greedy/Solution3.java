package com.coding.test.thisIsCoTe.greedy;

import java.util.Arrays;

public class Solution3 {
    public int solution(int[][] nums) {

        int max = 0;
        int min;
        for (int[] num : nums) {
            min = Arrays.stream(num).min().getAsInt();
//            System.out.println("min = " + min);
            max = Math.max(max, min);
        }

        System.out.println("max = " + max);
        return max;
    }

    public static void main(String[] args) {
        Solution3 sol = new Solution3();
        sol.solution(new int[][] {{3, 1, 2}, {4, 1, 4}, {2, 2, 2}});
        sol.solution(new int[][] {{7, 3, 1, 8}, {3, 3, 3, 4}});
    }
}