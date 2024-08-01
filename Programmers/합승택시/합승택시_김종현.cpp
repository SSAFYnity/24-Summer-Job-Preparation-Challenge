#include <string>
#include <vector>
#include <queue>
#include <iostream>
#include <algorithm>
using namespace std;
int ans, s1[201],s2[201],s3[201];
const int INF = 2e8+4;
vector<pair<int,int>> list[201];

void dijstra(int st, int n, int s[]) {
    priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq;
    pq.push({0, st});
    fill(s+1, s+n+1, INF);
    s[st]=0;
    while(!pq.empty()) {
        pair<int,int> curr = pq.top();
        pq.pop();
        for(pair<int,int> next : list[curr.second]) {
            if(s[next.second] > curr.first+next.first) {
                s[next.second] = curr.first+next.first;
                pq.push({s[next.second], next.second});
            }
        }
    }
   
}

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = 0;
    for(int i=0; i<fares.size(); i++) {
        int st = fares[i][0];
        int ed = fares[i][1];
        int dist = fares[i][2];
        list[st].push_back({dist,ed});
        list[ed].push_back({dist,st});
    }
    
    dijstra(s,n,s1);
    dijstra(a,n,s2);
    dijstra(b,n,s3);
    
    answer = INF;
    for(int d=1; d<=n; d++) {
        int sum = s1[d] + s2[d] + s3[d];    
        if(sum < answer) answer = sum;
    }
    return answer;
}