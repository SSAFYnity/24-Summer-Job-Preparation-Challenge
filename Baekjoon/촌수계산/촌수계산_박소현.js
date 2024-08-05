function calculate(adjList, s, e) {
  const q = [[s, 0]];
  const visited = Array(N + 1).fill(false);
  visited[s] = true;

  while (q.length > 0) {
    let [now, val] = q.shift();

    if (now === e) {
      return val;
    }

    for (const next of adjList[now]) {
      if (!visited[next]) {
        q.push([next, val + 1]);
        visited[next] = true;
      }
    }
  }
  return -1;
}

const filePath = process.platform === "linux" ? "/dev/stdin" : "./2644.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const N = parseInt(input[0]);
const [a, b] = input[1].split(" ").map(Number);
const M = parseInt(input[2]);

let adjList = Array(N + 1)
  .fill()
  .map(() => []);

for (let i = 3; i < M + 3; i++) {
  let [s, e] = input[i].split(" ").map(Number);
  adjList[s].push(e);
  adjList[e].push(s);
}

let ans = calculate(adjList, a, b);
console.log(ans);
