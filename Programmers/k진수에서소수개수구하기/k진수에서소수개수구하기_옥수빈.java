import java.util.*;

class Solution {
    public int solution(int n, int k) {
        
        System.out.println(trans(n, k));
        StringTokenizer st = new StringTokenizer(trans(n, k), "0");
        int answer = 0;
        while(st.hasMoreTokens()){
            if(isPrime(Long.valueOf(st.nextToken())))
                answer++;
        }
        
        return answer;
    }
    
    public String trans(int n, int k){
        String string = "";
        while(n != 0){
            string = (n%k) + string;
            n = (n - (n%k)) / k;
        }
        return string;
    }
    
    public boolean isPrime(long n){
        if(n == 1)
            return false;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n%i == 0)
                return false;
        }
        return true;
    }
    
}