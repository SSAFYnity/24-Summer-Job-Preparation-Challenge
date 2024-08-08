import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Shorcut {
        int x, y, length;

        public Shorcut(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Shorcut{" +
                    "x=" + x +
                    ", y=" + y +
                    ", length=" + length +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        PriorityQueue<Shorcut> pq = new PriorityQueue<>((o1, o2) -> o1.x == o2.x ?
                (o2.y - o2.x - o2.length) - (o1.y - o1.x - o1.length) : o1.x - o2.x);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            if (end <= D && end - start > length) {
                pq.add(new Shorcut(start, end, length));
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty() && !pq.isEmpty()) {
            int size = q.size();
            Shorcut shortcut = pq.poll();
            for (int s = 0; s < size; s++) {
                Point p = q.poll();
                q.add(p);
                if (shortcut.x >= p.x)
                    q.add(new Point(shortcut.y, p.y + shortcut.length + (shortcut.x - p.x)));
            }
        }
        while (!q.isEmpty()) {
            Point p = q.poll();
            answer = Math.min(answer, p.y + D - p.x);
        }
        System.out.println(answer);
    }
}