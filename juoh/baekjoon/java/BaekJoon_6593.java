package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int h;
    int x;
    int y;
    int time;
    Node(int h, int x, int y, int time) {
        this.h = h;
        this.x = x;
        this.y = y;
        this.time = time;
    }
}


class Main {
    static int L, R, C;
    static char[][][] board;
    static boolean[][][] visited;
    static int[] dh = new int[]{0, 0, 0, 0, -1, 1};
    static int[] dx = new int[]{0, 0, 1, -1, 0, 0};
    static int[] dy = new int[]{1, -1, 0, 0, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 || R == 0 || C == 0) {
                break;
            }
            visited = new boolean[L][R][C];
            board = new char[L][R][C];
            Node start = null;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String input = br.readLine();
                    for (int k = 0; k < C; k++) {
                        board[i][j][k] = input.charAt(k);
                        if (board[i][j][k] == 'S') {
                            start = new Node(i, j, k, 0);
                        }
                    }
                }
                br.readLine();
            }
            int time = bfs(start);
            if (time > 0) {
                System.out.println("Escaped in "+ time +" minute(s).");
            } else {
                System.out.println("Trapped!");
            }

        }
    }

    public static int bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visited[start.h][start.x][start.y] = true;
        while(!q.isEmpty()) {
            Node node = q.remove();
            for(int i=0; i<6; i++) {
                int nh = node.h + dh[i];
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(isValid(nh, nx, ny)) {
                    if (board[nh][nx][ny] == 'E') {
                        return node.time + 1;
                    }
                    if (!visited[nh][nx][ny] && board[nh][nx][ny] == '.') {
                        q.add(new Node(nh, nx, ny, node.time + 1));
                        visited[nh][nx][ny] = true;
                    }
                }
            }
        }
        return -1;

    }

    public static boolean isValid(int h, int x, int y) {
        return h >= 0 && x >= 0 && y >= 0 && h < L && x < R && y < C;
    }
}
