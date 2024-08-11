#include <iostream>
#include <unordered_map>
#include <deque>
using namespace std;

int n, d;
int road[10001];
unordered_map<int, pair<int, int>> shortcut;
int cur = 0, total = 0;
int path[10001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> d;

    for (int i = 0; i < n; i++) {
        int s, e, l;
        cin >> s >> e >> l;

        if (e - s < l || e > d) {
            continue;
        }

        if (shortcut.count(s)) {
            if (e - l > shortcut[s].first - shortcut[s].second) {
                shortcut[s] = {e, l};
            }
            continue;
        }

        shortcut[s] = {e, l};
    }

    deque<int> dq;
    dq.push_back(0);
    path[0] = 1;
    while (!dq.empty()) {
        int cur = dq.front(); dq.pop_front();

        if (cur > d) {
            continue;
        }

        if (shortcut.count(cur)) {
            if (path[shortcut[cur].first] == 0 || path[shortcut[cur].first] > path[cur] + shortcut[cur].second) {
                path[shortcut[cur].first] = path[cur] + shortcut[cur].second;
                dq.push_back(shortcut[cur].first);
            }
        }
        
        if (path[cur + 1] == 0 || path[cur + 1] > path[cur] + 1) {
            path[cur + 1] = path[cur] + 1;
            dq.push_back(cur + 1);
        }
    }
    
    cout << (path[d] - 1);

    return 0;
}