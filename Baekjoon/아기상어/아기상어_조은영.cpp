#include <iostream>
#include <math.h>
#include <queue>
#include <algorithm>
#define INF 1e9;
using namespace std;

queue<pair<int,int>>q;

int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };
int shark_size = 2, fish_cnt=0;
int arr[20][20] = {};
bool visited[20][20];
int N = 0, ans=0;

struct Position {
	int x, y, dist;
};

Position shark;

bool compare(Position a, Position b) {
	// �Ÿ��� ���� ��� �� ���� �����
	if (a.dist == b.dist) {
		// x���� ������ ������ �����
		if (a.x == b.x) return a.y < b.y;
		return a.x < b.x;
	}
	return a.dist < b.dist;
}

bool isRange(int x, int y) {
	if (x >= 0 && x < N && y >= 0 && y < N) return true;
	return false;
}

bool bfs() {

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			visited[i][j] = false;
		}
	}
	queue<Position>q;
	vector<Position>fishes;

	q.push({ shark.x, shark.y, 0 });
	visited[shark.x][shark.y] = true;

	while (!q.empty()) {

		Position cur = q.front();
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nextX = dx[i] + cur.x;
			int nextY = dy[i] + cur.y;

			if (isRange(nextX, nextY) && !visited[nextX][nextY]) {
				// �̵��� �� ���� ��쿡�� ������ �����ϱ� �湮�� �õ������� ��� trueó��
				visited[nextX][nextY] = true;

				// ���� ���ų� ���� ��쿡�� �̵�
				if (arr[nextX][nextY] <= shark_size) {
					q.push({ nextX, nextY, cur.dist+1 });
					
					// ���� ���� �۰� 0�� �ƴϸ� ����
					if (arr[nextX][nextY] < shark_size && arr[nextX][nextY]>0) {
						fishes.push_back({ nextX, nextY, cur.dist + 1 });
					}

				}
			}
		}
	}

	// ���� ����Ⱑ ������ ����
	if (fishes.empty()) return false;

	sort(fishes.begin(), fishes.end(), compare);

	// ���� �켱���� �����
	Position target = fishes[0];

	// ����� �Ա�
	arr[shark.x][shark.y] = 0;
	shark.x = target.x;
	shark.y = target.y;
	fish_cnt++;
	ans += target.dist;

	if (fish_cnt == shark_size) {
		shark_size++;
		fish_cnt = 0;
	}

	return true;

}


int main() {

	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 9) {
				shark.x = i;
				shark.y = j;
				shark.dist = 0;
				arr[i][j] = 0;
			}
		}
	}

	while (bfs());

	cout << ans << "\n";

	return 0;
}