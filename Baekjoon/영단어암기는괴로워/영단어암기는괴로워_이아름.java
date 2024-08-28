import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Word> pq = new PriorityQueue<>();
        Map<String, Word> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str.length() >= M) {
                map.put(str, new Word(str, 1 + map.getOrDefault(str, new Word(str, 0)).count));
            }
        }
        for (Word w : map.values()) {
            pq.offer(w);
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().str).append("\n");
        }
        System.out.println(sb);
    }

    static class Word implements Comparable<Word> {
        String str;
        int count;

        Word(String str, int count) {
            this.str = str;
            this.count = count;
        }

        @Override
        public int compareTo(Word o) {
            int c = Integer.compare(o.count, count);
            if (c == 0) {
                int cc = Integer.compare(o.str.length(), str.length());
                if (cc == 0) {
                    return str.compareTo(o.str);
                } else {
                    return cc;
                }
            } else {
                return c;
            }
        }
    }
}