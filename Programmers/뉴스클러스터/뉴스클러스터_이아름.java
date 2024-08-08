import java.io.*;
import java.util.*;

class Solution {

    public int solution(String str1, String str2) {
        double answer = 0;
        ArrayList<String> A = getStringArray(str1);
        ArrayList<String> B = getStringArray(str2);
        if (A.size() > B.size()) {
            answer = jarcard(A, B);
        } else {
            answer = jarcard(B, A);
        }

        return (int) (answer * 65536);
    }

    static ArrayList<String> getStringArray(String str) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            char first = str.charAt(i);
            char second = str.charAt(i + 1);
            if ((first >= 'a' && first <= 'z' || first >= 'A' && first <= 'Z') &&
                    (second >= 'a' && second <= 'z' || second >= 'A' && second <= 'Z')) {
                String temp = String.valueOf(first);
                temp += second;
                res.add(temp.toUpperCase());
            }
        }
        return res;
    }

    static double jarcard(ArrayList<String> A, ArrayList<String> B) {
        double union = 0;
        double intersection = 0;
        if (A.size() == 0 && B.size() == 0) return 1;
        out:
        for (int i = A.size() - 1; i >= 0; i--) {
            for (int j = B.size() - 1; j >= 0; j--) {
                String a = A.get(i);
                String b = B.get(j);
                if (A.remove(b)) {
                    B.remove(j);
                    union++;
                    continue out;
                }
            }
            if (B.isEmpty()) {
                intersection++;
            } else {
                intersection += 2;
                B.remove(0);
            }
        }
        return union / (intersection + union);
    }
}