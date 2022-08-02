package com.coding.test.thisiscote.dynamic;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 효율적인 화폐 구성 : level2
 * 주어진 화폐들로 k원을 만들 때 필요한 최소한의 화폐 갯수
 * 불가능할 경우 -1
 *
 * [입력 조건]
 * 1 <= moneys.lenght <= 100
 * 1 <= k <= 10,000
 * 0 < moneys[i] <= k
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Slf4j
@Component
public class Dynamic4 {

    public int solution(int k, int[] moneys) {
        int[] dp = new int[k + 1];
        Arrays.sort(moneys);

        boolean canMake;
        for (int i = 1; i <= k; i++) {
            dp[i] = k;
            canMake = false;
            for (int money : moneys) {
                if (i < money) {
                    break;
                }
                if (i == money) {
                    dp[i] = 0;
                    canMake = true;
                    break;
                }
                if (dp[i - money] > 0) {
                    dp[i] = Math.min(dp[i - money], dp[i]);
                    canMake = true;
                }
            }
            if (canMake) {
                dp[i] += 1;
            } else {
                dp[i] = -1;
            }
        }

        log.debug("dp[k] = " + dp[k]);
        return dp[k];
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Dynamic4 sol = ac.getBean(Dynamic4.class);

        //return 5
        sol.solution(15, new int[]{2, 3});
        //return -1
        sol.solution(4, new int[]{3, 5, 7});
    }
}
