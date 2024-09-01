import java.util.*;

class Solution {
    static int n, winProb, idx, num;
    static Map<String, Integer> map; 
    static Map<String, String> enemies;
    static TreeSet<Integer> set;
    static String answer;
    static int[][] results;
    
    public int binarySearch(int t, int enemy) {
        int s = 0;
        int e = results[0].length-1;
        int p = (s+e)/2;
        while (s <= e) {
            p = (s+e)/2;
            if (results[enemy][p] < t) s = p+1;
            else if (results[enemy][p] >= t) e = p-1;
        }
        return e;
    }
    
    public void calProb() {
        for (Iterator iter = map.keySet().iterator(); iter.hasNext(); ) {
            String str = (String) iter.next();
            
            String enemy = enemies.get(str);
            int enemyIdx = map.get(enemy);
            int prob = 0;
            for (int a : results[map.get(str)]) {
                prob += binarySearch(a, enemyIdx);
            }
            if (winProb < prob) {
                winProb = prob;
                answer = str;
            }
        }
        
    }
    
    public void makeResult(int[][] dice) {
        StringBuilder sb = new StringBuilder();
        StringBuilder en = new StringBuilder();
        for (int i : set) sb.append(i + " ");
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) en.append(i + " ");
        }
        map.put(sb.toString(), idx);
        enemies.put(sb.toString(), en.toString());
        num = 0;
        select(0, dice, 0, sb.toString());
        Arrays.sort(results[idx]);
        idx++;
    }
    
    public void select(int d, int[][] dice, int summ, String str) {
        if (d >= n/2) {
            results[idx][num++] = summ;
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            select(d + 1, dice, summ + dice[Integer.parseInt(str.split(" ")[d])][i], str);
        }
    }
    
    public void back(int p, int[][] dice) {
        if (set.size() == n/2) {
            makeResult(dice);
            return;
        }
        if (p >= n) return;
        back(p+1, dice);
        set.add(p);
        back(p+1, dice);
        set.remove(p);
        
    }
    
    public int[] solution(int[][] dice) {
        
        n = dice.length;
        winProb = -1;
        idx = 0;
        set = new TreeSet<>();
        int len = 1;
        for (int i = n; i > n/2; i--) len *= i;
        for (int i = n/2; i > 0; i--) len /= i;
        results = new int[len][(int) Math.pow(6, n/2)];
        map = new HashMap<>();
        enemies = new HashMap<>();
        
        back(0, dice);
        calProb();
        
        int[] ansDice = new int[n/2];
        int q = 0;
        for (String s : answer.split(" ")) {
            ansDice[q++] = Integer.parseInt(s) + 1;
        }
        
        
        return ansDice;
    }
}