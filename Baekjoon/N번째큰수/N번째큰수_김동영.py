import heapq

N = int(input())

MAP = [list(map(int, input().split())) for _ in range(N)]

lst = []

for i in range(N):
    for j in range(N):
        if len(lst) < N:
            heapq.heappush(lst, MAP[i][j])
        else:
            if MAP[i][j] > lst[0]:
                heapq.heappushpop(lst, MAP[i][j])

print(lst[0])