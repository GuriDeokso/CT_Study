package baekjoon_java;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac
 * 백준
 * 1103
 * 게임
 * 골드 2
 * 아이디어 :
 * dfs 를 통해 노드를 방문한다.
 * 노드를 방문할 때 dp[노드 번호]의 값을 설정한다.
 **/

public class Main_1103 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] maps; // #은 -1로 표현하자.
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int row, col;
    static int result = 1;
    static int[][] dp; // dp[i] 는 i 위치에서 방문 할 수 있는 최대 노드 수
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        maps = new int[N][M];

        // 1-1. maps input
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j)-'H'==0) {
                    maps[i][j] = -1;
                } else {
                    maps[i][j] = str.charAt(j) - '0';
                }
            }
        }//for

        visited = new boolean[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i],-1);
        }


        result = dfs(0,0);

        System.out.println(result);
    }

    private static int dfs(int x, int y) {
        // 범위 체크 및 구멍 체크
        if(x<0 || y<0 || x>=N || y>=M || maps[x][y]==-1) return 0;
        // 방문 체크 ( 방문한 적이 있어? 갔던데 또간거야? -> 싸이클이네)
        if(visited[x][y]==true){
            return -1;
        }
        // 이미 한번 재봤던 노드다.
        if (dp[x][y] != -1) return dp[x][y];

        // 방문 처리 및 dp 초기 설정
        visited[x][y] = true;
        dp[x][y] = 0;
        // dfs 순회
        for (int i = 0; i < 4; i++)
        {
            int nx = x + (maps[x][y] * dx[i]);
            int ny = y + (maps[x][y] * dy[i]);
            int rst = dfs(nx,ny);
            // 싸이클 발견 시
            if(rst==-1){
                return -1;
            }
            // 갈 수 있는 최대 노드 저장
            dp[x][y] = Math.max(dp[x][y],rst + 1);
        }
        // 방문 철회
        visited[x][y] = false;
        return dp[x][y];
    }
}
