# 진수 변환하기 (target = 변환해야 할 수, num = 변환해야 할 진수)
def convert(target, num):
    answer = ""
    while target>num:
        a, b = target//num, target%num
        answer += str(b)
        target = a
    
    answer += str(a)
    
    return int(answer[::-1])

# 소수인지 판별하는 함수
def is_prime(num):
    if num==1:
        return False
    elif num==2:
        return True
    elif num==3:
        return True
    for i in range(2, int(num**0.5)+1):
        if num%i==0:
            return False
    return True
    
def solution(n, k):
    answer = 0
    ans = convert(n, k)
    res = list(str(ans).split("0"))
    
    for i in range(len(res)):
        if res[i]!='' and is_prime(int(res[i])):
            answer += 1
            
    return answer