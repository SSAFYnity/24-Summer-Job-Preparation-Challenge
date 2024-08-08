import sys
from collections import deque

input = sys.stdin.readline

INF = int(1e8)

n = int(input())

x,y = map(int,input().split())

m = int(input())


graph = [[] for _ in range(n+1)]
visited = [False] *(n+1)
dis = [INF]*(n+1)

for i in range(m):
    a,b = map(int,input().split())
    graph[a].append((b,1))
    graph[b].append((a,1))

def bfs(start):
    q= deque([start])


    dis[start] = 0

    while q:
        now = q.popleft()



        for next,cost in graph[now]:
            if dis[next] > dis[now] +cost:
                dis[next] = dis[now] + cost
                q.append(next)

bfs(x)

if dis[y] != INF:
    print(dis[y])
else:
    print(-1)

