import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                list1.add(str1.substring(i, i + 2));
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                list2.add(str2.substring(i, i + 2));
            }
        }

        Map<String, Integer> count1 = new HashMap<>();
        for (String s : list1) {
            if (count1.containsKey(s)) {
                count1.put(s, count1.get(s) + 1);
            } else {
                count1.put(s, 1);
            }
        }

        Map<String, Integer> count2 = new HashMap<>();
        for (String s : list2) {
            if (count2.containsKey(s)) {
                count2.put(s, count2.get(s) + 1);
            } else {
                count2.put(s, 1);
            }
        }

        if (list1.size() == 0 && list2.size() == 0) {
            return 65536;
        }

        int inter = 0;
        for (String key : count1.keySet()) {
            if (count2.containsKey(key)) {
                inter += Math.min(count1.get(key), count2.get(key));
            }
        }

        int uni = 0;
        Map<String, Integer> map = new HashMap<>(count1);
        for (String key : count2.keySet()) {
            if (map.containsKey(key)) {
                map.put(key, Math.max(map.get(key), count2.get(key)));
            } else {
                map.put(key, count2.get(key));
            }
        }
        for (int value : map.values()) {
            uni += value;
        }

        answer = (int)(((double)inter / uni) * 65536);

        return answer;
    }
}
