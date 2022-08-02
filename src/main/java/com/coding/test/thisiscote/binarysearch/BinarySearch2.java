package com.coding.test.thisiscote.binarysearch;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 떡볶이 떡 만들기 : level 2
 *
 * [입력 조건]
 * 1 <= lens.length <= 1,000,000
 * 1 <= k <= 2,000,000,000
 * 0 <= lens[i] <= 1,000,000,000
 * [풀이시간] 40분
 * [시간제한] 2초
 */
@Slf4j
@Component
public class BinarySearch2 {

    public int solution(int k, int[] lens) {

        Arrays.sort(lens);
        int max = lens[lens.length - 1];
        int min = 0;

        int sum = 0;
        int height = max / 2;
        while (sum != k) {
            sum = 0;
            for (int len : lens) {
                if (len > height) {
                    sum += len - height;
                }
            }

            if (sum == k) {
                break;
            }
            if (sum > k) {
                min = height;
                height += ((max - min) / 2);
                continue;
            }
            max = height;
            height -= ((max - min) / 2);
        }

        log.info("height={}", height);
        return height;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        BinarySearch2 sol = ac.getBean(BinarySearch2.class);

        //return 15
        sol.solution(6, new int[] {19, 14, 10, 17});
    }
}
