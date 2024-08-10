import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
class Solution {
    public int solution(String str1, String str2) {
        Queue<String> str1List = new PriorityQueue<>();
        Character pre = str1.charAt(0);
        for (int i = 1; i < str1.length(); i++) {
            Character cur = str1.charAt(i);
            if (((pre >= 'A' && pre <= 'Z') || (pre >= 'a' && pre <= 'z')) && ((cur >= 'A' && cur <= 'Z') || (cur >= 'a'
                    && cur <= 'z'))) {
                str1List.add(String.valueOf(pre).toLowerCase() + String.valueOf(cur).toLowerCase());
            }
            pre = cur;
        }

        Queue<String> str2List = new PriorityQueue<>();
        pre = str2.charAt(0);
        for (int i = 1; i < str2.length(); i++) {
            Character cur = str2.charAt(i);
            if (((pre >= 'A' && pre <= 'Z') || (pre >= 'a' && pre <= 'z')) && ((cur >= 'A' && cur <= 'Z') || (cur >= 'a'
                    && cur <= 'z'))) {
                str2List.add(String.valueOf(pre).toLowerCase() + String.valueOf(cur).toLowerCase());
            }
            pre = cur;
        }

        double intersection = 0;
        double union = 0;
        while (!str1List.isEmpty()) {
            if (str2List.isEmpty())
                break;
            String value1 = str1List.peek();
            String value2 = str2List.peek();

            if (value1.equals(value2)) {
                intersection++;
                union++;
                str1List.poll();
                str2List.poll();
            } else if (value1.compareTo(value2) < 0) {
                union++;
                str1List.poll();
            } else {
                union++;
                str2List.poll();
            }

        }

        union += str1List.size();
        union += str2List.size();

        if (intersection == 0 && union == 0)
            return 65536;

        return (int) ((intersection / union) * 65536);
    }
}