import sys
input = sys.stdin.readline

INF = int(1e8)

n, d = map(int,input().split())

graph = [[] for _ in range(d+1)]

visited = [False] * (d+1)

distance = [INF] * (d+1)
for i in range(d):
    graph[i].append((i+1, 1))

for _ in range(n):
    a,b,c = map(int,input().split())
    if b > d:
        continue
    graph[a].append((b,c))


def get_min():
    min = INF
    index = 0
    for i in range(d+1):
        if distance[i] < min and not visited[i]:
            min = distance[i]
            index = i
    return index

def dijk(start):
    distance[start] = 0
    visited[start] = False

    for _ in range(d+1):
        now = get_min()
        visited[now] = True
        for j in graph[now]:
            cost = distance[now] + j[1]
            if cost < distance[j[0]]:
                distance[j[0]] = cost

dijk(0)

print(distance[d])



