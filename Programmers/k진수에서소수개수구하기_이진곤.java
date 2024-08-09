class Solution {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }
        sb = sb.reverse();
        String[] nums = sb.toString().split("0");

        int answer = 0;
        loop: for (String str : nums) {
            if (str.isEmpty()) {
                continue;
            }
            long num = Long.parseLong(str);
            if (num == 1) {
                continue;
            }
            for (long i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    continue loop;
                }
            }
            answer++;
        }

        return answer;
    }
}