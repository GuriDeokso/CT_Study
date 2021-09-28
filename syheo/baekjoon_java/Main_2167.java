package baekjoon_java;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main_2167 {

    /**
     * solved.ac
     * 백준
     * 2167
     * 2차원 배열의 합
     * 브론즈 1
     * 아이디어 :
     * array를 dp로 활용하여 array[x][y] 는 array[x][y-1]까지의 모든 합을 저장한다.
     * 정답은 구간 합의 모든 행에 대한 합을 계산하여 도출
     * 회고 :
     * array의 dp 점화식을 문제 구간합과 동일하게 하여
     * 더 효율적으로 짜보자.
     **/

    static class Info{
        Point start;
        Point end;

        public Info(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M;
    static int[][] array;
    static int K;
    static List<Info> infos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 1. input
        tokens = new StringTokenizer(input.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        array = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 1; j <= M; j++) {
                array[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        K = Integer.parseInt(input.readLine());

        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(input.readLine());
            infos.add(new Info(new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())), new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()))));
        }

        // 2. 누적합 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (j != 0) {
                    array[i][j] += array[i][j - 1];
                } else {
                    array[i][j] = array[i - 1][M];
                }
            }
        }

        // 3. 정답 계산 , 출력
        for (Info info : infos) {
            int sum = 0;
            Point start = info.start;
            Point end = info.end;
            for (int i = start.x; i <= end.x; i++) {
                sum += array[i][end.y] - array[i][start.y - 1];
            }
            System.out.println(sum);
        }

    }
}
