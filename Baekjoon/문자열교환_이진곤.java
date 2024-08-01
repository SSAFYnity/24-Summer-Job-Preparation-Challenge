import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열교환_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        int originLen = str.length();

        int bCnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'b') {
                bCnt++;
            }
        }

        int curBCnt = 0;
        str += str.substring(0, bCnt);
        for (char ch : str.substring(0, bCnt).toCharArray()) {
            if (ch == 'b') {
                curBCnt++;
            }
        }
        int maxBCnt = curBCnt;

        for (int i = 1; i < originLen; i++) {
            if (str.charAt(i - 1) == 'b') {
                curBCnt--;
            }
            if (str.charAt(i + bCnt - 1) == 'b') {
                curBCnt++;
            }
            maxBCnt = Math.max(maxBCnt, curBCnt);
        }
        System.out.println(bCnt - maxBCnt);
    }
}