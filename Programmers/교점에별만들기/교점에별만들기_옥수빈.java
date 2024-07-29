import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<long[]> list = new ArrayList<>();

        long xMin = Long.MAX_VALUE;
        long yMin = Long.MAX_VALUE;
        long xMax = Long.MIN_VALUE;
        long yMax = Long.MIN_VALUE;

        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long[] intersection = 
                    intersect(line[i][0], line[i][1], line[j][0], line[j][1], line[i][2], line[j][2]);
                if (intersection[0] == 1001 || intersection[1] == 1001)
                    continue;
                xMin = Math.min(xMin, intersection[0]);
                xMax = Math.max(xMax, intersection[0]);
                yMin = Math.min(yMin, intersection[1]);
                yMax = Math.max(yMax, intersection[1]);

                list.add(intersection);
            }
        }

        int width = (int) (xMax - xMin + 1);
        int height = (int) (yMax - yMin + 1);
        char[][] arr = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                arr[y][x] = '.';
            }
        }

        for (long[] l : list) {
            int y = (int) (l[1] - yMin);
            int x = (int) (l[0] - xMin);
            arr[y][x] = '*';
        }

        String[] answer = new String[height];
        for (int y = 0; y < height; y++) {
            answer[y] = new String(arr[height - 1 - y]);
        }

        return answer;
    }
    
    public long[] intersect(int a, int b, int c, int d, int e, int f){
        long denominator = (long) a * d - (long) b * c;
        if (denominator == 0)
            return new long[]{1001, 1001};

        long bfed = (long) b * f - (long) e * d;
        long ecaf = (long) e * c - (long) a * f;

        if (bfed % denominator == 0 && ecaf % denominator == 0) {
            long x = bfed / denominator;
            long y = ecaf / denominator;
            return new long[]{x, y};
        }
        return new long[]{1001, 1001};
    }
}