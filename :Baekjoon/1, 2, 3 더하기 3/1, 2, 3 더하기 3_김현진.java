package boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1, 2, 3 더하기 3_김현진 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(sc.readLine());
        long[] array = new long[1000001];

        array[0] = 1;
        array[1] = 2;
        array[2] = 4;

        for(int test_case=0; test_case<T; test_case++){
            int n = Integer.parseInt(sc.readLine());
            for(int i=3; i<n; i++){
                array[i] = (array[i-3]+ array[i-2] + array[i-1]) % 1000000009;
            } sb.append(array[n-1]).append('\n');
        } System.out.println(sb);
    }
}
