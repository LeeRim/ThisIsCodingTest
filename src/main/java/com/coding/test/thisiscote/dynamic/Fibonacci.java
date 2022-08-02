package com.coding.test.thisiscote.dynamic;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * 피보나치 재귀함수, 반복문 구현
 */
@Slf4j
@Component
public class Fibonacci {

    BigInteger[] memoization;


    public BigInteger recursive(int n) {
        memoization = new BigInteger[n + 1];

        log.debug("recursionFunction = " + recursionFunction(n));
        return recursionFunction(n);
    }

    public BigInteger recursionFunction(int n) {
        if (n == 1 || n == 2) {
            return BigInteger.valueOf(1L);
        }
        if (memoization[n] != null) {
            return memoization[n];
        }
        memoization[n] = recursionFunction(n - 1).add(recursionFunction(n - 2));
        return memoization[n];
    }

    public BigInteger iterative(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[1] = BigInteger.valueOf(1L);
        dp[2] = BigInteger.valueOf(1L);

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }

        log.debug("iterative = " + dp[n]);
        return dp[n];
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Fibonacci sol = ac.getBean(Fibonacci.class);

        //218922995834555169026
        sol.recursive(99);
        sol.iterative(99);
    }
}
