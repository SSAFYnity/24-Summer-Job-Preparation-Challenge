import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int[] alpha = new int['z' - 'a' + 1];
        int half = N / 2;
        for (int i = 0; i < N; i++) {
            if (N % 2 != 0 && half == i) continue;
            alpha[s.charAt(i) - 'a']++;
        }
        boolean check = true;
        for (int j : alpha) {
            if (j % 2 != 0) {
                check = false;
                break;
            }
        }
        if (check) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}