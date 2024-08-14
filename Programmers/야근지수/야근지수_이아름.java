import java.io.*;
import java.util.*;

class Solution {

    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++){
            pq.offer(works[i]);
        }
        int count = n;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if(cur - 1 > 0){
                pq.offer(cur - 1);
            }
            n--;
            if(n == 0) break;
        }
        while(!pq.isEmpty()){
            int cur = pq.poll();
            answer += cur * cur;
        }
        return answer;
    }
}