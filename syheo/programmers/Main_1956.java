package syheo.programmers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;

public class Main_1956{
    static final int INF = 999999999;
    

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] maps = new int[V+1][V+1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j) {
                    maps[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            maps[a][b] = c;
        }
        //logic
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (maps[i][j] > maps[i][k] + maps[k][j]) {
                        maps[i][j] = maps[i][k] + maps[k][j];
                    }
                }
            }
        }

        int answer = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                if (maps[i][j] != INF && maps[j][i] != INF) {
                    answer = Math.min(answer, maps[i][j] + maps[j][i]);
                }
            }
        }
        //output
        if(answer == INF){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }
    }
}

