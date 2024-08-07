from collections import deque

n = int(input())
a, b = map(int, input().split())
m = int(input())
connect = [[] for _ in range(n + 1)]
for _ in range(m):
    x, y = map(int, input().split())
    connect[x].append(y)
    connect[y].append(x)
queue = deque([[0, a]])
visited = [False] * (n + 1)
ans = -1
while queue and ans == -1:
    i, now = queue.popleft()
    visited[now] = True
    for c in connect[now]:
        if c == b:
            ans = i + 1
            break
        if not visited[c]:
            queue.append([i + 1, c])
print(ans)