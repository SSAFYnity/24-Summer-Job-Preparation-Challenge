import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        Map<String, Integer> unionMap = new HashMap<>();
        Map<String, Integer> intersectionMap = new HashMap<>();

        List<Character> charList = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            if (!charList.contains(str1.charAt(i)) || !charList.contains(str1.charAt(i + 1)))
                continue;
            if (!map1.containsKey(str1.substring(i, i + 2)))
                map1.put(str1.substring(i, i + 2), 1);
            else
                map1.put(str1.substring(i, i + 2), map1.get(str1.substring(i, i + 2)) + 1);
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            if (!charList.contains(str2.charAt(i)) || !charList.contains(str2.charAt(i + 1)))
                continue;
            String string = str2.substring(i, i + 2);
            if (!map2.containsKey(str2.substring(i, i + 2)))
                map2.put(str2.substring(i, i + 2), 1);
            else
                map2.put(str2.substring(i, i + 2), map2.get(str2.substring(i, i + 2)) + 1);
        }

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                intersectionMap.put(key, Math.min(map1.get(key), map2.get(key)));
                unionMap.put(key, Math.max(map1.get(key), map2.get(key)));
            } else {
                unionMap.put(key, map1.get(key));
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                unionMap.put(key, map2.get(key));
            }
        }

        int unionSize = 0;
        for (String key : unionMap.keySet()) {
            unionSize += unionMap.get(key);
        }
        int intersectionSize = 0;
        for (String key : intersectionMap.keySet()) {
            intersectionSize += intersectionMap.get(key);
        }
        
        if(unionSize == 0 && intersectionSize == 0)
            return 65536;

        int answer = unionMap.size() == 0 ? 0 : (intersectionSize * 65536) / unionSize;
        return answer;
    }
}