package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


// DFS 역시나 시간초과 + dp
public class BaekJoon_1520 {
    static int[][] board;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int row;
    static int column;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        board = new int[row][column];
        for(int i=0; i<row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<column; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[row][column];
        for(int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        System.out.println(dfs(0, 0));
    }

    // 따라서, 한 번 끝점까지 탐색이 완료된 점은 dp를 사용하여 경로의 개수를 저장해 놓고
    // 다른 점에서 또 탐색이 되었을 때는 이 경로를 더해주는 방향으로 로직을 설계
    public static int dfs(int x, int y) {
        if(x == row -1 && y == column - 1) {
            return 1;
        }
        if(dp[x][y] == -1) {
            dp[x][y] = 0;
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isValid(nx, ny) && board[nx][ny] < board[x][y]) {
                    dp[x][y] += dfs(nx, ny);
                }

            }
        }
        return dp[x][y];
    }
    public static boolean isValid(int x, int y) {
        return 0 <= x && x < row && 0 <= y && y < column;
    }
}
