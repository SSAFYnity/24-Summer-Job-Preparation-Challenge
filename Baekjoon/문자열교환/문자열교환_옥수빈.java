package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_1522_문자열교환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        int length = string.length();
        int[] aIndex = new int[length + 1];
        aIndex[0] = 0;
        int aCount = 0;
        for (int i = 1; i <= length; i++) {
            if (string.charAt(i - 1) == 'a') {
                aIndex[i] = aIndex[i - 1] + 1;
                aCount++;
            } else {
                aIndex[i] = aIndex[i - 1];
            }
        }

        int answer = length;
        for (int s = 1; s <= length; s++) {
            int e = s + aCount - 1 > length ? s + aCount - length - 1 : s + aCount - 1;
            int needChange = 0;
            if (s < e) {
                needChange = aCount - (aIndex[e] - aIndex[s - 1]);
            } else if (s > e) {
                needChange = aCount - (aIndex[length] - aIndex[s - 1] + aIndex[e]);
            }
            answer = Math.min(answer, needChange);
        }

        System.out.println(answer);
    }
}
