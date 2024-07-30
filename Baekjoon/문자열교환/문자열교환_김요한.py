s = input()
# 'a' 갯수 세기
a_cnt = s.count('a')
n = len(s)
# 원형 흉내를 내게끔 s + s
circular_s = s + s
# 'a' 갯수만큼 b 세기
b_cnt = 0
for i in range(a_cnt):
    if circular_s[i] == 'b':
        b_cnt += 1
# 정답 초기화
ans = b_cnt
# 슬라이딩 윈도우 (총 a_cnt의 갯수만큼 윈도우가 있다)
for i in range(n):
    # 윈도우의 첫 번째 문자가 'b'이면 b_cnt 감소
    if circular_s[i] == 'b':
        b_cnt -= 1
    # 윈도우의 마지막 문자가 'b'이면 b_cnt 증가
    if circular_s[i + a_cnt] == 'b':
        b_cnt += 1
    ans = min(ans, b_cnt)
print(ans)