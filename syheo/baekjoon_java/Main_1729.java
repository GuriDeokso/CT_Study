package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * i번째 행의 6개의 수들에 1씩을 더한다.
 * i번째 행의 6개의 수들에서 1씩을 뺀다.
 * i번째 열의 6개의 수들에 1씩을 더한다.
 * i번째 열의 6개의 수들에서 1씩을 뺀다.
 * 왼쪽 위에서 오른쪽 아래로의 대각선 위에 있는 6개의 수들에 1씩을 더한다.
 * 오른쪽 위에서 왼쪽 아래로의 대각선 위에 있는 6개의 수들에 1씩을 더한다.
 * 왼쪽 위에서 오른쪽 아래로의 대각선 위에 있는 6개의 수들에서 1씩을 뺀다.
 * 오른쪽 위에서 왼쪽 아래로의 대각선 위에 있는 6개의 수들에서 1씩을 뺀다.
 */

public class Main_1729 {

    static final int MAP_SIZE = 2;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[][] maps = new int[MAP_SIZE][MAP_SIZE];
    static int maxSum = 0;
    static int cnt = 0;
    static Map<Integer, Integer> rowVi = new LinkedHashMap<>();
    static Map<Integer, Integer> colVi = new LinkedHashMap<>();
    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        // 1. input
        for (int i = 0; i < MAP_SIZE; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < MAP_SIZE; j++) {
                maps[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 1. init maxSum
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                maxSum += maps[i][j];
            }
        }

        // 2. dfs
        dfs(9, 9, 9, 9, maxSum);

        System.out.println(visited.size());
        System.out.println(maxSum);
    }

    private static void dfs(int rowCnt, int colCnt, int diaCnt, int diaRCnt, int nowSum) {
        if ((rowCnt == 0 && colCnt == 0 && diaCnt == 0 && diaRCnt == 0 || (visited.contains(makeString(rowCnt, colCnt, diaCnt, diaRCnt, rowVi.toString(), colVi.toString()))))) {
            cnt++;
            return;
        }
        visited.add(makeString(rowCnt, colCnt, diaCnt, diaRCnt, rowVi.toString(), colVi.toString()));
        maxSum = Math.max(maxSum, nowSum);
        if (rowCnt != 0) {
            for (int i = 0; i < MAP_SIZE; i++) {
                int sum = addRow(i);
                rowVi.putIfAbsent(i, rowVi.getOrDefault(i, 0) + 1);
                dfs(rowCnt - 1, colCnt, diaCnt, diaRCnt, nowSum + sum);
                if (rowVi.get(i) == 1) {
                    rowVi.remove(i);
                } else {
                    rowVi.put(i, rowVi.get(i) - 1);
                }
                minusRow(i);
            }
        }
        if (colCnt != 0) {
            for (int i = 0; i < MAP_SIZE; i++) {
                int sum = addCol(i);
                colVi.putIfAbsent(i, colVi.getOrDefault(i, 0) + 1);
                dfs(rowCnt, colCnt - 1, diaCnt, diaRCnt, nowSum + sum);
                System.out.println("i == " + i + colVi.toString());
                if (colVi.get(i) == 1) {
                    colVi.remove(i);
                } else {
                    colVi.put(i, colVi.get(i) - 1);
                }
                minusCol(i);
            }
        }
        if (diaCnt != 0) {
            int sum = addDiagonal();
            dfs(rowCnt, colCnt, diaCnt - 1, diaRCnt, nowSum + sum);
            minusDiagonal();
        }
        if (diaRCnt != 0) {
            int sum = addDiagonalReversed();
            dfs(rowCnt, colCnt, diaCnt, diaRCnt - 1, nowSum + sum);
            minusDiagonalReversed();
        }
    }

    public static int addRow(int row) {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[row][i] == 9) {
                sum -= 9;
                maps[row][i] = 0;
            } else {
                sum += 1;
                maps[row][i] += 1;
            }
        }
        return sum;
    }

    public static int addCol(int col) {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[i][col] == 9) {
                sum -= 9;
                maps[i][col] = 0;
            } else {
                sum += 1;
                maps[i][col] += 1;
            }
        }
        return sum;
    }

    public static int minusRow(int row) {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[row][i] == 0) {
                sum += 9;
                maps[row][i] = 9;
            } else {
                sum -= 1;
                maps[row][i] -= 1;
            }
        }
        return sum;
    }

    public static int minusCol(int col) {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[i][col] == 0) {
                sum += 9;
                maps[i][col] = 9;
            } else {
                sum -= 1;
                maps[i][col] -= 1;
            }
        }
        return sum;
    }

    public static int addDiagonal() {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[i][i] == 9) {
                sum -= 9;
                maps[i][i] = 0;
            } else {
                sum += 1;
                maps[i][i] += 1;
            }
        }
        return sum;
    }

    public static int addDiagonalReversed() {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[i][MAP_SIZE - 1 - i] == 9) {
                sum -= 9;
                maps[i][MAP_SIZE - 1 - i] = 0;
            } else {
                sum += 1;
                maps[i][MAP_SIZE - 1 - i] += 1;
            }
        }
        return sum;
    }

    public static int minusDiagonal() {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[i][i] == 0) {
                sum += 9;
                maps[i][i] = 9;
            } else {
                sum -= 1;
                maps[i][i] -= 1;
            }
        }
        return sum;
    }

    public static int minusDiagonalReversed() {
        int sum = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (maps[i][MAP_SIZE - 1 - i] == 0) {
                sum += 9;
                maps[i][MAP_SIZE - 1 - i] = 9;
            } else {
                sum -= 1;
                maps[i][MAP_SIZE - 1 - i] -= 1;
            }
        }
        return sum;
    }

    public static String makeString(int rowCnt, int colCnt, int diaCnt, int diaRCnt, String rowVi, String colVi) {
        return rowCnt + "," + colCnt + "," + diaCnt + "," + diaRCnt + "," + rowVi + "," + colVi;
    }

}
