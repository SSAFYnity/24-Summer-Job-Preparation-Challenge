def solution(line):
    # 교점 구하기
    dots = []
    
    for i in range(len(line)-1):
        for j in range(i+1, len(line)):
            a, b, c = line[i][0], line[i][1], line[j][0]
            d, e, f = line[j][1], line[i][2], line[j][2]
            if (a*d-b*c)!=0:
                x = (b*f-e*d)/(a*d-b*c)
                y = (e*c-a*f)/(a*d-b*c)
                if int(x)==x and int(y)==y:
                    dots.append([int(x), int(y)])
                    
    if not dots:
        return []
    
    # 교점 범위 구하기
    max_x = max(dots, key=lambda dot: dot[0])[0]
    min_x = min(dots, key=lambda dot: dot[0])[0]
    max_y = max(dots, key=lambda dot: dot[1])[1]
    min_y = min(dots, key=lambda dot: dot[1])[1]
    
    # 결과 배열 크기 구하기
    len_x = max_x-min_x
    len_y = max_y-min_y
    
    # 별 그리기
    stars = [["."] * (len_x + 1) for _ in range(len_y + 1)]
    for x, y in dots:
        stars[max_y - y][x - min_x] = "*"
    
    # 리스트를 문자열로 변환
    result = ["".join(row) for row in stars]
    return result