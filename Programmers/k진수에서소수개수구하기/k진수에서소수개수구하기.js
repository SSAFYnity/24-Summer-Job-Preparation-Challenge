function isPrime(a) {
  if (a < 2) return false;
  for (let i = 2; i <= Math.sqrt(a); i++) {
    if (a % i === 0) return false;
  }
  return true;
}

function solution(n, k) {
  let answer = 0;
  let num = "";

  // n -> k진수
  while (n > 0) {
    let temp = n % k;
    n = Math.floor(n / k);
    num = temp.toString() + num;
  }

  let numList = num.split("0");

  // 소수 갯수 카운트
  numList.forEach((val) => {
    if (val.length > 0 && isPrime(parseInt(val))) {
      answer += 1;
    }
  });

  return answer;
}
