import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int value : works) {
            pq.add(value);
        }

        while(n-- > 0) {
            if (pq.isEmpty()) break;

            int tmp = pq.poll();
            if (--tmp != 0) pq.add(tmp);
        }

        for(int value : pq) {
            answer += value * value;
        }


        return answer;
    }
}