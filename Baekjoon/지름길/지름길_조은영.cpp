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

	// 1���� D���� �Ÿ��� 1�� �þ ������ �ּ� �Ÿ� ���ϱ�
	for (int i = 1; i <= D; i++) {
		// �������� ���� ���
		if (v[i].size() == 0) minValue[i] = minValue[i - 1] + 1;
		// �������� �ִ� ���
		else {
			// �������� �������� ��츦 ���
			for (auto value : v[i]) {
				minValue[i] = min(minValue[i], min(minValue[i - 1] + 1, minValue[value.first] + value.second));
			}
		}

	}

	cout << minValue[D] << "\n";

}