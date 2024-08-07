#include <iostream>
#include <vector>
#include <cmath>
#define MAX 1e9
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0, D = 0;
	int from = 0, to = 0, len = 0;

	cin >> N >> D;

	int minValue[10001];
	vector<pair<int, int>>v[10001];

	fill(minValue, minValue + 10001, MAX);

	for (int i = 0; i < N; i++) {
		cin >> from >> to >> len;
		v[to].push_back({ from, len });
	}

	minValue[0] = 0;

	// 1부터 D까지 거리가 1씩 늘어날 때마다 최소 거리 구하기
	for (int i = 1; i <= D; i++) {
		// 지름길이 없는 경우
		if (v[i].size() == 0) minValue[i] = minValue[i - 1] + 1;
		// 지름길이 있는 경우
		else {
			// 지름길이 여러개인 경우를 대비
			for (auto value : v[i]) {
				minValue[i] = min(minValue[i], min(minValue[i - 1] + 1, minValue[value.first] + value.second));
			}
		}

	}

	cout << minValue[D] << "\n";

}