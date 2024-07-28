#include <string>
#include <vector>
#include <math.h>
#define INF 100000000 // 너무 적게 해주면 효율성 26번 통과 안됨

using namespace std;

// 노드 개수, 출발노드, a의 도착지점, b의 도착지점, [지점1, 지점2, 사이의 요금]
int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = INF;

    vector<vector<int>>v;

    v.assign(n + 1, vector<int>(n + 1, INF));

    for (int i = 0; i < fares.size(); i++) {
        v[fares[i][0]][fares[i][1]] = fares[i][2];
        v[fares[i][1]][fares[i][0]] = fares[i][2];
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (i == j) v[i][j] = 0;
        }
    }

    // 최단거리 갱신
    for (int k = 1; k <= n; k++) {
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (v[a][k] + v[k][b] < v[a][b]) {
                    v[a][b] = v[a][k] + v[k][b];
                }

            }
        }
    }

    for (int i = 1; i <= n; i++) {
        answer = min(answer, v[s][i] + v[i][a] + v[i][b]);
    }

    return answer;
}