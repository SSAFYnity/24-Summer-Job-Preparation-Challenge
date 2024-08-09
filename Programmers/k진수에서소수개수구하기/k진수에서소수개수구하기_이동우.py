def is_prime(n):
    if n == 1 or n == 4 or n == 6 or n == 8:
        return False
    elif n == 2 or n == 3 or n == 5 or n == 7:
        return True
    elif n % 2 == 0 or n % 3 == 0:
        return False
    else:
        for i in range(3, int(n ** 0.5) + 1, 2):
            if n % i == 0:
                return False
        else:
            return True

def solution(n, k):
    answer = 0
    new_n = ''
    while n:
        new_n += str(n % k)
        n //= k
    n = new_n[::-1] + '0'
    num = ''
    for i in n:
        if i == '0' and num != '':
            if is_prime(int(num)):
                answer += 1
            num = ''
        else:
            num += i
    return answer