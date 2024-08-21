import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        int n = s.length();
        
        for(int i = n; i >= 1; i--){
            for(int j = 0; j <= n-i; j++){
               boolean flag= true;
               int st = j;
               int en = j + i - 1;
               
               while(st < en){ 
                   if(s.charAt(st) != s.charAt(en)){
                       flag = false;
                       break;
                   }
                   st++;
                   en--;
               }
               if(flag) {
                   answer = i;
                   return answer;
               }
            }
               
        }
        return answer;
    }
}