import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        for(int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                int start = i;
                int end = j;

                if (answer >= (end - start + 1)) break;

                boolean res = true;
                while(start <= end) {
                    if(s.charAt(start) == s.charAt(end)) {
                        start++;
                        end--;
                    } else {
                        res = false;
                        break;
                    }
                }

                if (res) {
                    answer = Math.max(answer, j - i + 1);
                }
            }
        }
        return answer;
    }
}
