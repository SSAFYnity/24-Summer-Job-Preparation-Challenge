#include <iostream>
#include <queue>
#define MAX 101
using namespace std;

int arr[MAX][MAX];
int visited[MAX];
int n; // 전체 사람 수

void bfs(int k) { 
  queue<int> q;
  q.push(k);
  
  while(!q.empty()) 
  { 
    k = q.front();
    q.pop();
    for(int i=1;i<=n;i++){ 
      if(arr[k][i]!=0 && !visited[i]) {
        q.push(i); 
        visited[i]=visited[k]+1;
      } 
    } 
  } 
}

int main(){
  

  int target_x, target_y; // 타겟
  int m; // 관계의 갯수
  cin >> n;
  cin >> target_x >> target_y;
  cin >> m; 

  for(int i = 0 ; i < m ; i++){
    int x, y;
    cin >> x >> y;
    arr[x][y] = 1; // 관계가 있다는 것을 의미
    arr[y][x] = 1; // 관계가 있다는 것을 의미
  }
  bfs(target_x);
  
if(visited[target_y]==0)
  visited[target_y]=-1; 
  
cout << visited[target_y];

}