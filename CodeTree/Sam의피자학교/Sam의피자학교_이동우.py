import copy

# 도우 누르는 함수
def press_dough(dough):
    add_f = [[0] * len(dough[0]) for _ in range(len(dough))]
    for i in range(len(dough)):
        for j in range(len(dough[0])):
            if i and dough[i - 1][j] and dough[i][j]:
                if dough[i][j] > dough[i - 1][j]:
                    add_f[i - 1][j] -= (dough[i][j] - dough[i - 1][j]) // 5
                    add_f[i][j] += (dough[i][j] - dough[i - 1][j]) // 5
                else:
                    add_f[i - 1][j] += (dough[i - 1][j] - dough[i][j]) // 5
                    add_f[i][j] -= (dough[i - 1][j] - dough[i][j]) // 5
            if j and dough[i][j - 1] and dough[i][j]:
                if dough[i][j] > dough[i][j - 1]:
                    add_f[i][j - 1] -= (dough[i][j] - dough[i][j - 1]) // 5
                    add_f[i][j] += (dough[i][j] - dough[i][j - 1]) // 5
                else:
                    add_f[i][j - 1] += (dough[i][j - 1] - dough[i][j]) // 5
                    add_f[i][j] -= (dough[i][j - 1] - dough[i][j]) // 5
    for i in range(len(dough)):
        for j in range(len(dough[0])):
            dough[i][j] -= add_f[i][j]
    spread_dough = []
    for j in range(len(dough[0])):
        for i in range(len(dough) - 1, -1, -1):
            if dough[i][j]:
                spread_dough.append(dough[i][j])
    return spread_dough

n, k = map(int, input().split())

# 도우를 말았을때 좌표값을 알아내는 과정
i = 0
p = 1
arr = []
while True:
    i += p
    if i <= n:
        arr.append(p)
    if i > n:
        arr[-1] += n - i + p
        break
    i += p
    if i <= n:
        arr.append(p)
    if i > n:
        arr[-1] += n - i + p
        break
    p += 1
roll = [[0] * arr[-1] for _ in range(arr[-2] + 1)]
point = [arr[-2], arr[-1] - 1]
ways = [[0, -1], [-1, 0], [0, 1], [1, 0]]
way_i = 0
arr_i = -1
origin_n = n
while n:
    roll[point[0]][point[1]] = n
    n -= 1
    arr[arr_i] -= 1
    if not arr[arr_i]:
        arr_i -= 1
        way_i += 1
        if way_i == 4:
            way_i = 0
    point[0] += ways[way_i][0]
    point[1] += ways[way_i][1]
n = origin_n

# 여기부터 도우 만들기
dough = list(map(int, input().split()))
task = 0
while True:
    # 최대최소확인
    if max(dough) - min(dough) <= k:
        break
    task += 1

    # 1. 밀가루 넣기
    min_f = min(dough)
    for i in range(n):
        if dough[i] == min_f:
            dough[i] += 1

    # 2. 말기
    roll_dough = copy.deepcopy(roll)
    for i in range(len(roll)):
        for j in range(len(roll[0])):
            if roll_dough[i][j]:
                roll_dough[i][j] = dough[roll_dough[i][j] - 1]

    # 3. 누르기
    dough = press_dough(roll_dough)

    # 4. 두번 접기
    fold_dough = [[0] * (n // 4) for _ in range(4)]
    x = 0
    for i in [2, 1, 0, 3]:
        if i % 2:
            for j in range(n // 4):
                fold_dough[i][j] = dough[x]
                x += 1
        else:
            for j in range(n // 4 - 1, -1, -1):
                fold_dough[i][j] = dough[x]
                x += 1

    # 5. 누르기
    dough = press_dough(fold_dough)

print(task)