package com.coding.test.thisiscote.dynamic;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 개미전사 : level2
 * 최대한 큰 합 구하기
 * 최소 한칸이상 떨어진 값을 더할 수 있다
 *
 * [입력 조건]
 *  3 <= nums.length <= 100
 *  0 <= nums[i] <= 1,000
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Slf4j
@Component
public class Dynamic2 {

    public int solution(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        log.debug("dp[nums.length - 1] = " + dp[nums.length - 1]);
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Dynamic2 sol = ac.getBean(Dynamic2.class);

        //return 8
        sol.solution(new int[] {1, 3, 1, 5});
    }
}
