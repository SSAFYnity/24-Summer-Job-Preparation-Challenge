import java.util.*;

class Solution {
    public boolean isAlphabet(char c) {
        return 'a' <= c && 'z' >= c || 'A' <= c && 'Z' >= c;
    }
    public int solution(String str1, String str2) {
        int answer = 65536;
        Map<String, Integer> one = new HashMap<>();
        Map<String, Integer> two = new HashMap<>();
        
        for (int i = 0; i < str1.length()-1; i++) {
            if (isAlphabet(str1.charAt(i)) && isAlphabet(str1.charAt(i+1))) {
                String s = str1.substring(i, i+2).toUpperCase();
                one.put(s, one.getOrDefault(s, 0)+1);
            }
        }
        for (int i = 0; i < str2.length()-1; i++) {
            if (isAlphabet(str2.charAt(i)) && isAlphabet(str2.charAt(i+1))) {
                String s = str2.substring(i, i+2).toUpperCase();
                two.put(s, two.getOrDefault(s, 0)+1);
            }
        }
        
        int gyo = 0;
        int hap = 0;
        
        for (String s : two.keySet()) {
            if (one.containsKey(s)) {
                gyo += Math.min(one.get(s), two.get(s));
            }
            hap += Math.abs(one.getOrDefault(s, 0) - two.get(s));
        }
        for (String s : one.keySet()) {
            if (two.containsKey(s)) continue;
            hap += one.get(s);
        }
        hap += gyo;        
        
        if (one.size() == 0 && two.size() == 0) return answer;
        
        answer *= gyo;
        answer /= hap;
        
        return answer;
    }
}