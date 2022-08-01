package com.coding.test.thisiscote.greedy;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Greedy4 {
    public int solution(int n, int k) {

        int count = 0;
        while (n > 1) {
            if ((n % k) == 0) {
                n /= k;
            } else {
                n--;
            }
            count++;
//            System.out.println("n = " + n);
        }

        System.out.println("count = " + count);
        return count;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        Greedy4 sol = ac.getBean(Greedy4.class);

        sol.solution(17, 4);
        sol.solution(25, 3);
    }
}
