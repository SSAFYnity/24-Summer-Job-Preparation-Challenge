import heapq
import sys
input = sys.stdin.readline
n, m = map(int, input().split())
visited = [sys.maxsize for _ in range(n+1)]
arr = [[] for _ in range(n+1)]
for i in range(m):
    a, b = map(int, input().split())
    arr[a].append((i, b))
    arr[b].append((i, a))

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    visited[start] = 0
    while q:
        time, node = heapq.heappop(q)
        if node == n:
            return
        if visited[node] < time:
            continue
        for i, nnode in arr[node]:
            ntime = i+((time-i)//m)*m if (time-i)%m==0 else i+((time-i)//m+1)*m
            if visited[nnode] > ntime+1:
                visited[nnode] = ntime+1
                heapq.heappush(q, (ntime+1, nnode))

dijkstra(1)
print(visited[-1])