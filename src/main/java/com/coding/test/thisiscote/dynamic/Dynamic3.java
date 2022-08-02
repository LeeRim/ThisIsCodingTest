package com.coding.test.thisiscote.dynamic;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 바닥 공사 : level1.5
 * n * 2 타일을 1*2, 2*1, 2*2 타일로 채우는 경우의 수
 * 796796로 나눈 나머지
 *
 *
 * [입력 조건]
 *  1 <= n <= 1,000
 * [풀이시간] 20분
 * [시간제한] 1초
 */
@Slf4j
@Component
public class Dynamic3 {

    public int solution(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.valueOf(1L);
        dp[1] = BigInteger.valueOf(1L);

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2].multiply(BigInteger.valueOf(2L)));
        }

        log.debug("dp[n] = " + Arrays.toString(dp));
        return dp[n].remainder(BigInteger.valueOf(796796L)).intValue();
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Dynamic3 sol = ac.getBean(Dynamic3.class);

        //return 5
        sol.solution(3);
    }
}
