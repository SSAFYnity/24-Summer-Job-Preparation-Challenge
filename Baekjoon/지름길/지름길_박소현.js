const filePath = process.platform === "linux" ? "/dev/stdin" : "./1446.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const [N, D] = input[0].split(" ").map(Number);
const arr = input.slice(1).map((l) => l.split(" ").map(Number));

const dp = Array(D + 1)
  .fill(0)
  .map((_, i) => i);

for (let j = 0; j <= D; j++) {
  if (j > 0) {
    dp[j] = Math.min(dp[j], dp[j - 1] + 1);
  }
  for (let [s, e, val] of arr) {
    // 시작점과 j가 같고, 고속도로길이 안의 범위에서 업데이트
    if (j === s && e <= D) {
      dp[e] = Math.min(dp[e], dp[j] + val);
    }
  }
}

console.log(dp[D]);
