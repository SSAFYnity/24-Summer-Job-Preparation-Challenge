import sys
from collections import deque



dx = [-1,1,0,0]
dy= [0,0,-1,1]

n = int(input())
graph = []
for i in range(n):
    graph.append(list(map(int, input())))


cntarr = []



def bfs(x,y):
    q = deque()
    q.append((x,y))
    graph[x][y] = 0
    tmpCnt =1
    while q:
        x,y= q.popleft()
        for i in range(4):
            tmpx = x+dx[i]
            tmpy = y+dy[i]
            if tmpx >=0 and tmpx <n and tmpy>=0 and tmpy <n:
                if graph[tmpx][tmpy] == 1:
                    graph[tmpx][tmpy] = 0
                    q.append((tmpx,tmpy))
                    tmpCnt+=1
            else: continue
    return tmpCnt

for i in range(n):
    for j in range(n):
        if graph[i][j] == 1:
            cntarr.append(bfs(i,j))



cntarr.sort()

print(len(cntarr))

for i in range(len(cntarr)):
    print(cntarr[i])


