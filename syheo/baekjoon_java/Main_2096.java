package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * solved.ac
 * 백준
 * 2096
 * ㄴㅐㄹㅕ가기
 * 아이디어 :
 * minDP,maxDP를 이용하여 최솟값 최댓값에 dp 알고리즘 적용
 * 근데 공간 복잡도를 줄이기 위해 그냥 1차원[3] 로 해도 될듯.
 **/

public class Main_2096 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N;
    static int[][] numbers;
    static int[][] dp;
    static int[][] dpMin;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 1. input
        N = Integer.parseInt(br.readLine());

        numbers = new int[N][3];
        dp = new int[N][3];
        dpMin = new int[N][3];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                numbers[i][j] = Integer.parseInt(tokens.nextToken());
                dp[i][j] = numbers[i][j];
                dpMin[i][j] = numbers[i][j];

            }
        }

        // 2. dp

        for (int i = 1; i < N; i++) {
            dp[i][0] += Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] += Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][2]);
            dp[i][2] += Math.max(dp[i-1][1],dp[i-1][2]);
            dpMin[i][0] += Math.min(dpMin[i-1][0],dpMin[i-1][1]);
            dpMin[i][1] += Math.min(Math.min(dpMin[i-1][0],dpMin[i-1][1]),dpMin[i-1][2]);
            dpMin[i][2] += Math.min(dpMin[i-1][1],dpMin[i-1][2]);
        }

        // 3. answer
        answer.append(Math.max(Math.max(dp[N-1][0],dp[N-1][1]),dp[N-1][2]));
        answer.append(" ");
        answer.append(Math.min(Math.min(dpMin[N-1][0],dpMin[N-1][1]),dpMin[N-1][2]));
        System.out.println(answer.toString());

    }
}
