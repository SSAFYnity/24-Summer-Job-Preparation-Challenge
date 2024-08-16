import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String row = br.readLine();
        int mid = row.length() / 2;

        int[] value = new int[26];

        for (int i = 0; i < mid; i++) {
            int tmp1 = row.charAt(i) - 'a';
            value[tmp1]++;
            int tmp2 = row.charAt(N - i - 1) - 'a';
            value[tmp2]++;
        }

        String answer = "Yes";
        for (int i = 0; i < 26; i++) {
            if (value[i] % 2 != 0) {
                answer = "No";
                break;
            }
        }
        System.out.println(answer);
    }
}
