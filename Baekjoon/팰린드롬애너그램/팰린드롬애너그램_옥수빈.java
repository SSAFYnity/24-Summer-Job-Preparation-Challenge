import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (N % 2 == 1 && i == N / 2)
                continue;
            map.putIfAbsent(S.charAt(i), 0);
            map.computeIfPresent(S.charAt(i), (k, v) -> v + 1);
        }

        for (char key : map.keySet()) {
            if (map.get(key) % 2 == 1) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}