import java.util.*;

class Solution {
    
    Map<String, Integer> map1 = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();
    
    public int solution(String str1, String str2) {
        int answer = 0;
        
        makeSubset(map1, str1);
        makeSubset(map2, str2);

        int intersection = 0;
        int union = 0;
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                intersection += Math.min(map1.get(key), map2.get(key));
                union += Math.max(map1.get(key), map2.get(key));
                map2.remove(key);
                continue;
            }
            union += map1.get(key);
        }
        for (Integer value : map2.values()) {
            union += value;
        }
        
        if (union == 0) {
            return 65536;
        }
        return (int) ((double) intersection / union * 65536);
    }
    
    private void makeSubset(Map<String, Integer> map, String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            String part = str.substring(i, i + 2).toLowerCase();
            if (!isLetter(part)) {
                continue;
            }
            map.computeIfPresent(part, (k,v) -> v + 1);
            map.putIfAbsent(part, 1);
        }
    }
    
    private boolean isLetter(String part) {
        return Character.isAlphabetic(part.charAt(0)) && 
            Character.isAlphabetic(part.charAt(1));
    }
}
