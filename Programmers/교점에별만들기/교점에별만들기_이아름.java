import java.io.*;
import java.util.*;

class Solution {

    public String[] solution(int[][] line) {

        ArrayList<long[]> list = new ArrayList<>();

        long maxX = Long.MIN_VALUE, minX = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE, minY = Long.MAX_VALUE;

        for(int i = 0; i < line.length - 1; i++){
            for(int j = i + 1; j < line.length; j++){

                long a = line[i][0], b = line[i][1], e = line[i][2],
                        c = line[j][0], d = line[j][1], f = line[j][2];

                long xTop = b * f - e * d;
                long yTop = e * c - a * f;
                long bottom = a * d - b * c;

                if(bottom == 0) continue;

                if(xTop % bottom == 0 && yTop % bottom == 0){
                    long xx = xTop / bottom;
                    long yy = yTop / bottom;
                    list.add(new long[]{yy, xx});

                    maxX = Math.max(maxX, xx);
                    maxY = Math.max(maxY, yy);
                    minX = Math.min(minX, xx);
                    minY = Math.min(minY, yy);
                }
            }
        }

        int sizeX = (int)(maxX - minX + 1);
        int sizeY = (int)(maxY - minY + 1);

        char[][] res = new char[sizeY][sizeX];

        for(int i = 0; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++){
                res[i][j] = '.';
            }
        }

        for(long[] i : list){
            int mx = (int)(i[1] - minX);
            int my = (int)(maxY - i[0]);
            res[my][mx] = '*';
        }

        String[] ans = new String[sizeY];
        for(int i = 0; i < ans.length; i++){
            ans[i] = new String(res[i]);
        }

        return ans;
    }
}