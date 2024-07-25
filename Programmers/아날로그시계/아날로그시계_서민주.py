def solution(h1, m1, s1, h2, m2, s2):
    answer = 0
    start_time = h1 * 3600 + m1 * 60 + s1
    end_time = h2 * 3600 + m2 * 60 + s2
    
    # 시계는 12시간마다 반복되므로, 시간을 12시간 범위로 한정
    total_seconds = 12 * 3600
    
    # 포함되지 않는 시작시간 00시, 12시는 미리 카운팅하기
    if start_time%total_seconds==0 or start_time%total_seconds==12*3600:
        answer += 1
    
    
    while start_time<end_time:
        # 1초마다 움직이는 각도 게산하기
        h_angle = (start_time / 120) % 360
        m_angle = (start_time / 10) % 360
        s_angle = (start_time * 6) / 360
        
        # 다음 시간의 각도를 계산
        h_next = ((start_time + 1) / 120) % 360
        m_next = ((start_time + 1) / 10) % 360
        s_next = ((start_time + 1) * 6) % 360
        
        if s_angle<h_angle and s_next>=h_next:
            answer += 1
        if s_angle<m_angle and s_next>=m_next:
            answer += 1
            
        # 중복 카운팅 제외
        if s_next == h_next and h_next == m_next:
            answer -= 1
        
        start_time += 1
    return answer