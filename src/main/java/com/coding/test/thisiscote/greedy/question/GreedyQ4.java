package com.coding.test.thisiscote.greedy.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 만들 수 없는 금액 : level1
 * 주어진 동전들로 지불 할 수 없는 최소 값
 * <p>
 * [입력 조건]
 * 1 <= coins.length <= 1,000
 * 1 <= coins[i] <= 1,000,000
 * <p>
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class GreedyQ4 {

    public int solution(int[] coins) {
        Arrays.sort(coins);

        int min = 1;
        for (int coin : coins) {
            if (min < coin) {
                break;
            }
            min += coin;
        }

        System.out.println("min = " + min);
        return min;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        GreedyQ4 sol = ac.getBean(GreedyQ4.class);

        // 8
        sol.solution(new int[]{3, 2, 1, 1, 9});
        // 1
        sol.solution(new int[]{3, 5, 7});
    }
}
