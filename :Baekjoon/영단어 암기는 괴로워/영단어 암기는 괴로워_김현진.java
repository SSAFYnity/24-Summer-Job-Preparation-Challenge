package boj.silver;

import java.io.*;
import java.util.*;

public class 영단어_암기는_괴로워_김현진 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(sc.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hash = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String str = sc.readLine();
            if (str.length() >= M) {
                hash.put(str, hash.getOrDefault(str, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(hash.entrySet());
        list.sort((entry1, entry2) -> {
            int cnt = Integer.compare(entry2.getValue(), entry1.getValue());
            if (cnt == 0) {
                int lengthCompare = Integer.compare(entry2.getKey().length(), entry1.getKey().length());
                if (lengthCompare == 0) return entry1.getKey().compareTo(entry2.getKey());
                return lengthCompare;
            } return cnt;
        });

        for (Map.Entry<String, Integer> entry : list) {
            bw.write(entry.getKey() + "\n");
        }

        bw.flush();
        bw.close();
    }
}
