package baekjoon;

import java.io.*;
import java.util.*;


public class BaekJoon_1956{
    static class Node {
        int v;
        int cost;
        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int minCost = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        for(int i=0; i<v + 1; i++) {
            graph.add(new ArrayList<>());
        }


        int e = Integer.parseInt(st.nextToken());
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }
        for(int i=1; i<v + 1; i++) {
            visited = new boolean[v + 1];
            dfs(i, i, 0);
        }
        System.out.println((minCost >= Integer.MAX_VALUE) ? minCost : -1);
    }

    public static void dfs(int start, int root, int cost) {
        visited[start] = true;
        for(Node adjNode: graph.get(start)) {
            if(!visited[adjNode.v]) {
                dfs(adjNode.v, root, cost + adjNode.cost);
            } else if(adjNode.v == root) {
                minCost = Math.min(minCost, cost + adjNode.cost); //
            }
        }
    }
}
