package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9663 {

    /**
     * solved.ac
     * 백준
     * 9653
     * N-QUEEN
     * 골드 5
     * 아이디어 :
     * 열 값을 하나씩 증가시키면서 해당 열에 가능한 노드일 경우 다음 열로 진행
     * 이전 열 상태는 볼 필요 없이 전진하는 방향의 대각선 및 행(row)만 확인하면 됨.
     **/

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static final int Max = 15;
    static int[] arr;
    static int result = 0;

    public static void main(String[] args) throws IOException {

        // 1. input
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        nQueen(0);

        System.out.println(result);


    }

    public static void nQueen(int col) {
        // 모든 열을 다 채웠으면 result 값 증가시키고 return
        if (col == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[col] = i;
            // 놓을 수 있는 위치일 경우 재귀호출
            if (Possibility(col)) {
                nQueen(col + 1);
            }
        }

    }

    public static boolean Possibility(int col) {

        for (int i = 0; i < col; i++) {
            // 해당 열의 행과 i열의 행이 일치할경우 (같은 행에 존재할 경우)
            if (arr[col] == arr[i]) {
                return false;
            }

            /*
             * 대각선상에 놓여있는 경우
             * (열의 차와 행의 차가 같을 경우가 대각선에 놓여있는 경우다)
             */
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }

        return true;
    }




}
