import java.util.HashMap;
import java.util.Map;

class 뉴스클러스터링_강민정 {
    public int solution(String str1, String str2) {
        double answer = 0;

        Map<String, Integer> str1Map = makeMap(str1);	// 문자열 str1을 두 글자씩 끊어서 다중집합을 만든 후 빈도 수 카운팅
        Map<String, Integer> str2Map = makeMap(str2);	// 문자열 str2를 두 글자씩 끊어서 다중집합을 만든 후 빈도 수 카운팅
        Map<String, Integer> intersection = new HashMap<>();		// 교집합
        Map<String, Integer> union = new HashMap<>(str1Map);		// 합집합

        for (Map.Entry<String, Integer> entry : str2Map.entrySet()) {
            String key = entry.getKey();		// 단어
            int countInStr2 = entry.getValue();
            int countInStr1 = str1Map.getOrDefault(key, 0);	// 문자열 str2에 있는 단어가 문자열 str1에 있으면 값 반환, 없으면 기본으로 0을 반환

            if (countInStr1 > 0) {		// 교집합이 있을 때
                intersection.put(key, Math.min(countInStr1, countInStr2));		// 교집합에 추가
            }

            union.put(key, Math.max(countInStr1, countInStr2));		// 합집합 계산(빈도 수가 더 큰 값을 넣기)
        }

        int intersectionSize = intersection.values().stream().mapToInt(Integer::intValue).sum();		// 교집합 크기
        int unionSize = union.values().stream().mapToInt(Integer::intValue).sum();		// 합집합 크기

        if (unionSize == 0) {		// 분모가 0이면
            answer = 65536;
        } else {
            answer = (double) intersectionSize / unionSize * 65536;		// 자카드 유사도
        }

        return (int) answer;  // 두 문자열의 자카드 유사도
    }

    public Map<String, Integer> makeMap(String str) {
        Map<String, Integer> result = new HashMap<>();		// 단어 : 빈도 수

        for (int i = 0; i < str.length() - 1; i++) {		// 시작 인덱스
            char first = str.charAt(i);
            char second = str.charAt(i + 1);

            if (Character.isLetter(first) && Character.isLetter(second)) {		// 영문자로 이루어진 단어
                String word = ("" + first + second).toLowerCase();		// 대소문자를 구분하지 않기 위해 소문자로 변환함
                result.put(word, result.getOrDefault(word, 0) + 1);		// 기존에 등장한 단어는 빈도 수가 1 증가함
            }
        }

        return result;
    }
}
