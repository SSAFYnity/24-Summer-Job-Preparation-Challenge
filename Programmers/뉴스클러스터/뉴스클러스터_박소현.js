function makeSlice(str) {
	let map = new Map();
	for (let i = 0; i < str.length - 1; i++) {
			if (str[i].match(/^[a-zA-Z]$/) && str[i + 1].match(/^[a-zA-Z]$/)) {
					let word = (str[i] + str[i + 1]).toLowerCase();
					map.set(word, (map.get(word) || 0) + 1); 
			}
	}
	return map;
}

// 자카드 유사도 : 두 집합의 교집합 크기를 합집합 크기도 나눈 값
// 글자의 경우, 두글자씩 끊어서 다중집합을 만듦. 단, 영문자 외 다른 문자는 그 글자쌍을 버린다
// 대소문자는 무시
function solution(str1, str2) {
	let map1 = makeSlice(str1);
	let map2 = makeSlice(str2);
	
	let isCount = 0;
	let uCount = 0;

	let allKeys = new Set([...map1.keys(), ...map2.keys()]);
	
	
	// 각 키당 합집합과 교집합을 더해가며 구함
	for (let key of allKeys) {
			let count1 = map1.get(key) || 0;
			let count2 = map2.get(key) || 0;
			isCount += Math.min(count1, count2);
			uCount += Math.max(count1, count2);
	}

	let ans = uCount === 0 ? 1 : isCount / uCount;
	return Math.floor(ans * 65536);
}

