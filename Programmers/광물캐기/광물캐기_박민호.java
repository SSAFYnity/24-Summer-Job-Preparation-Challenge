class Solution {
    public static int[][] caculate = {{1,1,1}, {5,1,1}, {25,5,1}};
    public int solution(int[] picks, String[] minerals) {
        int answer = Integer.MAX_VALUE;
        int temp;
        if(picks[0]>0){
            temp = greedy(minerals, 0, 0, picks[0]-1, picks[1], picks[2], 0, answer);
            answer = (answer<=temp) ? answer : temp;
        }
        if(picks[1]>0){
            temp = greedy(minerals, 1, 0, picks[0], picks[1]-1, picks[2], 0, answer);
            answer = (answer<=temp) ? answer : temp;
        }
        if(picks[2]>0){
            temp = greedy(minerals, 2, 0, picks[0], picks[1], picks[2]-1, 0, answer);
            answer = (answer<=temp) ? answer : temp;
        }
        return answer;
    }
    public int greedy(String[] minerals, int currentPick, int count, int dia, int iron, int stone, int currentAnswer, int min){
        int size = minerals.length;
        if(count==size){
            return (min<currentAnswer) ? min : currentAnswer;
        }
        switch(minerals[count]){
            case "diamond":
                currentAnswer += caculate[currentPick][0];
                break;
            case "iron":
                currentAnswer += caculate[currentPick][1];
                break;
            case "stone":
                currentAnswer += caculate[currentPick][2];
                break;
        }
        count++;
        int temp;
        if(count%5==0){
            if(dia>0){
                temp = greedy(minerals, 0, count, dia-1, iron, stone, currentAnswer, min);
                min = (min<=temp) ? min : temp;
            }
            if(iron>0){
                temp = greedy(minerals, 1, count, dia, iron-1, stone, currentAnswer, min);
                min = (min<=temp) ? min : temp;
            }
            if(stone>0){
                temp = greedy(minerals, 2, count, dia, iron, stone-1, currentAnswer, min);
                min = (min<=temp) ? min : temp;
            }
        }
        else{
            temp = greedy(minerals, currentPick, count, dia, iron, stone, currentAnswer, min);
            min = (min<=temp) ? min : temp;
        }
        if(dia+iron+stone==0 && count%5==0){
            return (min<=currentAnswer) ? min : currentAnswer;
        }
        return min;
    }
}