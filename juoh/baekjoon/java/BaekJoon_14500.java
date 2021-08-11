import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_14500 {
    static int N, M;
    static int maxValue = 0; // 최댓값
    static int[] dy = new int[] {0, 0, -1, 1}; // 동 서 북 남
    static int[] dx = new int[] {1, -1, 0, 0};
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        solution();

    }

    public static void solution() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M]; // 방문 체크

        for (var i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (var j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (var i = 0; i < N; i++) {
            for (var j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;

                middleFinger(i, j);
            }

        }
        System.out.println(maxValue);
    }

    public static void dfs(int x, int y, int count, int sumValue) {
        if (count == 4) {
            maxValue = Math.max(maxValue, sumValue);
            return;
        }
        else {
            for (var i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, count + 1, sumValue + board[nx][ny]);
                    visited[nx][ny] = false;
                }

            }
        }

    }

    public static void middleFinger(int x, int y) {
        var wing = 4; // 가운데에서의 상하좌우 날개
        var min = Integer.MAX_VALUE;
        var sum = board[x][y];
        
        for (var i = 0; i < 4; i++) {
            int nextRow = x + dx[i];
            int nextCol = y + dy[i];

            // 날개가 2개이상 없다면 ㅗ 모양이 아니다. 그러므로 함수를 종료한다.
            if (wing <= 2)
                return;
            // 날개가 맵 바깥에 있는 경우
            if (!isValid(nextRow, nextCol)) {
                wing--;
                continue;
            }
            min = Math.min(min, board[nextRow][nextCol]);
            sum += board[nextRow][nextCol];
        }
        // 날개가 4개일때 가장 작은 날개를 없애야 ㅗ,ㅏ,ㅓ,ㅜ 모양이 나온다.
        if (wing == 4) {
            sum -= min;
        }
        maxValue = Math.max(maxValue, sum);
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}
