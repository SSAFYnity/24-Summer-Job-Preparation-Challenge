N = int(input())
apart = []
for _ in range(N):
    apart.append(list(map(int, list(input()))))
just = []
for i in range(N):
    for j in range(N):
        if apart[i][j]:
            stack = [[i,j]]
            apart[i][j] = 0
            n = 1
            while stack:
                now = stack.pop()
                move = [[now[0],now[1]-1], [now[0],now[1]+1], [now[0]+1,now[1]], [now[0]-1,now[1]]]
                for m in move:
                    if 0 <= m[0] < N and 0 <= m[1] < N and apart[m[0]][m[1]]:
                        n += 1
                        apart[m[0]][m[1]] = 0
                        stack.append(m)
            just.append(n)
just.sort()
print(len(just))
for n in just:
    print(n)