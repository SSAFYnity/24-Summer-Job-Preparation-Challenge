import java.io.*;
import java.util.*;
class Solution {
    // 1. n진법으로 변환한다.
    // 2. 변환한 수를 0을 기준으로 자른다.
    // 3. 각 숫자별로 소수 판별 한다.

    public int solution(int n, int k) {


        StringTokenizer st = new StringTokenizer(baseN(n,k),"0", false); // 진법 변환 및 0마다 자르기
        int cnt = 0; // 소수가 나온 횟수
        while(st.hasMoreTokens()){
            if(isPrime(Long.parseLong(st.nextToken()))) cnt++;
        }
        return cnt;
    }

    public String baseN(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while(n/k != 0) {
            sb.insert(0, n%k);
            n = n/k;
        }
        sb.insert(0, n%k);

        return sb.toString();
    }

    public boolean isPrime(long value) {

        if(value == 1) return false;

        for(long i = 2; i <= Math.sqrt(value); i++){
            if(value%i == 0) return false;
        }
        return true;
    }
}