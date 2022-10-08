package com.coding.test.thisiscote.dynamic.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 정수 삼각형 : level1.5
 * https://www.acmicpc.net/problem/1932
 * <p>
 * [입력 조건]
 * 1 ≤ N ≤ 500
 * 0 <= graph[i][j] <= 9,999
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class DynamicQ2 {

    public void solution(int n, int[][] graph) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < graph.length; i++) {
            dp[i] = graph[i].clone();
        }

        dp[0][0] = graph[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + graph[i][j];
                    continue;
                }
                if (j == dp[i - 1].length) {
                    dp[i][j] = dp[i - 1][j - 1] + graph[i][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + graph[i][j];
            }
        }

//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp[n - 1][i], max);
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        DynamicQ2 sol = ac.getBean(DynamicQ2.class);

        //30
        sol.solution(5, new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
    }
}
