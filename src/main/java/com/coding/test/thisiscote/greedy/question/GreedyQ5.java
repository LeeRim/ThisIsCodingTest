package com.coding.test.thisiscote.greedy.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 볼링공 고르기 : level1
 * 2사람이 볼링공을 고른다
 * 서로 다른 무게의 볼링공을 고르는 경우의 수
 * 같은 무게여도 번호가 다르면 다른 볼링공이다
 * <p>
 * [입력 조건]
 * 1 <= weights.length <= 1,000
 * 1 <= weights.max() <= 10
 * 1 <= weights[i] <= weights.max()
 * <p>
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class GreedyQ5 {

    public int solution(int[] weights) {
        int count = 0;

        int firstWeight;
        for (int i = 0; i < weights.length; i++) {
            firstWeight = weights[i];
            for (int j = i + 1; j < weights.length; j++) {
                if (firstWeight != weights[j]) {
                    count++;
                }
            }
        }

        System.out.println("count = " + count);
        return count;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        GreedyQ5 sol = ac.getBean(GreedyQ5.class);

        // 8
        sol.solution(new int[]{1, 3, 2, 3, 2});
        // 25
        sol.solution(new int[]{1, 5, 4, 3, 2, 4, 5, 2});
    }
}
