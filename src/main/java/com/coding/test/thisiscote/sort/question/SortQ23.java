package com.coding.test.thisiscote.sort.question;

import com.coding.test.thisiscote.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 국영수 : level1
 * https://www.acmicpc.net/problem/10825
 *
 * [입력 조건]
 * 1 <= points.size() <= 100,000
 * [풀이시간] 20분
 * [시간제한] 1초
 */
@Component
public class SortQ23 {

    public void solution(List<Point> points) {
        Collections.sort(points);
        for (Point point : points) {
            System.out.println(point.getName());
        }
    }

    static class Point implements Comparable<Point> {
        private String name;
        private int language;
        private int english;
        private int math;

        public Point(String name, int language, int english, int math) {
            this.name = name;
            this.language = language;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Point o) {
            if (language == o.language) {
                if (english == o.english) {
                    if (o.math == math) {
                        return name.compareTo(o.name);
                    }
                    return o.math - math;
                }
                return english - o.english;
            }
            return o.language - language;
        }
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);

        SortQ23 sol = ac.getBean(SortQ23.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            String[] s = str.split(" ");
            points.add(new Point(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3])));
        }
        sol.solution(points);
    }
}
