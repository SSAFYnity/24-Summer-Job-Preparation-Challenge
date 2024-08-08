const fs = require('fs')
const io = process.platform === "linux" ? "/dev/stdin" : "./input.txt"

function bfs(i, j, N) {
  // 이동 방향
  const dr = [-1, 1, 0, 0]
  const dc = [0, 0, -1, 1]
  const queue = [[i, j]]  // 이동 방향 담을 큐
  apartments[i][j] = 0  // 간 길 표시
  let cnt = 1  // 제출할 cnt

  while (queue.length > 0) {
    const [r, c] = queue.shift()
    for (let idx = 0; idx < 4; idx++) {
      const nr = r + dr[idx]
      const nc = c + dc[idx]
      if (0 <= nr && nr < N && 0 <= nc && nc < N && apartments[nr][nc] === 1) {
        queue.push([nr, nc])
        apartments[nr][nc] = 0
        cnt++
      }
    }
  }
  return cnt
}

const [N, ...arr] = fs.readFileSync(io).toString().trim().split('\n')

const apartments = arr.map((complex) => {
  return complex.trim().split('').map(Number)
})

let complexCnt = 0
const homeCnt = []

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (apartments[i][j] === 1) {
      homeCnt.push(bfs(i, j, N))
      complexCnt++
    }
  }
}

console.log(complexCnt)

// 오름차순 정렬 (JS는 sort를 문자열 취급하여 유니코드 순대로 정렬)
homeCnt.sort(function(a, b) {
  return a - b
})  

for (const number of homeCnt) {
  console.log(number)
} 