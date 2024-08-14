import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class 매칭 점수_김현진
{

	public int solution(String word, String[] pages) {
		int answer = 0;
		word = word.toLowerCase();
		Matcher mt = null;

		PageInfo[] pageInfos = new PageInfo[pages.length];
		List<String>[] datas = new List[pages.length];

		// 각페이지에 관한 정보 정리
		for (int i = 0; i < pages.length; i++) {
			int score = 0;
			pages[i] = pages[i].toLowerCase();
			datas[i] = new ArrayList<>();
			pageInfos[i] = new PageInfo();

			// 본 페이지 url를 구하는 코드
			mt = Pattern.compile("(<meta property=\"og:url\" content=\"https://(\\S*)\")").matcher(pages[i]);
			while (mt.find()) {
				String name = mt.group(2).trim();
				pageInfos[i].name = name;
			}

			// 본 페이지 basicScore를 구하는 코드
			mt = Pattern.compile("(?<=[^a-zA-Z])(" + word + ")[^a-zA-Z]").matcher(pages[i]);
			while (mt.find()) {
				score += 1;
			}
			pageInfos[i].basicScore = score;

			//// 본 페이지 외부 url를 구하는 코드
			mt = Pattern.compile("<a href=\"(\\S*)//(\\S*)\"").matcher(pages[i]);
			while (mt.find()) {
				String url = mt.group(2).trim();
				datas[i].add(url);
			}
			pageInfos[i].linkedOutPage = datas[i];
			pageInfos[i].outerCnt = datas[i].size();

		}

		// 외부 링크를 계산하여 총점을 구하는 코드
		for (int i = 0; i < pageInfos.length; i++) {

			for (String url : pageInfos[i].linkedOutPage) {
				for (int k = 0; k < pageInfos.length; k++) {
					if (i == k)
						continue;
					if (url.equals(pageInfos[k].name)) {
						pageInfos[k].score += (double) pageInfos[i].basicScore / pageInfos[i].linkedOutPage.size();
					}
				}
			}
		}

		// 최대 스코어의 index를 구하는 코드

		double max = 0;
		for (int i = 0; i < pageInfos.length; i++) {
			if (max < (pageInfos[i].basicScore + pageInfos[i].score)) {
				max = (pageInfos[i].basicScore + pageInfos[i].score);
				answer = i;
			}
		}

		return answer;
	}

	// 각 페이지가 가지고 있는 정보
	class PageInfo {
		// 본 페이지 URL
		String name;
		// 본 페이지가 가지고 있는 외부 URL 갯수
		int outerCnt;
		// 본 페이지가 가지고 있는 기본 Score
		int basicScore;
		// 본 페이지가 가지고 있는 외부 URL
		List<String> linkedOutPage;
		// 기본 Score + 외부 url 계산 score
		double score;

	}
}