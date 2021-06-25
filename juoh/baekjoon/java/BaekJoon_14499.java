import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_14499 {
    public static void main(String[] args) throws IOException {
        BaekJoon_14499.solution();
    }

    public static void solution() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var n = Integer.parseInt(st.nextToken());
        var m = Integer.parseInt(st.nextToken());
        var x = Integer.parseInt(st.nextToken());
        var y = Integer.parseInt(st.nextToken());
        var k = Integer.parseInt(st.nextToken());
        var board = new int[n][m];
        var commands = new int[k];

        for (var i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (var j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (var i = 0; i < k; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        var dice = new int[7];
        Arrays.fill(dice, 0);

        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};

        for(int command: commands) {
            int nx = x + dx[command];
            int ny = y + dy[command];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            else {
                dice = BaekJoon_14499.move(command, dice);
                if (board[nx][ny] == 0) {
                    board[nx][ny] = dice[6];
                }
                else {
                    dice[6] = board[nx][ny];
                    board[nx][ny] = 0;
                }
                x = nx;
                y = ny;
            }
            System.out.println(dice[1]);
        }
    }

    public static int[] move(int command, int[] dice) {
        /*
        동쪽 이동    서쪽 이동   북쪽 이동   남쪽 이동
        1 -> 4     1 -> 3    1 -> 5    1 -> 2
        2 -> 2     2 -> 2    2 -> 1    2 -> 6
        3 -> 1     3 -> 6    3 -> 3    3 -> 3
        4 -> 6     4 -> 1    4 -> 4    4 -> 4
        5 -> 5     5 -> 5    5 -> 6    5 -> 1
        6 -> 3     6 -> 4    6 -> 2    6 -> 5
        */
        if (command == 1) {
            return new int[] {0, dice[4], dice[2], dice[1], dice[6], dice[5], dice[3]};
        }
        else if (command == 2) {
            return new int[]{0, dice[3], dice[2], dice[6], dice[1], dice[5], dice[4]};
        }
        else if (command == 3) {
            return new int[]{0, dice[5], dice[1], dice[3], dice[4], dice[6], dice[2]};
        }
        else {
            return new int[]{0, dice[2], dice[6], dice[3], dice[4], dice[1], dice[5]};
        }

    }
}
