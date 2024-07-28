const t = new Array(3);
t[0] = [1,1,1];
t[1] = [5,1,1];
t[2] = [25,5,1];

const memo = Array.from(Array(3), () => 0);
const slot = new Array(3);

let answer = 2500;

let count = 0;
function solution(picks, minerals) {    
    // console.log(t);
    count = picks.reduce((a,b)=> a+b);
    perm(0,picks, minerals);
    
    return answer;
}

const perm = (cnt, picks, minerals) => {
    if(cnt == count) {
        cal(picks, minerals);  
        return;
    }
    
    for(let i=0; i<3; i++) {
        if(memo[i] < picks[i]) {
            memo[i]++;
            slot[cnt] = i;
            perm(cnt+1, picks, minerals);
            memo[i]--;
        }
    }
}

const cal = (picks, minerals) => {
    let sum =0; 
    
    for(let i=0; i<count; i++) {
        let curr= slot[i];
        for(let j=i*5; j<(i*5)+5; j++) {
            if(j == minerals.length) {
                answer = Math.min(answer, sum);
                return;
            } 
            
            
            if(curr === 0) sum++;
            else if(curr == 1){
                if(minerals[j] === "diamond"){
                    sum += 5;
                }
                else sum++;
            }else{
                if(minerals[j] === "diamond"){
                    sum += 25;
                }
                else if(minerals[j] === "iron"){
                    sum += 5;
                }
                else sum++;
            }
        }
    }

    answer = Math.min(answer, sum);
}