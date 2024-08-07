import sys

graph = {_ + 1: dict() for _ in range(int(sys.stdin.readline().strip()))}

a, b = map(int, sys.stdin.readline().strip().split())

for _ in range(int(sys.stdin.readline().strip())):
    x, y = map(int, sys.stdin.readline().strip().split())
    graph[x][y] = graph[y][x] = 1  # 서로 일촌

visited = {a}

# dfs
stack = list(graph[a].items())
not_found = True
while stack:
    c, d = stack.pop()
    if b == c:
        print(d)
        not_found = False
        break
    if c not in visited:
        visited.add(c)
        stack += [(k, v + d) for k, v in graph[c].items()]

if not_found:
    print(-1)
