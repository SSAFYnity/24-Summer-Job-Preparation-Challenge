import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열교환_신유진 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String value = br.readLine();
        int aCount = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == 'a')
                aCount++;
        }

        int bCount = 0;
        for (int i = 0; i < aCount; i++) {
            if (value.charAt(i) == 'b')
                bCount++;
        }

        int minRes = bCount;
        String extValue = value + value;
        for (int i = 1; i < value.length(); i++) {
            if (extValue.charAt(i - 1) == 'b')
                bCount--;
            if (extValue.charAt(i + aCount - 1) == 'b')
                bCount++;
            minRes = Math.min(minRes, bCount);
        }

        System.out.println(minRes);
    }
}
