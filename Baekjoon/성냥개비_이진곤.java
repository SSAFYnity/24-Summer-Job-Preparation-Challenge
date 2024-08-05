import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 성냥개비_이진곤 {
    static String[] max = new String[101];
    static NumString[] min = new NumString[101];

    public static void main(String[] args) throws IOException {
        getNumString();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(in.readLine());
            sb.append(min[num].str).append(" ").append(max[num]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void getNumString() {
        max[2] = "1";
        max[3] = "7";
        for (int i = 4; i <= 100; i++) {
            max[i] = max[i-2] + "1";
        }

        min[0] = new NumString("");
        min[1] = new NumString("99999999999999999999");
        for (int i = 2; i <= 100; i++) {
            if (i >= 7) {
                NumString numString = new NumString(min[i - 7].str + "8");
                if (min[i] == null || min[i].compareTo(numString) > 0) {
                    min[i] = numString;
                }
            }
            if (i >= 6) {
                NumString numString;
                if (min[i - 6].len >= 1) {
                    numString = new NumString(min[i - 6].str + "0");
                }
                else {
                    numString = new NumString(min[i - 6].str + "6");
                }
                if (min[i] == null || min[i].compareTo(numString) > 0) {
                    min[i] = numString;
                }
            }
            if (i >= 5) {
                NumString numString = new NumString(min[i - 5].str + "2");
                if (min[i] == null || min[i].compareTo(numString) > 0) {
                    min[i] = numString;
                }
            }
            if (i >= 4) {
                NumString numString = new NumString(min[i - 4].str + "4");
                if (min[i] == null || min[i].compareTo(numString) > 0) {
                    min[i] = numString;
                }
            }
            if (i >= 3) {
                NumString numString = new NumString(min[i - 3].str + "7");
                if (min[i] == null || min[i].compareTo(numString) > 0) {
                    min[i] = numString;
                }
            }
            if (i >= 2) {
                NumString numString = new NumString(min[i - 2].str + "1");
                if (min[i] == null || min[i].compareTo(numString) > 0) {
                    min[i] = numString;
                }
            }
        }
    }

    static class NumString implements Comparable<NumString> {
        String str;
        int len;

        public NumString(String str) {
            this.str = str;
            this.len = str.length();
        }

        @Override
        public int compareTo(NumString other) {
            if (this.len == 0) return 1;
            if (other.len == 0) return -1;
            if (this.len == other.len) {
                for (int i = 0; i < this.len; i++) {
                    if (str.charAt(i) != other.str.charAt(i)) {
                        return this.str.charAt(i) - other.str.charAt(i);
                    }
                }
                return 0;
            }
            return this.len - other.len;
        }
    }
}