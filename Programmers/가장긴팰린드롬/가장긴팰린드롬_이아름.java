import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 1;
        int N = s.length();
        for (int i = 0; i < N; i++) {
            int left = i, right = i + 1;
            while (left >= 0 && right < N) {
                if (s.charAt(left) == s.charAt(right)) {
                    answer = Math.max(right - left + 1, answer);
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            int left = i - 1, right = i + 1;
            while (left >= 0 && right < N) {
                if (s.charAt(left) == s.charAt(right)) {
                    answer = Math.max(right - left + 1, answer);
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }

        return answer;
    }
}