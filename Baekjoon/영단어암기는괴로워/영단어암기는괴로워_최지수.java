import java.util.*;
import java.io.*;

public class Main {

    public static class Word implements Comparable<Word> {
        String str;
        int cnt, len;
        public Word(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
            this.len = str.length();
        }

        public int compareTo(Word o) {
            if (o.cnt == this.cnt && o.len == this.len) {
                return this.str.compareTo(o.str);
            } else if (o.cnt == this.cnt) {
                return o.len - this.len;
            }
            return o.cnt - this.cnt;
        }

        public String toString() {
            return "[" + str + " " + cnt + "]";
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Word> que = new PriorityQueue<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (s.length() >= m) {
                map.put(s, map.getOrDefault(s, 0)+1);
            }
        }

        for (String s : map.keySet()) {
            que.add(new Word(s, map.get(s)));
        }

        while (!que.isEmpty()) {
            bw.append(que.poll().str + "\n");
        }
        bw.flush();
        bw.close();
    }
}

