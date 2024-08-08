function isPrime(num) {
  // 1인 경우 소수가 아님 바로 false return
  if (num === '1') {
    return false
  }
  const primeCandidate = Number(num)
  for (let i = 2; i <= Math.sqrt(primeCandidate); i++) {
    // 나눠지면 소수가 아님 바로 false return
    if (primeCandidate % i === 0) {
      return false
    }
  }
  return true
}


function solution(n, k) {
  const number = n.toString(k)
  // 0이 없는 경우
  if (!(number.includes('0'))) {
    if (isPrime(number)) {
      return 1
    }
  }
  const cntSet = new Set()

  const zeroIdxes = []

  // 0 인덱스 찾기
  for (const idx in number) {
    if (number[idx] === '0') {
      zeroIdxes.push(Number(idx))
    }
  }

  for (const zeroIdx of zeroIdxes) {
    // 앞에 문자 판단
    let frontIdx = zeroIdx - 1
    while (frontIdx >= 0 && number[frontIdx] !== '0') {
      // 만약 해당 숫자를 이미 탐색했으면 끝내기
      if (cntSet.has(`${frontIdx}, ${zeroIdx}`)) {
        break
      }
      // 자신의 왼쪽이 0이거나 빈 경우 소수 판단
      if (frontIdx === 0 || number[frontIdx - 1] === '0') {
        const tempNum1 = number.slice(frontIdx, zeroIdx)
        if (isPrime(tempNum1)) {
          cntSet.add(`${frontIdx}, ${zeroIdx}`)
        }
      }
      frontIdx--
    }
    // 뒤에 문자 판단
    let rearIdx = zeroIdx + 1
    while (rearIdx < number.length && number[rearIdx] !== '0') {
      // 자신의 오른쪽이 0이거나 빈 경우 소수 판단
      if (rearIdx === number.length - 1 || number[rearIdx + 1] === '0') {
        const tempNum2 = number.slice(zeroIdx + 1, rearIdx + 1)
        if (isPrime(tempNum2)) {
          cntSet.add(`${zeroIdx + 1}, ${rearIdx + 1}`)
        }
      }
      rearIdx++
    }
    
  }

  return cntSet.size;
}

console.log(solution(437674, 3))
