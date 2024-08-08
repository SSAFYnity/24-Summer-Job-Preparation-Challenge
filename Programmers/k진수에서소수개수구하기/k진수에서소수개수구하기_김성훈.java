import java.util.*;

class Solution {

    public int solution(int n, int k) {

        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            sb.append(n % k);
            n /= k;
        }
        sb.reverse();

        char[] nums = sb.toString().toCharArray();
        sb.setLength(0);

        int count = 0;
        for (char num : nums) {
            if (num == '0') {
                if (sb.length() == 0) continue;
                long x = Long.parseLong(sb.toString());
                if (isPrime(x)) count++;
                sb.setLength(0);
            } else {
                sb.append(num);
            }
        }

        if (sb.length() != 0 && isPrime(Long.parseLong(sb.toString()))) count++;

        return count;
    }

    public boolean isPrime(long x) {
        if (x == 1) return false;
        int sqrt = (int)Math.sqrt(x);
        for (int i = 2; i <= sqrt; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}