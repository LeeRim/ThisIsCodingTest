package com.coding.test.thisiscote.greedy.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 모험가 길드 : level1
 * 공포도가 x인 모험가는 반드시 x명 이상으로 구성한 그룹에 참여 가능
 * 최대 그룹 수
 * <p>
 * [입력 조건]
 * 1 <= n <= 100,000
 * 0 <= degree[i] <= n
 * <p>
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class GreedyQ1 {

    public int solution(int n, int[] degree) {
        Arrays.sort(degree);
//        System.out.println(Arrays.toString(degree));

        int groupCount = 0;
        int count = 0;
//        List<Integer> count = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            count++;
            if (degree[i] <= count) {
                groupCount++;
                count = 0;
            }
        }

        System.out.println("groupCount = " + groupCount);
        return groupCount;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        GreedyQ1 sol = ac.getBean(GreedyQ1.class);

        sol.solution(5, new int[]{2, 3, 1, 2, 2});
    }
}
