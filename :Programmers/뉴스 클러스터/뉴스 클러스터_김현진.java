import java.util.ArrayList;
import java.util.List;

class 뉴스 클러스터_김현진{

	public int solution(String str1, String str2) {
		List<String> list1 = stringSplit(str1);
		List<String> list2 = stringSplit(str2);

		if (list1.isEmpty() && list2.isEmpty())
			return 65536;

		List<String> union = new ArrayList<>();
		List<String> inter = new ArrayList<>();

		for (String s : list1) {
			if (list2.remove(s))
				inter.add(s);
			union.add(s);
		}
		union.addAll(list2);

		double jacquard = (double) inter.size() / (double) union.size();

		return (int) (jacquard * 65536);
	}

	private List<String> stringSplit(String str) {
		List<String> list = new ArrayList<>();
		str = str.toLowerCase(); // 문자열을 소문자로 변환

		for (int i = 0; i < str.length() - 1; i++) {
			String s = str.substring(i, i + 2).replaceAll("[^a-z]", "");

			if (s.length() < 2)
				continue;
			list.add(s);
		}
		return list;
	}
}