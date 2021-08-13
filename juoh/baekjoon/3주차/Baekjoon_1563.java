package baekjoon_python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Baekjoon_1563 {
    final static int DIV = 1000000;
    // dp[날짜][지각횟수][연속결석횟수]
    static int[][][] dp = new int[1001][2][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int day = Integer.parseInt(br.readLine());
        System.out.println(solution(day));
    }
    public static int solution(int day) {
        dp[1][0][0] = dp[1][1][0] = dp[1][0][1] = 1;
        for(int i=2; i<day + 1; i++) {
            // n번째 날 개근상 조건인 사람들의 조건
            // 0번 지각 0 or 1 or 2번 연속 결석
            // 1번 지각 0 or 1 or 2번 연속 결석
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % DIV;
            dp[i][0][1] = (dp[i-1][0][0]) % DIV;
            dp[i][0][2] = (dp[i-1][0][1]) % DIV;
            dp[i][1][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]
                          + dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % DIV;
            dp[i][1][1] = (dp[i-1][1][0]) % DIV;
            dp[i][1][2] = (dp[i-1][1][1]) % DIV;
        }
        return (dp[day][0][0] + dp[day][0][1] + dp[day][0][2]
                + dp[day][1][0] + dp[day][1][1] + dp[day][1][2]) % DIV;
    }
}
