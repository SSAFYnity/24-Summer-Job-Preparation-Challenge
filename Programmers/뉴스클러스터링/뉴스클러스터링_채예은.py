import math


def make_set(s):
    result = []
    for i in range(len(s)-1):
        el = s[i:i+2]
        if el.isalpha():
            result.append(el.upper())
    return result

def solution(str1, str2):
    set1, set2 = make_set(str1), make_set(str2)
    if len(set1) + len(set2) == 0:
        return 65536
    
    el_count = {}
    for el in set1:
        el_count[el] = el_count.get(el, 0) + 1
    
    inter, union = 0, len(set1)
    for el in set2:
        if el_count.get(el, 0):
            inter += 1
            el_count[el] -= 1
        else:
            union += 1
    
    answer = math.trunc(inter/union * 65536)
    return answer
