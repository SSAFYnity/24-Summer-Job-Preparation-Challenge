string = input()

cnt = 0
answer = float("inf")

for i in string:
    if i == "a":
        cnt += 1

for i in range(len(string)):
    b_cnt = 0
    plus = 1
    while plus <= cnt:
        if string[(i + plus) % len(string)] == "b":
            b_cnt += 1
        plus += 1
    answer = min(b_cnt, answer)

print(answer)