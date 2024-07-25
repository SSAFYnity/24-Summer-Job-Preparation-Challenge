#include <iostream>
#include <deque>
using namespace std;

int q, n, m;
int head[100001], tail[100001];
int prv[100001], nxt[100001];
int len[100001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> q;
    int c;
    cin >> c >> n >> m;
    for (int i = 1; i <= m; i++) {
        int belt;
        cin >> belt;
        if (head[belt] == 0) {
            head[belt] = i;
        }
        nxt[tail[belt]] = i;
        prv[i] = tail[belt];
        tail[belt] = i;
        len[belt]++;
    }

    for (int i = 0; i < q - 1; i++) {
        int com, a, b;

        cin >> com;
        switch (com) {
            case 200: {
                cin >> a >> b;
                if (len[a] == 0) {
                    cout << len[b] << '\n';
                    break;
                }
                prv[head[b]] = tail[a];
                nxt[tail[a]] = head[b];
                if (head[b] == 0) {
                    tail[b] = tail[a];
                }
                head[b] = head[a];
                head[a] = 0;
                tail[a] = 0;
                len[b] += len[a];
                len[a] = 0;

                cout << len[b] << '\n';
                break;
            }
            case 300: {
                cin >> a >> b;
                int agift = head[a];
                int bgift = head[b];
                if (agift != 0) {
                    head[a] = nxt[head[a]];
                    prv[head[a]] = 0;
                }
                if (bgift != 0) {
                    head[b] = nxt[head[b]];
                    prv[head[b]] = 0;
                }
                
                if (head[a] == 0) {
                    tail[a] = 0;
                }
                if (head[b] == 0) {
                    tail[b] = 0;
                }

                if (bgift != 0) {
                    prv[head[a]] = bgift;
                    nxt[bgift] = head[a];
                    head[a] = bgift;
                    if (tail[a] == 0) {
                        tail[a] = head[a];
                    }
                    len[b]--;
                    len[a]++;
                }
                if (agift != 0) {
                    prv[head[b]] = agift;
                    nxt[agift] = head[b];
                    head[b] = agift;
                    if (tail[b] == 0) {
                        tail[b] = head[b];
                    }
                    len[a]--;
                    len[b]++;
                }
                
                cout << len[b] << '\n';
                break;
            }
            case 400: {
                cin >> a >> b;
                if (len[a] < 2) {
                    cout << len[b] << '\n';
                    break;
                }
                int half = len[a] / 2;
                int cur = head[a];
                for (int j = 1; j < half; j++) {
                    cur = nxt[cur];
                }
                int ahead = nxt[cur];
                prv[ahead] = -1;

                prv[head[b]] = cur;
                nxt[cur] = head[b];
                head[b] = head[a];
                if (tail[b] == 0) {
                    tail[b] = cur;
                }
                head[a] = ahead;

                len[b] += half;
                len[a] -= half;

                cout << len[b] << '\n';
                break;
            }
            case 500: {
                cin >> a;
                int l = (prv[a] == 0 ? -1 : prv[a]);
                int r = (nxt[a] == 0 ? -1 : nxt[a]);

                cout << (l + (2 * r)) << '\n';
                break;
            }
            case 600: {
                cin >> a;
                int first = (head[a] == 0 ? -1 : head[a]);
                int last = (tail[a] == 0 ? -1 : tail[a]);
                int cnt = len[a];

                cout << (first + (2 * last) + (3 * cnt)) << '\n';
                break;
            }
        }
    }
    
    return 0;
}