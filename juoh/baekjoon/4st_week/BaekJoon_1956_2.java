package baekjoon_python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1956_2 {

    static final int INF = 10001;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        graph = new int[v+1][v+1];
        for(int i=1; i<v+1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][end] = cost;
        }
        DofloydWarshall(v);
        System.out.println(solution(v));
    }

    public static void DofloydWarshall(int length) {
        // 플로이드 와샬
        for(int k=1; k<=length; k++) {
            for(int i=1; i<=length; i++) {
                for(int j=1; j<=length; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static int solution(int length) {
        int minCost = Integer.MAX_VALUE;
        for(int i=1; i<=length; i++) {
            for(int j=1; j<length; j++) {
                if(i == j) continue;
                if (graph[i][j] != INF && graph[j][i] != INF) {
                    minCost = Math.min(minCost, graph[i][j] + graph[j][i]);
                }
            }
        }
        return (minCost < Integer.MAX_VALUE) ? minCost : -1;
    }
}
