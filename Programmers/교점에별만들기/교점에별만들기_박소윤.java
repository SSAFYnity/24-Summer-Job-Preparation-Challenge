import java.util.*;

class Solution {
    
    List<long[]> intersections = new ArrayList<>();
    long[] min = { Long.MAX_VALUE, Long.MAX_VALUE };
    long[] max = { Long.MIN_VALUE, Long.MIN_VALUE };
    
    public String[] solution(int[][] line) {
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                if (isParallel(line[i], line[j])) {
                    continue;
                }
                long[] intersection = getIntersection(line[i], line[j]);
                if (intersection != null) {
                    intersections.add(intersection);
                    updateMinMax(intersection);
                }
            }
        }
        int maxX = (int)(max[0] - min[0] + 1);
        int maxY = (int)(max[1] - min[1] + 1);
        String[] answer = new String[maxY];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ".".repeat(maxX);
        }
        for (long[] intersection : intersections) {
            int x = (int)(intersection[0] + min[0] * -1);
            int y = (int)(intersection[1] + min[1] * -1);
            char[] cur = answer[y].toCharArray();
            cur[x] = '*';
            answer[y] = String.valueOf(cur);
        }
        
        return answer;
    }
    
    private void updateMinMax(long[] newIntersection) {
        min[0] = Math.min(min[0], newIntersection[0]);
        min[1] = Math.min(min[1], newIntersection[1]);
        max[0] = Math.max(max[0], newIntersection[0]);
        max[1] = Math.max(max[1], newIntersection[1]);
    }
    
    private long[] getIntersection(int[] line1, int[] line2) {
        
        double x = (double) ((long)line1[1] * line2[2] - (long)line1[2] * line2[1]) / 
            ((long)line1[0] * line2[1] - (long)line1[1] * line2[0]);
        double y = (double) ((long)line1[2] * line2[0] - (long)line1[0] * line2[2]) /
            ((long)line1[0] * line2[1] - (long)line1[1] * line2[0]);
        
        if (isInteger(x) && isInteger(y)) {
            return new long[]{(long)x, (long)y * -1};
        }
        return null;
    }
    
    private boolean isParallel(int[] line1, int[] line2) {
        
        return ((long)line1[0] * line2[1] - (long)line1[1] * line2[0] == 0);
    }
    
    private boolean isInteger(double num) {
        
        return (num % 1 == 0);
    }
}