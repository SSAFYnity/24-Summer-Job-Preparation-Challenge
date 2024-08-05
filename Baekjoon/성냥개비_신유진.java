package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 성냥개비_신유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int value = Integer.parseInt(br.readLine());

            // 가장 작은 수 계산
            String[] minArr = new String[101];

            // 초기 값 설정 (최소 숫자)
            minArr[2] = "1";
            minArr[3] = "7";
            minArr[4] = "4";
            minArr[5] = "2";
            minArr[6] = "6";
            minArr[7] = "8";
            minArr[8] = "10";

            for (int i = 9; i <= value; i++) {
                minArr[i] = minArr[i - 2] + "1";
                for (int j = 3; j <= 7; j++) {
                    if (i >= j) {
                        String candidate = minArr[i - j] + minArr[j];
                        if (minArr[i].length() > candidate.length() ||
                                (minArr[i].length() == candidate.length() && minArr[i].compareTo(candidate) > 0)) {
                            minArr[i] = candidate;
                        }
                    }
                }
            }
            String minRes = minArr[value];

            // 가장 큰 수 계산
            int maxRes = 0;
            int div2 = value / 2;
            if (value % 2 == 0) {
                maxRes = (int) ((Math.pow(10, div2) - 1) / 9);
            } else {
                div2 -= 1;
                int tmp = (int) ((Math.pow(10, div2) - 1) / 9);
                maxRes = (int) (7 * Math.pow(10, div2) + tmp);
            }

            System.out.println(minRes + " " + maxRes);
        }
    }
}

