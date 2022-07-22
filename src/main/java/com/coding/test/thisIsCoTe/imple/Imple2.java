package com.coding.test.thisIsCoTe.imple;

import com.coding.test.thisIsCoTe.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 시각
 * n시 59분 59초까지 3이 포함된 경우의 수
 *
 * [입력 조건]
 *  0 <= n <= 23
 * [풀이시간] 15분
 * [시간제한] 2초
 */
@Component
public class Imple2 {

    public int solution(int n) {
        int in60 = 0;
        int num1, num2;
        for (int i = 0; i < 60; i++) {
            num1 = i / 10;
            num2 = i % 10;
            if (num1 == 3 || num2 == 3) {
//                System.out.println(i);
                in60++;
            }
        }
//        System.out.println(in60);
//        System.out.println((120 - in60) * in60);

        int min = (120 - in60) * in60;
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 3) {
                count += 60 * 60;
            } else {
                count += min;
            }
        }

        System.out.println(count);
        return count;
    }

    public int solution2(int n) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    if (i == 3 || j / 10 == 3 || j % 10 == 3 || k / 10 == 3 || k % 10 == 3 ) {
//                        System.out.println(i + ":" + j + ":" + k);
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Imple2 sol = ac.getBean(Imple2.class);

        sol.solution(5);
        sol.solution(5);
        sol.solution2(5);
    }
}
