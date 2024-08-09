import java.util.*;
import java.io.*;

public class Main {

    static int n, d, answer, inf = Integer.MAX_VALUE;

    static class Highway implements Comparable<Highway> {
        int s, e, t;
        public Highway(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
        public int compareTo(Highway o) {
            if (this.e == o.e) {
                if (this.s == o.s) return o.t - this.t;
                else return this.s - o.s;
            }
            return this.e - o.e;
        }
        public String toString() {
            return s + "->" + e + " " + t;
        }
    }

    public static int binarySearch(int target, int[] arr) {
        int s = 0;
        int e = arr.length-1;
        int p;

        while (s <= e) {
            p = (s+e)/2;
            if (arr[p] == target) return p;
            else if (arr[p] > target) e = p-1;
            else s = p+1;
        }
        return e;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        TreeSet<Highway> ways = new TreeSet<>();
        TreeSet<Integer> sections = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            ways.add(new Highway(s, e, t));
            sections.add(e);
        }
        sections.add(0);
        sections.add(d);
        int[] until = new int[sections.size()];
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int i : sections) {
            map.put(i, idx);
            until[idx++] = i;
        }

        int[] dp = new int[sections.size()];
        Arrays.fill(dp, inf);
        dp[0] = 0;

        for (Highway h : ways) {
            idx = binarySearch(h.s, until);
            int pre = binarySearch(h.e, until) - 1;
            int temp = dp[map.get(h.e)];
            dp[map.get(h.e)] = Math.min(dp[idx] + (h.s-until[idx]) + h.t, dp[pre] + (h.e-until[pre]));
            dp[map.get(h.e)] = Math.min(dp[map.get(h.e)], temp);
        }

        dp[map.get(d)] = Math.min(dp[map.get(d)-1] + d-until[map.get(d)-1], dp[map.get(d)]);
        System.out.println(dp[map.get(d)]);

    }
}
