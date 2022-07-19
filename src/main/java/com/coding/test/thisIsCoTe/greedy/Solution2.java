package com.coding.test.thisIsCoTe.greedy;

import java.util.Arrays;

public class Solution2 {

    public int solution(int[] nums, int m, int k) {

        Arrays.sort(nums);
        int len = nums.length;
        int count = k;
        int sum = 0;
        int sum_num;
        while (m > 0) {
            if (count == 0) {
                sum_num = nums[len - 2];
                count = k;
            } else {
                sum_num = nums[len - 1];
                count--;
            }
//            System.out.println("sum_num = " + sum_num);

            sum += sum_num;
            m--;
        }

        System.out.println("sum = " + sum);
        return sum;
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        sol.solution(new int[]{2, 4, 5, 4, 6}, 8, 3);
        sol.solution(new int[]{3, 4, 3, 4, 3}, 7, 2);
    }
}
