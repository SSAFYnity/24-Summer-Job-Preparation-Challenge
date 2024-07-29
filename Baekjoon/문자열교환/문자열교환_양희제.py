# 문자열교환 - 양희제

import sys
from collections import deque
input = sys.stdin.readline


def count_a(word):
    cnt_a = 0
    for i in range(len(word)):
        if word[i] == "a":
            cnt_a += 1

    return cnt_a


sentence = input().rstrip()
answer = 1000

# a의 개수 세기
cnt_a = count_a(sentence)

if cnt_a == 0: print(0)
else:
    sentence += sentence[:cnt_a - 1]
    
    for i in range(len(sentence) - cnt_a + 1):
        answer = min(answer, cnt_a - count_a(sentence[i:i + cnt_a]))

    print(answer)