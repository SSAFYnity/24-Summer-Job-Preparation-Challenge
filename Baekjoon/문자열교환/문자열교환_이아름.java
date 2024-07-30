import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        int a = 0, b = 0;
        int ans = Integer.MAX_VALUE;
        int length = str.length;
        for (char c : str) {
            if (c == 'a') {
                a++;
            }
        }
        if (str[0] == 'b') b++;
        int left = 0, right = 0;
        int len = 1;

        while (left < length) {
            if (len <= a) {
                char r = str[++right % length];
                if (r == 'b') b++;
                len++;
            } else {
                char l = str[left++];
                if (l == 'b') b--;
                len--;
            }

            if (len == a) {
                ans = Math.min(ans, b);
            }
        }
        System.out.println(ans);
    }
}