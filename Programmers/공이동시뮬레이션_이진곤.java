class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long xStart = 0;
        long xEnd = n - 1;
        long yStart = 0;
        long yEnd = m - 1;
        long xStartOver = 0;
        long xEndOver = 0;
        long yStartOver = 0;
        long yEndOver = 0;

        for (int[] query : queries) {
            int cmd = query[0];
            int dist = query[1];

            switch (cmd) {
                case 0: // y-
                    yStart -= dist;
                    yEnd -= dist;
                    if (yStart < 0) {
                        yStartOver += Math.abs(yStart);
                        yStart = 0;
                    }
                    if (yEnd < 0) {
                        yEnd = 0;
                    }
                    break;
                case 1: // y+
                    yStart += dist;
                    yEnd += dist;
                    if (yEnd >= m) {
                        yEndOver += yEnd - (m - 1);
                        yEnd = m - 1;
                    }
                    if (yStart >= m) {
                        yStart = m - 1;
                    }
                    break;
                case 2: // x-
                    xStart -= dist;
                    xEnd -= dist;
                    if (xStart < 0) {
                        xStartOver += Math.abs(xStart);
                        xStart = 0;
                    }
                    if (xEnd < 0) {
                        xEnd = 0;
                    }
                    break;
                case 3: // x+
                    xStart += dist;
                    xEnd += dist;
                    if (xEnd >= n) {
                        xEndOver += xEnd - (n - 1);
                        xEnd = n - 1;
                    }
                    if (xStart >= n) {
                        xStart = n - 1;
                    }
                    break;
            }
        }

        if (xStart <= x && x <= xEnd && yStart <= y && y <= yEnd) {
            long xResult = 1;
            long yResult = 1;

            if (x == xStart) xResult += xStartOver;
            if (x == xEnd) xResult += xEndOver;
            if (y == yStart) yResult += yStartOver;
            if (y == yEnd) yResult += yEndOver;

            if (xResult > n) xResult = n;
            if (yResult > m) yResult = m;

            return xResult * yResult;
        }
        return 0;
    }
}