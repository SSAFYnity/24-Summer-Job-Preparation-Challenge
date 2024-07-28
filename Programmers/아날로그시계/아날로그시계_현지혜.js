function solution(h1, m1, s1, h2, m2, s2) {
    let answer = 0;
    let start = h1*3600 + m1*60 + s1; // 시작시간 초단위
    let end = h2*3600 + m2*60 + s2; // 종료시간 초단위

    function getDegree(h, m, s) { // 시침, 분침, 초침 초당 각도 계산
        const hDegree = (h % 12) * 30.0 + m * 0.5 + s * (1 / 120.0);
        const mDegree = m * 6.0 + s * 0.1;
        const sDegree = s * 6.0;
        return [hDegree, mDegree, sDegree];
    }
    
    function hourMatch(now,next){ // 시침, 초침 겹치는 지 체크
        if(now[0] > now[2] && next[0] <= next[2]){
            return true;
        }
        if (now[2] === 354 && now[0] > 354){
            return true;
        }
        return false;
    }
    function minMatch(now,next){ // 분침, 초침 겹치는 지 체크
        if(now[1] > now[2] && next[1] <= next[2]){
            return true;
        }
        if (now[2] === 354 && now[1] > 354){
            return true;
        }
        return false;
    }
    
    for (let i = start; i< end; i++){
        const now = getDegree(Math.floor(i/3600)%12, Math.floor((i%3600)/60), i%60);
        const next = getDegree(Math.floor((i+1)/3600)%12, Math.floor(((i+1)%3600)/60), (i+1)%60);
        
        const hourResult = hourMatch(now, next); // 시침 겹쳤으면 true : false
        const minResult = minMatch(now, next) // 분침 겹쳤으면 true : false

        if (hourResult && minResult){
            if (next[0] === next[1]) answer++; // 각도가 같으면 answer +1
            else answer += 2; // 각도 다르면 answer +2
        }
        else if (hourResult || minResult) answer ++; // 둘 중 하나만 체크되면 answer +1
    }
    if(start ===0 || start === 43200) answer ++; // 시작시간 0시 또는 12시면 +1
    return answer;
    
    
    
    return answer;
}