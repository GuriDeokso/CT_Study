package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BaekJoon_2668 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 0;

        N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i=0; i<N; i++) {
            int x = Integer.parseInt(br.readLine());
            graph.get(i).add(x);
//            System.out.println(graph);
        }
        for (int i=0; i<N; i++) {
            boolean[] visited = new boolean[N];
            Arrays.fill(visited, false);
            dfs(i, i, visited);
        }
        System.out.println(result.size());
        for(int x: result) {
            System.out.println(x);
        }
    }

    public static void dfs(int v, int i, boolean[] visited) {
        visited[v] = true;
        for (int node: graph.get(v)) {
            node--;
            if (!visited[node]) {
                dfs(node, i, visited);
            } else if (visited[node] && node == i) {
                result.add(node+1);
            }
        }
    }
}
