class Solution {
    public int solution(int n, int k) {
        // 진수 변환 & 0으로 구분
        String[] ss = Integer.toString(n, k).split("0+");

        // 소수 판별
        int answer = 0;

        for (String s : ss) {
            boolean flag = true;
            Long num = Long.parseLong(s);

            if (num < 2) continue;

            int max = (int)Math.sqrt(num);
            for (int i = 2; i <= max; i++) {
                if (num % i == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) answer++;
        }

        return answer;
    }
}