package syheo.programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main_11404{
    
    public static int cityCnt;
    public static int[][] maps;
    public static final int INF = 1000000000;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        cityCnt = Integer.parseInt(br.readLine());
        
        int busCnt = Integer.parseInt(br.readLine());
        
        maps = new int[cityCnt+1][cityCnt+1];
        
        //disance 초기화
        for(int i=1; i <= cityCnt; i++) {
            for(int j=1; j <= cityCnt; j++) {
                if(i == j) continue;
                maps[i][j] = INF;
            }
        }
        
        for(int i=1; i <= busCnt; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            maps[a][b] = Math.min(maps[a][b], c);    
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i <= cityCnt; i++) {
            for(int j=1; j <= cityCnt; j++) {
                if(maps[i][j] >= INF) sb.append("0 ");
                else sb.append(maps[i][j] + " ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    public static void floydWarshall() {
        for(int k = 1; k <= cityCnt; k++) {
            for(int i=1; i <= cityCnt; i++) {
                for(int j=1; j <= cityCnt; j++) {
                    maps[i][j] = Math.min(maps[i][k] + maps[k][j], maps[i][j]);
                }
            }
        }
    }
    

}
