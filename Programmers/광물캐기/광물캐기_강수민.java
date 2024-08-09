package lv2;

public class 광물_캐기 {
    private int min;
    private int[][] piro = {
            {1, 1, 1},
            {5, 1, 1},
            {25, 5, 1}
    };

    public int solution(int[] picks, String[] minerals) {

        int[] stones = new int[minerals.length];
        String[] arr = {"diamond", "iron", "stone"};

        for (int i = 0; i < minerals.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (minerals[i].equals(arr[j])) {
                    stones[i] = j;
                    break;
                }
            }
        }

        int pickCnt = 0; // 곡객이 전체 갯수
        for (int i : picks) {
            pickCnt += i;
        }

        min = Integer.MAX_VALUE;
        dfs(picks, stones, 0, 0, pickCnt);

        return min;
    }

    /*
        광석을 캐는 dfs 메서드

        - picks : 남은 곡갱이 수
        - stones : 숫자로 변환한 광석 배열
        - idx : 다음 캘 광석
        - sum : 누적 피로도
    */
    private void dfs(int[] picks, int[] stones, int idx, int sum, int pickCnt) {
        if (idx >= stones.length || pickCnt == 0) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < picks.length; i++) { // 곡갱이 종류별로 써보기

            if (picks[i] == 0) {
                continue; // 0개 남으면 다음 곡갱이
            }

            picks[i]--;

            int j = 0;
            int temp = 0;
            for (; j < 5; j++) {
                if (idx + j < stones.length) { // 광석 배열 인덱스 벗어나지 않았다면 진행
                    temp += piro[i][stones[idx + j]];
                } else {
                    break;
                }
            }
            dfs(picks, stones, idx + j, sum + temp, pickCnt - 1);
            picks[i]++;
        }

    }
}
