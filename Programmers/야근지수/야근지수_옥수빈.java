import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works){
            pq.add(work);
        }
        
        while(!pq.isEmpty() && n > 0){
            int x = pq.poll();
            n--;
            if(x > 0)
                pq.add(x-1);
        }
        
        if(pq.isEmpty())
            return answer;
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}