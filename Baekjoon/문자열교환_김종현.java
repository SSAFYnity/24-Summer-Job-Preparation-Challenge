import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'a') cnt++;
        }

        int l = 0;
        int r = cnt-1;
        int min = 0;
        for (int i = 0; i <= r; i++) {
            if (str.charAt(i) == 'b') min++;
        }

        int ans = min;
        while (l < str.length()) {
            if (str.charAt(++r % str.length()) == 'b') min++;
            if (str.charAt(l++) == 'b') min--;

            ans = Math.min(ans, min);
        }
        System.out.println(ans);
    }
}
