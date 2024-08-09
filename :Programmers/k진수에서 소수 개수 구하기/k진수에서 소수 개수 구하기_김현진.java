import java.util.*;

class k진수에서 소수 개수 구하기_김현진 {
    
    public int solution(int n, int k) {
        String str = NumeralSystem(n, k);  // 정수 n을 k진수로 변환
        return ExtractNumber(str);  // 진법으로 변환한 수에서 숫자 추출
    }
    
    
    // 1. 정수 n을 k진수로 변환
    public static String NumeralSystem(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(n != 0){
            sb.append(n%k);
            n /= k;
        } return sb.reverse().toString();
    }
    
    
    // 2. 진법으로 변환한 수에서 숫자 추출
    public static int ExtractNumber(String str){
        int cnt = 0;
        String[] numbers = str.split("0");
        
        for(String number : numbers){
            if(!number.isEmpty()){
                long num = Long.parseLong(number);
                if(num != 1 && isPrimeNum(num)) cnt++;
            }
        } return cnt;
    }
    
    
    // 3. 소수 판별
    public static boolean isPrimeNum(long num){
        if(num == 2) return true;
        if(num%2 == 0) return false;
        
        long sqrt = (long)Math.sqrt(num);
        for(long i = 3; i <= sqrt; i += 2){
            if(num % i == 0) return false;
        } return true;
    }
}