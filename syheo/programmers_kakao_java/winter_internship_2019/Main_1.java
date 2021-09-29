package programmers_kakao_java.winter_internship_2019;

import java.util.Stack;

/**
 * 2019 카카오 개발자 겨울 인턴십
 * 크레인 인형뽑기 게임
 * 1:30 - 1:57 2:34 - 2:45, 27+11 = 38분 소요
 * 아이디어 :
 * 각 열의 꼭대기에 있는 인형의 위치를 colPoint 저장하고
 * moves[]에 맞게 인형을 하나하나 뽑으면서 위치를 한칸씩 밑으로 내려준다.
 * 바구니는 Stack 자료구조를 사용하여 저장한다.
 */

public class Main_1 {

    static int[] colPoint;
    static int maxValue;
    static Stack<Integer> stack = new Stack<>();

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;

        // 1. set colPoint size
        maxValue = board.length;
        colPoint = new int[board.length];

        // 2. set colPoint value
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[j][i]!=0){
                    colPoint[i] = j;
                    break;
                }
            }
        }

        // 3. move
        for (int i = 0; i < moves.length; i++) {
            // 가리키는 열에 인형이 남아 있고, 바구니에 인형이 있고, 바구니와 뽑은 인형이 일치할 경우
            if(colPoint[moves[i]-1]!=maxValue && stack.size()>=1 && stack.peek()==board[colPoint[moves[i]-1]][moves[i]-1]){
                stack.pop();
                answer+=2;
            }
            // 가리키는 열에 인형이 없으면 아무런 일도 일어나지 않는다.
            else if(colPoint[moves[i]-1]==maxValue){
                continue;
            }
            // 바구니에 인형 담기
            else{
                stack.add(board[colPoint[moves[i]-1]][moves[i]-1]);
            }
            // 인형 하나 제거
            colPoint[moves[i]-1]++;
        }

        return answer;
    }

    public static void main(String[] args) {
        // expect value : 4
        System.out.println(solution(new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}},new int[]{1,5,3,5,1,2,1,4}));
    }
}
