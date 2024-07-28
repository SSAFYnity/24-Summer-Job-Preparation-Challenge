package 광물캐기;
import java.util.ArrayList;

public class 광물캐기_신유진 {
    // static int[] picks = { 1, 3, 2 }; // 다이아, 철, 돌
    // static String[] minerals = { "diamond", "diamond", "diamond", "iron", "iron",
    // "diamond", "iron", "stone" };
    static int[] picks = { 0, 1, 1 }; // 다이아, 철, 돌
    static String[] minerals = { "diamond", "diamond", "diamond", "diamond",
            "diamond", "iron", "iron", "iron", "iron", "iron", "diamond" };

    static class Info implements Comparable<Info> {
        int idx;
        int diaRes;
        int ironRes;
        int stoneRes;

        Info(int idx, int diaRes, int ironRes, int stoneRes) {
            this.idx = idx;
            this.diaRes = diaRes;
            this.ironRes = ironRes;
            this.stoneRes = stoneRes;
        }

        @Override
        public int compareTo(Info o) {
            return o.stoneRes - this.stoneRes;
        }

        @Override
        public String toString() {
            return this.idx + ": " + this.diaRes + ": " + this.ironRes + ": " + this.stoneRes;
        }
    }

    public static void main(String[] args) {
        ArrayList<Info> mineralsArr = new ArrayList<>();
        int size = Math.min(picks[0] + picks[1] + picks[2] - 1, (minerals.length - 1) / 5);
        for (int i = 0; i <= size; i++) {
            int diaSum = 0;
            int ironSum = 0;
            int stoneSum = 0;
            for (int j = i * 5; j < i * 5 + 5; j++) {
                if (j == minerals.length)
                    break;

                String value = minerals[j];
                if (value == "diamond") {
                    diaSum += 1;
                    ironSum += 5;
                    stoneSum += 25;
                } else if (value == "iron") {
                    diaSum += 1;
                    ironSum += 1;
                    stoneSum += 5;
                } else {
                    diaSum += 1;
                    ironSum += 1;
                    stoneSum += 1;
                }
            }
            mineralsArr.add(new Info(i, diaSum, ironSum, stoneSum));
        }

        int answer = 0;
        int pickIdx = 0;
        int mineralsIdx = 0;
        // for (Vertex vertex : arr) {
        while (true) {
            if (picks[pickIdx]-- > 0) {
                if (pickIdx == 2) {
                    answer += mineralsArr.get(mineralsIdx++).stoneRes;
                } else if (pickIdx == 1) {
                    answer += mineralsArr.get(mineralsIdx++).ironRes;
                } else {
                    answer += mineralsArr.get(mineralsIdx++).diaRes;
                }
            }
            System.out.println("startIdx: " + pickIdx + ", answer: " + answer);

            if (picks[pickIdx] <= 0)
                pickIdx++;
            if (pickIdx > 2 || mineralsIdx >= mineralsArr.size())
                break;
        }
        System.out.println(answer);
    }
}
