package com.coding.test.thisiscote.dynamic;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 1로 만들기 : level1.5
 *  5로 나누어 떨어지면 5로 나눈다
 *  3으로 나누어 떨어지면 3으로 나눈다
 *  2로 나누어 떨어진면 2로 나눈다
 *  1을 뺀다
 *  4가지 연산(아무거나)을 반복해서 1을 만든다
 *
 * [입력 조건]
 *  1 <= n <= 30,000
 * [풀이시간] 20분
 * [시간제한] 1초
 */
@Slf4j
@Component
public class Dynamic1 {

    public int solution(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;

        int[] divs = {5, 3, 2};
        int min;
        for (int i = 2; i <= n; i++) {
            min = i;

            for (int div : divs) {
                if (i % div == 0) {
                    min = Math.min(dp[i / div], min);
                    break;
                }
            }

            min = Math.min(dp[i - 1], min);

            dp[i] = min + 1;
        }

        log.debug("dp[n] = " + dp[n]);
        return dp[n];
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Dynamic1 sol = ac.getBean(Dynamic1.class);

        //return 3
        sol.solution(26);
    }
}
