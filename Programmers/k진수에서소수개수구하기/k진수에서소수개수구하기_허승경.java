class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        // 1. n -> k
        String kNum = Long.toString(n, k);
        String [] arr = kNum.split("0");
        String tmp = "";

        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals("")) continue;
            if(isPrimeNumber(arr[i])) answer ++;
        }

        return answer;
    }

    boolean isPrimeNumber(String n){
        long num = Long.parseLong(n);
        if(num == 1){
            return false;
        }

        if(num == 2) return true;

        for(int i=2;i<=(int)Math.sqrt(num);i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}