# N번째 큰 수

N = int(input())
numbers = [list(map(int, input().split())) for _ in range(N)]
idx_list = [N - 1] * N

for i in range(N):
    max_number = 0
    max_num_idx = -1
    for idx, num_idx in enumerate(idx_list):
        if num_idx == -1:
            continue

        number = numbers[num_idx][idx]
        if max_number < number:
            max_number = number
            max_num_idx = idx

    idx_list[max_num_idx] -= 1

else:
    print(max_number)
