public static int 가장 긴 팰린드롬_김현진(String s) {
	int answer = 1;
	int len = s.length();
	char[] a = s.toCharArray();

	int[][] dp = new int[len][len];

	// 1. 1자리
	for (int i = 0; i < len; i++)
		dp[i][i] = 1;

	// 2. 2자리
	for (int i = 0; i < len-1; i++) {
		if( a[i] == a[i+1] ) {
			dp[i][i + 1] = 1;
			answer = 2;
		}
	}
	// 3. 3자리 이상
	for (int k = 3; k <= len; k++) {
		for (int i = 0; i <= len-k ; i++) {
			int j = i+k-1; // k길이 만큼 떨어진 index
			if( a[i] == a[j] && dp[i+1][j-1] == 1 ) { // 문자열이 같고, [i-1][j+1] 가 팰린드롬이라면
				dp[i][j] = 1;
				answer = Math.max(answer,k);
			}
		}
	}

	return answer;
}