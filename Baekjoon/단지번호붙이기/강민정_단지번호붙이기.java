import java.io.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class 강민정_단지번호붙이기 {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;       // 5 <= 지도의 크기 <= 25
    static int[][] mapArr;      // 지도
    static boolean[][] visited;     // 방문 배열
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answerCnt = 0;      // 단지 수
        List<Integer> houseLst = new ArrayList();       // 단지 별 집의 수
        n = Integer.parseInt(br.readLine());      // 5 <= 지도의 크기 <= 25
        mapArr = new int[n][n];     // 지도
        visited = new boolean[n][n];
        for(int i=0; i<n; i++) {
            String row = br.readLine();
            for(int j=0; j<n; j++) {
                mapArr[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(mapArr[i][j] == 0 || visited[i][j]) {        // 집이 없거나 방문한 곳
                    continue;
                }
                int result = bfs(new Point(i, j));       // 시작 위치를 넣고 BFS 탐색
                answerCnt++;            // 단지 수 증가
                houseLst.add(result);       // 이 단지 안에 속하는 집의 수를 리스트에 추가
            }
        }

        Collections.sort(houseLst);     // 집의 수를 오름차순 정렬

        System.out.println(answerCnt);      // 총 단지 수 출력
        for(int houseCnt : houseLst) {
            System.out.println(houseCnt);       // 집의 수 출력
        }
    }

    /*
        집이 있는 startPoint를 시작점으로 BFS 탐색
        집의 갯수를 반환함
     */
    public static int bfs(Point startPoint) {
        int result = 1;     // 단지 내에 있는 집의 수
        Queue<Point> que = new LinkedList();
        que.add(startPoint);        // 시작 위치의 좌표를 담은 객체를 큐에 추가
        visited[startPoint.x][startPoint.y] = true;     // 방문 처리

        while(!que.isEmpty()) {     // 큐가 비어있지 않다면
            Point curPoint = que.poll();        // 현재 집의 위치

            for(int d=0; d<4; d++) {     // 사방 탐색
                int nx = curPoint.x + dx[d];
                int ny = curPoint.y + dy[d];
                if(!isValid(nx, ny) || mapArr[nx][ny] == 0 || visited[nx][ny]) {        // 좌표가 지도 밖으로 벗어나거나 집이 없는 곳이거나 방문한 적 있다면
                    continue;
                }
                visited[nx][ny] = true;     // 방문 처리
                que.add(new Point(nx, ny));     // 새로운 집 추가
                result++;       // 집의 갯수 증가
            }
        }

        return result;
    }

    /*
        좌표가 범위 내에 있으면 true를 반환
     */
    public static boolean isValid(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }
}