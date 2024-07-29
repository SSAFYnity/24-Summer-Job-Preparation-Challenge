import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solve(s));
    }

    public static int solve(String s) {
        int n = s.length();
        int aCount = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                aCount++;
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int bCount = 0;
            for (int j = i; j < i + aCount; j++) {
                if (s.charAt(j % n) == 'b') {
                    bCount++;
                }
            }
            min = Math.min(min, bCount);
        }

        return min;
    }
}
