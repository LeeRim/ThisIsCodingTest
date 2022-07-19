package com.coding.test.thisIsCoTe.greedy;

import org.springframework.stereotype.Component;

import java.util.Arrays;

public class Solution4 {
    public int solution(int n, int k) {

        int count = 0;
        while (n > 1) {
            if ((n % k) == 0) {
                n /= k;
            } else {
                n--;
            }
            count++;
//            System.out.println("n = " + n);
        }

        System.out.println("count = " + count);
        return count;
    }

    public static void main(String[] args) {
        Solution4 sol = new Solution4();
        sol.solution(17, 4);
        sol.solution(25, 3);
    }
}
