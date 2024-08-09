import math
from collections import Counter

def solution(str1, str2):
    
    allow = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    }
    
    test1, test2 = str1.lower(), str2.lower()
    
    multiset1 = Counter([
        test1[_] + test1[_ + 1] 
        for _ in range(len(test1) - 1)
        if test1[_] in allow and 
        test1[_ + 1] in allow 
    ])
    
    multiset2 = Counter([
        test2[_] + test2[_ + 1] 
        for _ in range(len(test2) - 1)
        if test2[_] in allow and 
        test2[_ + 1] in allow 
    ])
    
    sum_inter = sum((multiset1 & multiset2).values())
    sum_union = sum((multiset1 | multiset2).values())
            
    return (
        math.floor(sum_inter / sum_union * 65536) if sum_inter != 0 and sum_union != 0 
        else 0 if (sum_inter != 0 and sum_union == 0) or (sum_inter == 0 and sum_union != 0) 
        else 65536
    )
