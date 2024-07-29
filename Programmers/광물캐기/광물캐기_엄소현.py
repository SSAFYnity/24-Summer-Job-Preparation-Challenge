from itertools import permutations
from sys import maxsize

def solution(picks, minerals):
    mineral_dict = {"diamond": 0, "iron": 1, "stone": 2}
    fatigue = [[1, 1, 1], 
               [5, 1, 1], 
               [25, 5, 1]]
    
    dia = [0] * picks[0]
    iron = [1] * picks[1]
    stone = [2] * picks[2]
    picks = list(permutations(dia+iron+stone))
    min_res = int(maxsize)
    
    for pick in picks:
        picks_idx = 0
        minerals_idx = 0
        res = 0
        
        while picks_idx < len(pick):
            x = pick[picks_idx]
            num = 0
            while minerals_idx < len(minerals) and num < 5:
                y = mineral_dict[minerals[minerals_idx]]
                res += fatigue[x][y]
                minerals_idx += 1
                num += 1

            picks_idx += 1
        
        min_res = min(min_res, res)
        
    return min_res
