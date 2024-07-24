def solution(picks, minerals):
    answer = 0
    total = 0
    
    pick = 0
    for i in picks:
        pick += i
    
    if len(minerals) > pick * 5:
        minerals = minerals[0:(pick*5)+1]

    diamond = {"diamond": 1, "iron": 1, "stone": 1}
    iron = {"diamond": 5, "iron": 1, "stone": 1}
    stone = {"diamond": 25, "iron": 5, "stone": 1}
    
    new_array = []
    temp = []
    for i in minerals:
        temp.append(i)
        if len(temp) == 5:
            new_array.append(temp)
            temp = []
    if temp:
        new_array.append(temp)
    
    for i in new_array:
        temp_score = 0
        for j in i:
            if j == 'diamond':
                temp_score += 100
            elif j == "iron":
                temp_score += 10
            else:
                temp_score += 1
        i.append(temp_score)
        
    new_array.sort(key=lambda x: x[-1], reverse=True)
    
    for i in new_array:
        i.pop()
    
    for group in new_array:
        if picks[0] > 0:
            picks[0] -= 1
            cur = diamond
        elif picks[1] > 0:
            picks[1] -= 1
            cur = iron
        elif picks[2] > 0:
            picks[2] -= 1
            cur = stone
        else:
            break

        for mineral in group:
            answer += cur[mineral]
            total += 1

            if total == len(minerals):
                return answer
    
    return answer
