def get_road(i, now, far):
    global ans
    if i == N:
        if far < ans:
            ans = far
        return
    if roads[i][1] <= D and now <= roads[i][0] and roads[i][2] < roads[i][1] - roads[i][0]:
        get_road(i + 1, roads[i][1], far + roads[i][2] - roads[i][1] + roads[i][0])
    get_road(i + 1, now, far)

N, D = map(int, input().split())
roads = []
for _ in range(N):
    roads.append(list(map(int, input().split())))
roads.sort()
ans = D
get_road(0, 0, D)
print(ans)