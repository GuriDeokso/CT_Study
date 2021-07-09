package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_14621 {
    static int[] parent;
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, String> univType = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int univSize = Integer.parseInt(st.nextToken());
        int roadSize = Integer.parseInt(st.nextToken());

        parent = new int[univSize + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=univSize; i++) {
            String type = st.nextToken();
            univType.put(i, type);
            parent[i] = i;
        }
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0; i<roadSize; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if(univType.get(start)!= null && !univType.get(start).equals(univType.get(end))) {
                edges.add(new Edge(start, end, cost));
            }
        }
        Collections.sort(edges);
        int answer = 0;
        int count = 0;
        for(Edge e: edges) {
            if(findParent(e.start) != findParent(e.end)) {
                unionParent(e.start, e.end);
                answer += e.cost;
                count++;
            }
        }
        System.out.println((count != univSize - 1) ? -1 : answer);

    }

    public static int findParent(int self) {
        if(parent[self] != self) {
            parent[self] = findParent(parent[self]);
        }
        return parent[self];
    }

    public static void unionParent(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if(pa < pb) {
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
        }
    }
}
