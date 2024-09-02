import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 산타의선물공장2_이진곤 {
    static int n;
    static int m;
    static int[] root;
    static int[] tail;
    static int[] prevs;
    static int[] nexts;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(in.readLine());
        n = -1;
        m = -1;
        for (int q = 0; q < Q; q++) {
            String[] str = in.readLine().split(" ");
            int cmd = Integer.parseInt(str[0]);
            switch (cmd) {
                case 100: // 공장 설립
                    setup(str);
                    break;
                case 200: // 물건 모두 옮기기
                    System.out.println(moveAll(str));
                    break;
                case 300: // 앞 물건만 교체하기
                    System.out.println(tradeFirst(str));
                    break;
                case 400: // 물건 나누기
                    System.out.println(divideGift(str));
                    break;
                case 500: // 선물 정보 얻기
                    System.out.println(giftInfo(str));
                    break;
                case 600: // 벨트 정보 얻기
                    System.out.println(beltInfo(str));
                    break;
            }
        }
    }

    static void setup(String[] str) {
        n = Integer.parseInt(str[1]);
        m = Integer.parseInt(str[2]);
        ArrayList<Integer>[] beltList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            beltList[i] = new ArrayList<>();
        }

        root = new int[n + 1];
        tail = new int[n + 1];
        cnt = new int[n + 1];
        prevs = new int[m + 1];
        nexts = new int[m + 1];
        Arrays.fill(prevs, -1);
        Arrays.fill(nexts, -1);
        for (int i = 1; i <= m; i++) {
            int beltIdx = Integer.parseInt(str[2 + i]);
            beltList[beltIdx].add(i);
        }

        for (int i = 1; i <= n; i++) {
            if (beltList[i].isEmpty()) {
                continue;
            }

            root[i] = beltList[i].get(0);
            tail[i] = beltList[i].get(beltList[i].size() - 1);
            cnt[i] = beltList[i].size();

            for (int j = 0; j < beltList[i].size(); j++) {
                if (j > 0) {
                    prevs[beltList[i].get(j)] = beltList[i].get(j - 1);
                }
                if (j < beltList[i].size() - 1) {
                    nexts[beltList[i].get(j)] = beltList[i].get(j + 1);
                }
            }
        }
    }

    static int moveAll(String[] str) {
        int src = Integer.parseInt(str[1]);
        int dst = Integer.parseInt(str[2]);

        if (cnt[src] != 0) {
            if (cnt[dst] == 0) {
                root[dst] = root[src];
                tail[dst] = tail[src];
            }
            else {
                int r = root[dst];
                root[dst] = root[src];
                int srcLast = tail[src];
                nexts[srcLast] = r;
                prevs[r] = srcLast;
            }
            root[src] = tail[src] = 0;

            cnt[dst] += cnt[src];
            cnt[src] = 0;
        }
        return cnt[dst];
    }

    static int removeRoot(int num) {
        int r = -1;

        if (cnt[num] == 0) {
            r = 0;
        }
        else if (cnt[num] == 1) {
            r = root[num];
            root[num] = tail[num] = 0;
            cnt[num] = 0;
        }
        else {
            r = root[num];
            int moreHead = nexts[r];
            nexts[r] = prevs[moreHead] = 0;
            cnt[num]--;
            root[num] = moreHead;
        }

        return r;
    }

    static void insertRoot(int num, int r) {
        if (r == 0) {
            return;
        }

        if (cnt[num] == 0) {
            root[num] = tail[num] = r;
            cnt[num] = 1;
        }
        else {
            int oldRoot = root[num];
            nexts[r] = oldRoot;
            prevs[oldRoot] = r;
            root[num] = r;
            cnt[num]++;
        }
    }

    static int tradeFirst(String[] str) {
        int src = Integer.parseInt(str[1]);
        int dst = Integer.parseInt(str[2]);

        int srcRoot = removeRoot(src);
        int dstRoot = removeRoot(dst);
        insertRoot(dst, srcRoot);
        insertRoot(src, dstRoot);
        return cnt[dst];
    }

    static int divideGift(String[] str) {
        int src = Integer.parseInt(str[1]);
        int dst = Integer.parseInt(str[2]);
        int val = cnt[src];
        ArrayList<Integer> moveGift = new ArrayList<>();
        for (int i = 0; i < val / 2; i++) {
            moveGift.add(removeRoot(src));
        }
        for (int i = moveGift.size() - 1; i >= 0; i--) {
            insertRoot(dst, moveGift.get(i));
        }

        return cnt[dst];
    }

    static int giftInfo(String[] str) {
        int giftIdx = Integer.parseInt(str[1]);
        int prevNum = prevs[giftIdx] == 0 ? -1 : prevs[giftIdx];
        int nextNum = nexts[giftIdx] == 0 ? -1 : nexts[giftIdx];
        return prevNum + 2 * nextNum;
    }

    static int beltInfo(String[] str) {
        int beltIdx = Integer.parseInt(str[1]);
        int firstGift = root[beltIdx] == 0 ? -1 : root[beltIdx];
        int lastGift = tail[beltIdx] == 0 ? -1 : tail[beltIdx];
        return firstGift + 2 * lastGift + 3 * cnt[beltIdx];
    }
}