package com.coding.test.thisiscote.shortestpath;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 최단거리
 * 플로이드 워셜 알고리즘 구현
 */
@Slf4j
@Component
public class FloydWarshall {

    public int[][] byDynamic(int node, int edge, int[][] roads) {
        final int MAX = (int) 1e9;

        int[][] dp = new int[node + 1][node + 1];
        for (int i = 0; i <= node; i++) {
            for (int j = 0; j <= node; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = MAX;
            }
        }
        for (int[] road : roads) {
            dp[road[0]][road[1]] = road[2];
        }
//        for (int[] row : dp) {
//            System.out.println(Arrays.toString(row));
//        }

        for (int k = 0; k <= node; k++) {
            for (int start = 0; start <= node; start++) {
                if (k == start) {
                    continue;
                }
                for (int end = 1; end <= node; end++) {
                    if (k == end || start == end) {
                        continue;
                    }
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k][end]);
                }
            }
        }

        for (int i = 1; i <= node; i++) {
            for (int j = 1; j <= node; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        return dp;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        FloydWarshall sol = ac.getBean(FloydWarshall.class);

//      0	4	8	6
//      3	0	7	9
//      5	9	0	4
//      7	11	2	0
        sol.byDynamic(4, 7, new int[][]{{1, 2, 4}, {1, 4, 6}, {2, 1, 3}, {2, 3, 7}, {3, 1, 5}, {3, 4, 4}, {4, 3, 2}});
    }
}
