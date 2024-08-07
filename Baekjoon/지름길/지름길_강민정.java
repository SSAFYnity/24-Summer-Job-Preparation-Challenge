import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

class 지름길_강민정 {
    static class Shortcut {
        int start;      // 시작 위치
        int end;        // 도착 위치
        int distance;   // 거리

        public Shortcut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInput = br.readLine().split(" ");
        int n =  Integer.parseInt(firstInput[0]);       // 지름길의 개수 <= 12
        int d = Integer.parseInt(firstInput[1]);        // 고속도로의 길이 <= 10,000
        List<Shortcut> shortcuts = new ArrayList();     // 지름길 리스트
        int[] dp = new int[d + 1];          // 위치 별 최소 거리를 저장하는 DP
        int idx = 0;        // 지름길 리스트 shortcuts의 인덱스

        Arrays.fill(dp, Integer.MAX_VALUE);         // 배열 초기화
        dp[0] = 0;

        for(int i=0; i<n; i++) {        // 지름길의 정보 입력 받기
            String[] inputArr = br.readLine().split(" ");
            int start = Integer.parseInt(inputArr[0]);      // 시작 위치
            int end = Integer.parseInt(inputArr[1]);        // 도착 위치
            int distance = Integer.parseInt(inputArr[2]);   // 지름길 길이

            shortcuts.add(new Shortcut(start, end, distance));      // 지름길 추가
        }

        Collections.sort(shortcuts, Comparator.comparingInt(x -> x.start));     // 시작점을 기준으로 지름길 리스트를 오름차순 정렬

        for(int i=0; i<d+1; i++) {
            if (i > 0) {        // 인덱스가 음수가 되는 상황을 방지
                dp[i] = Math.min(dp[i - 1] + 1, dp[i]);         // 이전 위치에서 한 칸 갈 것인지, 지름을 이용했을 경우를 고를지
            }
            while(idx < n && shortcuts.get(idx).start == i) {        // 리스트의 길이를 넘지 않는 idx이고, 지름길의 시작점에 왔다면
                if(shortcuts.get(idx).end <= d) {     // 지름길을 타고 도착한 지점이 도착해야 하는 지점보다 크다면
                    dp[shortcuts.get(idx).end] = Math.min(dp[shortcuts.get(idx).end], shortcuts.get(idx).distance + dp[i]);     // 지름길을 이용하지 않을 것인가, 할 것인가
                }
                idx++;      // 그 다음 지름길을 보기 위해 인덱스 증가, shortcuts.get(idx).end > d인 경우 그 다음 지름길 케이스로 넘어감
            }
        }

        System.out.println(dp[d]);          // 도착점 d로 도착하는 최솟값
    }
}