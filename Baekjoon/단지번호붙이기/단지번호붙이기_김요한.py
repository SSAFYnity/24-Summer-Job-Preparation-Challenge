import sys

ein = sys.stdin.readline

N = int(ein().strip())
arr = list(list(map(int, ein().strip()))
           for _ in range(N))

chart = dict()

dr, dc = [0, 0, -1, 1], [-1, 1, 0, 0]

n = 0
for r in range(N):
    for c in range(N):
        if arr[r][c] == 1:
            arr[r][c] = 0
            chart[n] = [(r, c)]
            stack = [(r, c)]
            # dfs
            while stack:
                cr, cc = stack.pop()
                for d in range(4):
                    nr, nc = cr + dr[d], cc + dc[d]
                    if 0 <= nr < N and 0 <= nc < N:
                        if arr[nr][nc] == 1:
                            arr[nr][nc] = 0
                            chart[n].append((nr, nc))
                            stack.append((nr, nc))
            n += 1

print(len(chart))
for _ in [len(_) for _ in sorted(
        [chart[_] for _ in range(len(chart))],
        key=len)]:
    print(_)

