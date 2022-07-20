package com.coding.test.thisIsCoTe.greedy;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Greedy3 {

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
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Greedy3 sol = ac.getBean(Greedy3.class);

        sol.solution(new int[][] {{3, 1, 2}, {4, 1, 4}, {2, 2, 2}});
        sol.solution(new int[][] {{7, 3, 1, 8}, {3, 3, 3, 4}});
    }
}