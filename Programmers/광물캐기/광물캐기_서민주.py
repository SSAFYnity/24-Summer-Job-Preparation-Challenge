def solution(picks, minerals):
    def dfs(level, ans):
        global answer
        if level == max_level:
            answer = min(answer, ans)
            return
        for i in range(3):
            if picked[i] > 0:
                picked[i] -= 1
                dfs(level + 1, ans + total[i][level])
                picked[i] += 1

    # 곡괭이로 광물을 캘 때 증가하는 피로도 배열
    hp = [[1, 1, 1], [5, 1, 1], [25, 5, 1]]
    n = len(minerals)
    
    # 피로도 증가량 저장 배열
    total = [[] for _ in range(3)]
    picked = picks[:]
    
    for i in range(3):
        for j in range(0, n, 5):
            result = 0
            for k in range(j, min(j+5, n)):
                if minerals[k] == "diamond":
                    result += hp[i][0]
                elif minerals[k] == "iron":
                    result += hp[i][1]
                elif minerals[k] == "stone":
                    result += hp[i][2]
            total[i].append(result)
    
    max_level = (n + 4) // 5
    max_level = min(max_level, sum(picks))
    global answer
    answer = 1e9
    dfs(0, 0)
    return answer