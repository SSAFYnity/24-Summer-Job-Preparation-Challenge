def solution(n, k):
    cnt=0
    t = convert(n,k)
    t = convert(n, k)
    words = t.split('0')

    for word in words:
        if word and prime(int(word)):
            cnt += 1
    return cnt

def prime (n):
    if n == 1:
        return False
    for j in str(n):
        if j == "0":
            return False
    for i in range(2,int(n**0.5) + 1):
        if n % i == 0:
            return False

    return True



def convert(n, q):
    num = ''

    while n > 0:
        n, mod = divmod(n, q)
        num += str(mod)

    return num[::-1] 