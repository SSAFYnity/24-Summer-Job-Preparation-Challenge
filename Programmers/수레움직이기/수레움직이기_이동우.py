from collections import deque
import copy

def solution(maze):
    answer = 0

    n = len(maze)
    m = len(maze[0])
    red_visited = [[False] * m for _ in range(n)]
    blue_visited = [[False] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if maze[i][j] == 1:
                red = [i, j]
                red_visited[i][j] = True
            elif maze[i][j] == 2:
                blue = [i, j]
                blue_visited[i][j] = True
            elif maze[i][j] == 5:
                red_visited[i][j] = True
                blue_visited[i][j] = True

    ways = [[0, 1], [0, -1], [1, 0], [-1, 0]]
    finished = False
    queue = deque([[0, [True, red, red_visited], [True, blue, blue_visited]]])
    while queue and not finished:
        move, red_info, blue_info = queue.popleft()

        if red_info[0] and blue_info[0]:
            _, red, red_visited = red_info
            _, blue, blue_visited = blue_info
            for ri, rj in ways:
                for bi, bj in ways:
                    rri = red[0] + ri
                    rrj = red[1] + rj
                    bbi = blue[0] + bi
                    bbj = blue[1] + bj
                    if rri < 0 or rri >= n:
                        continue
                    if rrj < 0 or rrj >= m:
                        continue
                    if bbi < 0 or bbi >= n:
                        continue
                    if bbj < 0 or bbj >= m:
                        continue
                    if rri == bbi and rrj == bbj:
                        continue
                    if rri == blue[0] and rrj == blue[1] and bbi == red[0] and bbj == red[1]:
                        continue
                    if red_visited[rri][rrj] or blue_visited[bbi][bbj]:
                        continue
                    r_visited = copy.deepcopy(red_visited)
                    b_visited = copy.deepcopy(blue_visited)
                    r_visited[rri][rrj] = True
                    b_visited[bbi][bbj] = True
                    if maze[rri][rrj] == 3 and maze[bbi][bbj] == 4:
                        finished = True
                        answer = move + 1
                    elif maze[rri][rrj] == 3:
                        queue.append([move + 1, [False, [rri, rrj], r_visited], [True, [bbi, bbj], b_visited]])
                    elif maze[bbi][bbj] == 4:
                        queue.append([move + 1, [True, [rri, rrj], r_visited], [False, [bbi, bbj], b_visited]])
                    else:
                        queue.append([move + 1, [True, [rri, rrj], r_visited], [True, [bbi, bbj], b_visited]])

        elif red_info[0]:
            _, red, red_visited = red_info
            _, blue, blue_visited = blue_info
            for ri, rj in ways:
                rri = red[0] + ri
                rrj = red[1] + rj
                if rri < 0 or rri >= n:
                    continue
                if rrj < 0 or rrj >= m:
                    continue
                if rri == blue[0] and rrj == blue[1]:
                    continue
                if red_visited[rri][rrj]:
                    continue
                r_visited = copy.deepcopy(red_visited)
                r_visited[rri][rrj] = True
                if maze[rri][rrj] == 3:
                    finished = True
                    answer = move + 1
                else:
                    queue.append([move + 1, [True, [rri, rrj], r_visited], [False, blue, b_visited]])

        elif blue_info[0]:
            _, red, red_visited = red_info
            _, blue, blue_visited = blue_info
            for bi, bj in ways:
                bbi = blue[0] + bi
                bbj = blue[1] + bj
                if bbi < 0 or bbi >= n:
                    continue
                if bbj < 0 or bbj >= m:
                    continue
                if bbi == red[0] and bbj == red[1]:
                    continue
                if blue_visited[bbi][bbj]:
                    continue
                b_visited = copy.deepcopy(blue_visited)
                b_visited[bbi][bbj] = True
                if maze[bbi][bbj] == 4:
                    finished = True
                    answer = move + 1
                else:
                    queue.append([move + 1, [False, red, red_visited], [True, [bbi, bbj], b_visited]])

    return answer