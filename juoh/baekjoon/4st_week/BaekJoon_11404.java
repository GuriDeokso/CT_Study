package baekjoon;

import java.io.*;
import java.util.*;

public class BaekJoon_11404 {
    static final int INF = Integer.MAX_VALUE / 2;
    static int[][] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int citySize = Integer.parseInt(br.readLine());
        int busSize = Integer.parseInt(br.readLine());
        graph = new int[citySize + 1][citySize + 1];
        for(int i=1; i <= citySize; i++) {
            for(int j=1; j <= citySize; j++) {
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }
        for(int i=0; i<busSize; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][end] = Math.min(graph[start][end], cost);
        }
        DofloydWarshall(citySize);
        printGraph(citySize);

    }

    public static void DofloydWarshall(int length) {
        for(int k=1; k<=length; k++) {
            for(int i=1; i<=length; i++) {
                for(int j=1; j<=length; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

    }

    public static void printGraph(int length) {
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<=length; i++) {
            for(int j=1; j<=length; j++) {
                if(graph[i][j] >= INF) sb.append("0 ");
                else sb.append(graph[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
