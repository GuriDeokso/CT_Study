package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge{  // Edge를 하나의 클래스로 표현하여 입력 받음
    public int weight; // edge에 부여된 가중치
    public int v; // edge 끝 부분에 있는 vertex의 번호

    Edge(int v, int weight){ // edge에 값 setting하는 함수
        this.weight = weight;
        this.v = v;
    }

}


public class BaekJoon_15971 {
    static boolean[] visited;
    static ArrayList<Edge>[] graph;
    static int result = 0;
    static int N;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        Arrays.fill(visited, false);
        graph = new ArrayList[N+1];
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[startNode].add(new Edge(endNode, cost));
            graph[endNode].add(new Edge(startNode, cost));
        }
        dfs(start, 0, 0);
        System.out.println(result);
    }

    public static void dfs(int v, int dist, int maxValue) {
        visited[v] = true;
        if (v == end) {
            result = dist - maxValue;
            return;
        }
        for (Edge edge: graph[v]) {
            if (!visited[edge.v]) {
                dfs(edge.v, dist + edge.weight, Math.max(maxValue, edge.weight));
            }
        }

    }
}
