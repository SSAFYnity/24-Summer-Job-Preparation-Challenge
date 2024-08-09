class Solution {
    public int solution(String word, String[] pages) {
        int answer = -1;
        float maxScore = 0;
        int size = pages.length;
        String[] web = new String[size];
        String[][] links = new String[size][];
        float[] scores = new float[size];
        int[] baseScores = new int[size];
        int[] linkCounts = new int[size];
        for(int i=0;i<size;i++){
        	web[i] = pages[i].split("<meta property=\"og:url\" content=\"")[1].split("\"")[0];
        	String[] tempSearch = pages[i].toLowerCase().split(word.toLowerCase());
        	for(int j=1;j<tempSearch.length;j++) {
        		if(tempSearch[j].length()>0 && !(tempSearch[j].charAt(0) >= 'a' && tempSearch[j].charAt(0) <= 'z')) {
        			int priviousSize = tempSearch[j-1].length();
        			if(priviousSize>0 && !(tempSearch[j-1].charAt(priviousSize-1)>='a' && tempSearch[j-1].charAt(priviousSize-1)<='z')) {
            			baseScores[i]++;
        			}
        		}
        	}
        	scores[i] = Integer.valueOf(baseScores[i]).floatValue();
        	if(scores[i]>maxScore) {
				maxScore = scores[i];
			}
        	links[i] = pages[i].split("<a href=\"");
        	for(int j=0;j<links[i].length;j++) {
        		links[i][j] = links[i][j].split("\"")[0];
        	}
        	linkCounts[i] = links[i].length-1;
        }
        for(int i=0;i<size;i++) {
        	for(int j=1;j<links[i].length;j++) {
        		for(int t=0;t<size;t++) {
        			if(web[t].equals(links[i][j])) {
        				scores[t] += Integer.valueOf(baseScores[i]).floatValue() / Integer.valueOf(linkCounts[i]).floatValue();
        				if(scores[t]>maxScore) {
        					maxScore = scores[t];
        				}
        				break;
        			}
        		}
        	}
        }
        for(int i=0;i<size;i++) {
        	if(scores[i]==maxScore) {
        		answer = i; break;
        	}
        }
        return answer;
    }
}