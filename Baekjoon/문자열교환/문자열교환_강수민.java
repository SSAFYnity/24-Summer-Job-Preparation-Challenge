import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] arr = br.readLine().toCharArray();
        int ac = 0;
        for (char c : arr) {
            if (c == 'a') {
                ac++;
            }
        }

        int min = 1001;
        for (int i = 0; i < arr.length; i++) {
            int cnt = 0;
            for (int j = i; j < i + ac; j++) {
                if (arr[j % arr.length] == 'b') {
                    cnt++;
                }
            }
            min = Math.min(min, cnt);
        }

        bw.write(Integer.toString(min));
        bw.flush();
    }
}