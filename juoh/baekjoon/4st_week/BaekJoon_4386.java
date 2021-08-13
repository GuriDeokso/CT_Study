package baekjoon_python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_4386 {
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        double cost;

        Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int starSize = Integer.parseInt(br.readLine());
        int[] parent = new int[starSize];

        // 별좌표 및 부모 설정
        ArrayList<double[]> stars = new ArrayList<>();
        for(int i=0; i<starSize; i++) {
            st = new StringTokenizer(br.readLine());
            double[] pos = {
                Double.parseDouble(st.nextToken()),
                Double.parseDouble(st.nextToken())
            };
            stars.add(pos);
            parent[i] = i;
        }
        // 간선 생성
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i=0; i<starSize; i++) {
            for(int j=i+1; j<starSize; j++) {
                double[] pos = stars.get(i);
                double[] pos2 = stars.get(j);
                edges.add(new Edge(i, j, getDist(pos[0], pos[1], pos2[0], pos2[1])));
            }
        }
        // 간선 cost 오름차순
        Collections.sort(edges);
        double answer = 0;
        for(Edge edge: edges) {
            if (findParent(parent, edge.start) != findParent(parent, edge.end)) {
                unionParnet(parent, edge.start, edge.end);
                answer += edge.cost;
            }
        }
        System.out.println(Math.round(answer * 100) / 100.0);
    }

    public static double getDist(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2));
    }

    public static void unionParnet(int[] parent, int a, int b) {
        int parentA = findParent(parent, a);
        int parentB = findParent(parent, b);

        if (parentA < parentB) parent[parentB] = parentA;
        else parent[parentA] = parentB;
    }

    public static int findParent(int[] parent, int self) {
        if(parent[self] != self) {
            parent[self] = findParent(parent, parent[self]);
        }
        return parent[self];
    }
 }
