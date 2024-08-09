import java.util.*;

class Solution {
    public boolean isPrime(long num) {
        if (num == 1L) return false;
        else if (num == 2L) return true;
        else if (num % 2 == 0) return false;
        
        for (long i = 3; i <= Math.sqrt(num); i+=2) {
            if (num%i == 0) return false;
        }
        return true;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while (n/k > 0) {
            sb.insert(0, n%k);
            n/=k;
        }
        if (n != 0) sb.insert(0, n%k);
        
        
        String[] str = sb.toString().split("0");
        List<Long> list = new ArrayList<>();
        
        long maxi = 0;
        for (String s: str) {
            if (s.equals("")) continue;
            long num = Long.parseLong(s);
            list.add(num);
        }
        
        for (long i : list) {
            if (isPrime(i)) answer++;
        }
        
        return answer;
    }
}