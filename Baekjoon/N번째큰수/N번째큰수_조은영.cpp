#include <iostream>
#include <set>
using namespace std;


int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	set<int>s;

	int N = 0;
	int input = 0;

	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> input;

			if (s.size() < N) s.insert(input);
			else {
				int minValue = *s.begin();
				if (minValue < input) {
					s.erase(s.begin());
					s.insert(input);
				}
			}
		}
	}

	cout << *s.begin() << "\n";

	return 0;
}