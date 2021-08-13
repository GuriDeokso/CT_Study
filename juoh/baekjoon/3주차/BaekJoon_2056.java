package baekjoon_python;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_2056 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int maxValue = Integer.MIN_VALUE;
        for(int i=1; i<n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int precedingCount = Integer.parseInt(st.nextToken());

            dp[i] = time;
            for (int j = 0; j < precedingCount; j++) {
                int preceding = Integer.parseInt(st.nextToken());
                dp[i] = Math.max(dp[i], dp[preceding] + time);
            }
            maxValue = Math.max(maxValue, dp[i]);

        }
        System.out.println(maxValue);
    }

}
