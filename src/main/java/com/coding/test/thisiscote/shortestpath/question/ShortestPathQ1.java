package com.coding.test.thisiscote.shortestpath.question;

import com.coding.test.thisiscote.SpringConfig;
import com.coding.test.thisiscote.dynamic.question.DynamicQ2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 플로이드 : level1.5
 * https://www.acmicpc.net/problem/11404
 * <p>
 * [입력 조건]
 * 2 ≤ N ≤ 100
 * 1 <= m <= 100,000
 * [풀이시간] 40분
 * [시간제한] 1초
 */
@Component
public class ShortestPathQ1 {

    public static final int MAXVALUE = (int) 1e9;

    public void solution(int n, int m, int[][] graph) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = graph[i].clone();
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (i == k) {
                    continue;
                }
                for (int j = 1; j <= n; j++) {
                    if (i == j || k == j) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == MAXVALUE || i == j) {
                    dp[i][j] = 0;
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        ShortestPathQ1 sol = ac.getBean(ShortestPathQ1.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    continue;
                }
                graph[i][j] = MAXVALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            int[] path = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph[path[0]][path[1]] = Math.min(path[2], graph[path[0]][path[1]]);
        }

        sol.solution(n, m, graph);
    }
}
