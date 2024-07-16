#include <iostream>
#include <vector>
using namespace std;

vector<int>v[100];

int dfs(int x) {

	if (x == 99) return 1;

	int size = v[x].size();

	for (int i = 0; i < size; i++) {
		int y = v[x][i];

		if (dfs(y) == 1) {
			return 1;  // ��ǥ ������ ã�� ��� ���� ȣ�⿡���� 1�� ��ȯ
		}
	}

	return 0;
}

int main() {

	// ������� 0, �������� 99
	// 0���� 99�� ���� ���� �����ϸ� 1, �׷��� ������ 0 ���
	// DFS�� Ǯ���. �ٵ� ������ �ֱ� ������ ��� �����Ҷ� �����ؾ���

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int tc = 10;
	int tcNum = 0, inputNum = 0;
	int a = 0, b = 0;


	while (tc--) {
		cin >> tcNum >> inputNum;

		for (int i = 0; i < 100; i++) v[i].clear();

		for (int i = 0; i < inputNum; i++) {
			cin >> a >> b;
			v[a].push_back(b);
		}

		cout << "#" << tcNum << " " << dfs(0) << "\n";
	}

	return 0;
}