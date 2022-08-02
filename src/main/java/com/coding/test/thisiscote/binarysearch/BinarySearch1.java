package com.coding.test.thisiscote.binarysearch;

import com.coding.test.thisiscote.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 제품목록에 주문목록 포함 여부
 *
 * [입력 조건]
 *  1 <= products.length <= 1,000,000
 *  1 <= order.length <= 100,000
 *  0 <= 제품번호(products[n]) <= 1,000,000
 * [풀이시간] 30분
 * [시간제한] 1초
 */
@Component
public class BinarySearch1 {

    public String[] solution(int[] products, int[] order) {
        String[] exist = new String[order.length];

        Arrays.sort(products);

        int i = 0;
        for (int o : order) {
            if (Arrays.binarySearch(products, o) < 0) {
                exist[i] = "no";
            } else {
                exist[i] = "yes";
            }
            i++;
        }

        System.out.println("exist = " + Arrays.toString(exist));
        return exist;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        BinarySearch1 sol = ac.getBean(BinarySearch1.class);

        //no yes yes
        sol.solution(new int[]{8,3,7,9,2}, new int[]{5,7,9});
    }
}
